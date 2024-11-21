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
                     "INSERT INTO Room (roomType, isAvailable, lastMaintenance) " +
                             "VALUES (?, ?, ?)")) {

            statement.setString(1, room.getRoomType());
            statement.setBoolean(2, room.isAvailable());
            statement.setDate(3, Date.valueOf(room.getLastMaintenance())); 

            statement.executeUpdate();
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
                            resultSet.getDate("lastMaintenance").toLocalDate() 
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
                     "UPDATE Room SET roomType = ?, isAvailable = ?, lastMaintenance = ? " +
                             "WHERE roomID = ?")) {

            statement.setString(1, room.getRoomType());
            statement.setBoolean(2, room.isAvailable());
            statement.setDate(3, Date.valueOf(room.getLastMaintenance())); 
            statement.setInt(4, room.getRoomID());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating room: " + e.getMessage());
        }
    }

    public void deleteRoom(int roomID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Room WHERE roomID = ?")) {

            statement.setInt(1, roomID);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting room: " + e.getMessage());
        }
    }

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
                        resultSet.getDate("lastMaintenance").toLocalDate() 
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all rooms: " + e.getMessage());
        }
        return rooms;
    }
}