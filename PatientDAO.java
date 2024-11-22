import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date; 
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public void createPatient(Patient patient) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Patient (firstName, lastName, birthDate, HMO, medicalHistory)", 
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getLastName());
            statement.setDate(3, Date.valueOf(patient.getBirthDate())); 
            statement.setString(4, patient.getHmoProvider());
            statement.setString(5, patient.getMedicalHistory());

            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    patient.setPatientID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error creating patient: " + e.getMessage());
        }
    }

    public Patient getPatientByID(int patientID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Patient WHERE patientID = ?")) {

            statement.setInt(1, patientID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Patient(
                            resultSet.getInt("patientID"),
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            resultSet.getDate("birthDate").toLocalDate(), 
                            resultSet.getString("HMO"),
                            resultSet.getString("medicalHistory")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting patient by ID: " + e.getMessage());
        }
        return null;
    }

    public void updatePatient(Patient patient) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Patient SET firstName = ?, lastName = ?, birthDate = ?, HMO = ?, " +
                             "medicalHistory = ? WHERE patientID = ?")) {

            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getLastName());
            statement.setDate(3, Date.valueOf(patient.getBirthDate())); 
            statement.setString(4, patient.getHmoProvider());
            statement.setString(5, patient.getMedicalHistory());
            statement.setInt(6, patient.getPatientID());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating patient: " + e.getMessage());
        }
    }

    public void deletePatient(int patientID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Patient WHERE patientID = ?")) {

            statement.setInt(1, patientID);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting patient: " + e.getMessage());
        }
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Patient");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                patients.add(new Patient(
                        resultSet.getInt("patientID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDate("birthDate").toLocalDate(), 
                        resultSet.getString("HMO"),
                        resultSet.getString("medicalHistory")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all patients: " + e.getMessage());
        }
        return patients;
    }
}