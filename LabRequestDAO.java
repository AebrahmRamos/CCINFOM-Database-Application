import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LabRequestDAO {

    public void createLabRequest(LabRequest labRequest) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO LabRequest (patientID, doctorID, laboratoryID, requestDate, cost) " +
                             "VALUES (?, ?, ?, ?, ?)")) {

            statement.setInt(1, labRequest.getPatientID());
            statement.setInt(2, labRequest.getDoctorID());
            statement.setInt(3, labRequest.getLaboratoryID());
            statement.setTimestamp(4, Timestamp.valueOf(labRequest.getLabRequestDate()));
            statement.setDouble(5, labRequest.getCost()); // Include cost in insertion

            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    labRequest.setLabRequestID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error creating lab request: " + e.getMessage());
        }
    }

    public LabRequest getLabRequestByID(int requestID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM LabRequest WHERE requestID = ?")) {

            statement.setInt(1, requestID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new LabRequest(
                            resultSet.getInt("requestID"),
                            resultSet.getInt("patientID"),
                            resultSet.getInt("doctorID"),
                            resultSet.getInt("laboratoryID"),
                            resultSet.getTimestamp("requestDate").toLocalDateTime(),
                            resultSet.getDouble("cost")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting lab request by ID: " + e.getMessage());
        }
        return null;
    }

    public void updateLabRequest(LabRequest labRequest) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE LabRequest SET patientID = ?, doctorID = ?, laboratoryID = ?, requestDate = ?, cost = ? " +
                             "WHERE requestID = ?")) {

            statement.setInt(1, labRequest.getPatientID());
            statement.setInt(2, labRequest.getDoctorID());
            statement.setInt(3, labRequest.getLaboratoryID());
            statement.setTimestamp(4, Timestamp.valueOf(labRequest.getLabRequestDate()));
            statement.setDouble(5, labRequest.getCost());
            statement.setInt(6, labRequest.getLabRequestID());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating lab request: " + e.getMessage());
        }
    }

    public void deleteLabRequest(int requestID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM LabRequest WHERE requestID = ?")) {

            statement.setInt(1, requestID);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting lab request: " + e.getMessage());
        }
    }

    public List<LabRequest> getAllLabRequests() {
        List<LabRequest> labRequests = new ArrayList<>();
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM LabRequest");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                labRequests.add(new LabRequest(
                        resultSet.getInt("requestID"),
                        resultSet.getInt("patientID"),
                        resultSet.getInt("doctorID"),
                        resultSet.getInt("laboratoryID"),
                        resultSet.getTimestamp("requestDate").toLocalDateTime(),
                        resultSet.getDouble("cost")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all lab requests: " + e.getMessage());
        }
        return labRequests;
    }
}