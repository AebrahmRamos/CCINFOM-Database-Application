import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AdmissionDAO {

    public void createAdmission(Admission admission) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Admission (patientId, roomId, doctorId, admissionDate, admissionType, status) " +
                             "VALUES (?, ?, ?, ?, ?, ?)")) {

            statement.setInt(1, admission.getPatientID());
            statement.setInt(2, admission.getRoomID());
            statement.setInt(3, admission.getDoctorID());
            statement.setTimestamp(4, Timestamp.valueOf(admission.getAdmissionDate().atStartOfDay()));
            statement.setString(5, admission.getAdmissionType());
            statement.setString(6, admission.getStatus());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating admission: " + e.getMessage());
        }
    }

    public Admission getAdmissionById(int admissionId) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM Admission WHERE admissionId = ?")) {

            statement.setInt(1, admissionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Extract data from ResultSet and create Admission object
                    return new Admission(
                        resultSet.getInt("admissionId"),
                        resultSet.getInt("patientId"),
                        resultSet.getInt("roomId"),
                        resultSet.getTimestamp("admissionDate").toLocalDateTime().toLocalDate(),
                        resultSet.getString("admissionType"),
                        resultSet.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting admission by ID: " + e.getMessage());
        }
        return null; 
    }

    public void updateAdmission(Admission admission) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Admission SET patientId = ?, roomId = ?, doctorId = ?, " +
                             "admissionDate = ?, admissionType = ?, status = ? WHERE admissionId = ?")) {

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating admission: " + e.getMessage());
        }
    }

    public void deleteAdmission(int admissionId) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM Admission WHERE admissionId = ?")) {

            statement.setInt(1, admissionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting admission: " + e.getMessage());
        }
    }

    // Example for getting all admissions
    public List<Admission> getAllAdmissions() {
        List<Admission> admissions = new ArrayList<>();
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Admission");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                admissions.add(new Admission(
                    resultSet.getInt("admissionId"),
                    resultSet.getInt("patientId"),
                    resultSet.getInt("roomId"),
                    resultSet.getTimestamp("admissionDate").toLocalDateTime().toLocalDate(),
                    resultSet.getString("admissionType"),
                    resultSet.getString("status")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all admissions: " + e.getMessage());
        }
        return admissions;
    }
}