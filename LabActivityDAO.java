import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LabActivityDAO {

    public void createLabActivity(LabActivity labActivity) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO LabActivity (labRequestID, labStaffID, startTime, endTime, activityType, complexity, status) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            statement.setInt(1, labActivity.getLabRequestID());
            statement.setInt(2, labActivity.getLabStaffID());
            statement.setTimestamp(3, Timestamp.valueOf(labActivity.getStartTime()));
            statement.setTimestamp(4, Timestamp.valueOf(labActivity.getEndTime()));
            statement.setString(5, labActivity.getActivityType());
            statement.setInt(6, labActivity.getComplexity());
            statement.setString(7, labActivity.getStatus());

            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    labActivity.setLabActivityID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error creating lab activity: " + e.getMessage());
        }
    }

    public LabActivity getLabActivityByID(int activityID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM LabActivity WHERE activityID = ?")) {

            statement.setInt(1, activityID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new LabActivity(
                            resultSet.getInt("activityID"),
                            resultSet.getInt("labRequestID"),
                            resultSet.getInt("labStaffID"),
                            resultSet.getTimestamp("startTime").toLocalDateTime(),
                            resultSet.getTimestamp("endTime").toLocalDateTime(),
                            resultSet.getString("activityType"),
                            resultSet.getInt("complexity"),
                            resultSet.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting lab activity by ID: " + e.getMessage());
        }
        return null;
    }

    public void updateLabActivity(LabActivity labActivity) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE LabActivity SET labRequestID = ?, labStaffID = ?, startTime = ?, endTime = ?, " +
                             "activityType = ?, complexity = ?, status = ? WHERE activityID = ?")) {

            statement.setInt(1, labActivity.getLabRequestID());
            statement.setInt(2, labActivity.getLabStaffID());
            statement.setTimestamp(3, Timestamp.valueOf(labActivity.getStartTime()));
            statement.setTimestamp(4, Timestamp.valueOf(labActivity.getEndTime()));
            statement.setString(5, labActivity.getActivityType());
            statement.setInt(6, labActivity.getComplexity());
            statement.setString(7, labActivity.getStatus());
            statement.setInt(8, labActivity.getLabActivityID());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating lab activity: " + e.getMessage());
        }
    }

    public void deleteLabActivity(int activityID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM LabActivity WHERE activityID = ?")) {

            statement.setInt(1, activityID);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting lab activity: " + e.getMessage());
        }
    }

    public List<LabActivity> getAllLabActivities() {
        List<LabActivity> labActivities = new ArrayList<>();
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM LabActivity");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                labActivities.add(new LabActivity(
                        resultSet.getInt("activityID"),
                        resultSet.getInt("labRequestID"),
                        resultSet.getInt("labStaffID"),
                        resultSet.getTimestamp("startTime").toLocalDateTime(),
                        resultSet.getTimestamp("endTime").toLocalDateTime(),
                        resultSet.getString("activityType"),
                        resultSet.getInt("complexity"),
                        resultSet.getString("status")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all lab activities: " + e.getMessage());
        }
        return labActivities;
    }
}