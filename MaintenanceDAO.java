import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceDAO {

    public void createMaintenance(Maintenance maintenance) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Maintenance (roomID, scheduleDate, completionDate, status, notes) " +
                             "VALUES (?, ?, ?, ?, ?)")) {

            statement.setInt(1, maintenance.getRoomID());
            statement.setTimestamp(2, Timestamp.valueOf(maintenance.getScheduleDate()));
            statement.setTimestamp(3, Timestamp.valueOf(maintenance.getCompletionDate()));
            statement.setString(4, maintenance.getStatus());
            statement.setString(5, maintenance.getNotes());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating maintenance record: " + e.getMessage());
        }
    }

    public Maintenance getMaintenanceByID(int maintenanceID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Maintenance WHERE maintenanceID = ?")) {

            statement.setInt(1, maintenanceID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Maintenance(
                            resultSet.getInt("maintenanceID"),
                            resultSet.getInt("roomID"),
                            resultSet.getTimestamp("scheduleDate").toLocalDateTime(),
                            resultSet.getTimestamp("completionDate").toLocalDateTime(),
                            resultSet.getString("status"),
                            resultSet.getString("notes")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting maintenance record by ID: " + e.getMessage());
        }
        return null;
    }

    public void updateMaintenance(Maintenance maintenance) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Maintenance SET roomID = ?, scheduleDate = ?, completionDate = ?, " +
                             "status = ?, notes = ? WHERE maintenanceID = ?")) {

            statement.setInt(1, maintenance.getRoomID());
            statement.setTimestamp(2, Timestamp.valueOf(maintenance.getScheduleDate()));
            statement.setTimestamp(3, Timestamp.valueOf(maintenance.getCompletionDate()));
            statement.setString(4, maintenance.getStatus());
            statement.setString(5, maintenance.getNotes());
            statement.setInt(6, maintenance.getMaintenanceID());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating maintenance record: " + e.getMessage());
        }
    }

    public void deleteMaintenance(int maintenanceID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Maintenance WHERE maintenanceID = ?")) {

            statement.setInt(1, maintenanceID);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting maintenance record: " + e.getMessage());
        }
    }

    public List<Maintenance> getAllMaintenanceRecords() {
        List<Maintenance> maintenanceRecords = new ArrayList<>();
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Maintenance");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                maintenanceRecords.add(new Maintenance(
                        resultSet.getInt("maintenanceID"),
                        resultSet.getInt("roomID"),
                        resultSet.getTimestamp("scheduleDate").toLocalDateTime(),
                        resultSet.getTimestamp("completionDate").toLocalDateTime(),
                        resultSet.getString("status"),
                        resultSet.getString("notes")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all maintenance records: " + e.getMessage());
        }
        return maintenanceRecords;
    }
}