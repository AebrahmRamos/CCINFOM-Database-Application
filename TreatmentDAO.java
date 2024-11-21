import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TreatmentDAO {

    public void createTreatment(Treatment treatment) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Treatment (patientID, doctorID, roomID, admissionDate, treatmentType, description, cost) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            statement.setInt(1, treatment.getPatientID());
            statement.setInt(2, treatment.getDoctorID());
            statement.setInt(3, treatment.getRoomID());
            statement.setTimestamp(4, Timestamp.valueOf(treatment.getAdmissionDate().atStartOfDay()));
            statement.setString(5, treatment.getTreatmentType());
            statement.setString(6, treatment.getDescription());
            statement.setDouble(7, treatment.getCost());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating treatment: " + e.getMessage());
        }
    }

    public Treatment getTreatmentByID(int treatmentID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Treatment WHERE treatmentID = ?")) {

            statement.setInt(1, treatmentID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Treatment(
                            resultSet.getInt("treatmentID"),
                            resultSet.getInt("patientID"),
                            resultSet.getInt("doctorID"),
                            resultSet.getInt("roomID"),
                            resultSet.getTimestamp("admissionDate").toLocalDateTime().toLocalDate(),
                            resultSet.getString("treatmentType"),
                            resultSet.getString("description"),
                            resultSet.getDouble("cost")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting treatment by ID: " + e.getMessage());
        }
        return null;
    }

    public void updateTreatment(Treatment treatment) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Treatment SET patientID = ?, doctorID = ?, roomID = ?, admissionDate = ?, " +
                             "treatmentType = ?, description = ?, cost = ? WHERE treatmentID = ?")) {

            statement.setInt(1, treatment.getPatientID());
            statement.setInt(2, treatment.getDoctorID());
            statement.setInt(3, treatment.getRoomID());
            statement.setTimestamp(4, Timestamp.valueOf(treatment.getAdmissionDate().atStartOfDay()));
            statement.setString(5, treatment.getTreatmentType());
            statement.setString(6, treatment.getDescription());
            statement.setDouble(7, treatment.getCost());
            statement.setInt(8, treatment.getTreatmentID());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating treatment: " + e.getMessage());
        }
    }

    public void deleteTreatment(int treatmentID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Treatment WHERE treatmentID = ?")) {

            statement.setInt(1, treatmentID);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting treatment: " + e.getMessage());
        }
    }

    public List<Treatment> getAllTreatments() {
        List<Treatment> treatments = new ArrayList<>();
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Treatment");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                treatments.add(new Treatment(
                        resultSet.getInt("treatmentID"),
                        resultSet.getInt("patientID"),
                        resultSet.getInt("doctorID"),
                        resultSet.getInt("roomID"),
                        resultSet.getTimestamp("admissionDate").toLocalDateTime().toLocalDate(),
                        resultSet.getString("treatmentType"),
                        resultSet.getString("description"),
                        resultSet.getDouble("cost")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all treatments: " + e.getMessage());
        }
        return treatments;
    }
}