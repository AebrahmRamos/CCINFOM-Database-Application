import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DiagnosisDAO {

    public void createDiagnosis(Diagnosis diagnosis) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Diagnosis (diseaseID, admissionID, admissionType, diagnosisDate, severity, status) " +
                             "VALUES (?, ?, ?, ?, ?, ?)")) {

            statement.setInt(1, diagnosis.getDiseaseID());
            statement.setInt(2, diagnosis.getAdmissionID());
            statement.setString(3, diagnosis.getAdmissionType());
            statement.setTimestamp(4, Timestamp.valueOf(diagnosis.getDiagnosisDate().atStartOfDay()));
            statement.setString(5, diagnosis.getSeverity());
            statement.setString(6, diagnosis.getStatus());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating diagnosis: " + e.getMessage());
        }
    }

    public Diagnosis getDiagnosisByID(int diagnosisID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Diagnosis WHERE diagnosisID = ?")) {

            statement.setInt(1, diagnosisID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Diagnosis(
                            resultSet.getInt("diagnosisID"),
                            resultSet.getInt("diseaseID"),
                            resultSet.getInt("admissionID"),
                            resultSet.getString("admissionType"),
                            resultSet.getTimestamp("diagnosisDate").toLocalDateTime().toLocalDate(),
                            resultSet.getString("severity"),
                            resultSet.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting diagnosis by ID: " + e.getMessage());
        }
        return null;
    }

    public void updateDiagnosis(Diagnosis diagnosis) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Diagnosis SET diseaseID = ?, admissionID = ?, admissionType = ?, diagnosisDate = ?, " +
                             "severity = ?, status = ? WHERE diagnosisID = ?")) {

            statement.setInt(1, diagnosis.getDiseaseID());
            statement.setInt(2, diagnosis.getAdmissionID());
            statement.setString(3, diagnosis.getAdmissionType());
            statement.setTimestamp(4, Timestamp.valueOf(diagnosis.getDiagnosisDate().atStartOfDay()));
            statement.setString(5, diagnosis.getSeverity());
            statement.setString(6, diagnosis.getStatus());
            statement.setInt(7, diagnosis.getDiagnosisID());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating diagnosis: " + e.getMessage());
        }
    }

    public void deleteDiagnosis(int diagnosisID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Diagnosis WHERE diagnosisID = ?")) {

            statement.setInt(1, diagnosisID);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting diagnosis: " + e.getMessage());
        }
    }

    public List<Diagnosis> getAllDiagnoses() {
        List<Diagnosis> diagnoses = new ArrayList<>();
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Diagnosis");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                diagnoses.add(new Diagnosis(
                        resultSet.getInt("diagnosisID"),
                        resultSet.getInt("diseaseID"),
                        resultSet.getInt("admissionID"),
                        resultSet.getString("admissionType"),
                        resultSet.getTimestamp("diagnosisDate").toLocalDateTime().toLocalDate(),
                        resultSet.getString("severity"),
                        resultSet.getString("status")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all diagnoses: " + e.getMessage());
        }
        return diagnoses;
    }
}