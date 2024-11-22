import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    public void createRoom(Room room) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Room (roomType, isAvailable, lastMaintenance, cost) " +
                             "VALUES (?, ?, ?, ?)")) {

            statement.setString(1, room.getRoomType());
            statement.setBoolean(2, room.isAvailable());
            statement.setDate(3, Date.valueOf(room.getLastMaintenance()));
            statement.setDouble(4, room.getCost()); // Include cost in insertion

            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    room.setRoomID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error creating room: " + e.getMessage());
        }
    }

    public Room getRoomByID(int roomID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Room WHERE roomID = ?")) {

            statement.setInt(1, roomID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Room(
                            resultSet.getInt("roomID"),
                            resultSet.getString("roomType"),
                            resultSet.getBoolean("isAvailable"),
                            resultSet.getDate("lastMaintenance").toLocalDate(),
                            resultSet.getDouble("cost") // Retrieve cost from result set
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting room by ID: " + e.getMessage());
        }
        return null;
    }

    public void updateRoom(Room room) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Room SET roomType = ?, isAvailable = ?, lastMaintenance = ?, cost = ? " +
                             "WHERE roomID = ?")) {

            statement.setString(1, room.getRoomType());
            statement.setBoolean(2, room.isAvailable());
            statement.setDate(3, Date.valueOf(room.getLastMaintenance()));
            statement.setDouble(4, room.getCost()); // Include cost in update
            statement.setInt(5, room.getRoomID());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating room: " + e.getMessage());
        }
    }

    // ... (deleteRoom method remains unchanged)

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Room");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                rooms.add(new Room(
                        resultSet.getInt("roomID"),
                        resultSet.getString("roomType"),
                        resultSet.getBoolean("isAvailable"),
                        resultSet.getDate("lastMaintenance").toLocalDate(),
                        resultSet.getDouble("cost") // Retrieve cost from result set
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all rooms: " + e.getMessage());
        }
        return rooms;
    }
}