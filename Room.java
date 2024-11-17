import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Room {
    private int roomID;
    private String roomType;
    private boolean availability;
    private int patientID;
    private String maintenanceSchedule;

    public Room(int roomID, String roomType, boolean availability, int patientID, String maintenanceSchedule) {
        this.roomID = roomID;
        this.roomType = roomType;
        this.availability = availability;
        this.patientID = patientID;
        this.maintenanceSchedule = maintenanceSchedule;
    }

    public void saveRoom(Connection connection) {
        String sql = "INSERT INTO Room (roomID, roomType, availability, patientID, maintenanceSchedule) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, this.roomID);
            pstmt.setString(2, this.roomType);
            pstmt.setBoolean(3, this.availability);
            pstmt.setInt(4, this.patientID);
            pstmt.setString(5, this.maintenanceSchedule);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getRoomDetails(int roomID, Connection connection) {
        String sql = "SELECT * FROM Room WHERE roomID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, roomID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Room Type: " + rs.getString("roomType"));
                System.out.println("Availability: " + rs.getBoolean("availability"));
                // Additional fields can be fetched similarly
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
