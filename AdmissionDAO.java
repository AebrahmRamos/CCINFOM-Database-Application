/**
 * The AdmissionDAO class provides CRUD operations for managing admissions in the hospital management system.
 * It includes methods to create, retrieve, update, and delete admissions, as well as retrieve all admissions.
 * 
 * Methods:
 * - createAdmission(Admission admission): Inserts a new admission record into the database.
 * - getAdmissionById(int admissionId): Retrieves an admission record by its ID.
 * - updateAdmission(Admission admission): Updates an existing admission record in the database.
 * - deleteAdmission(int admissionId): Deletes an admission record by its ID, ensuring no pending bills exist.
 * - getAllAdmissions(): Retrieves all admission records from the database.
 * 
 * Each method handles SQL exceptions and ensures database resources are properly closed.
 */

 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class AdmissionDAO {

    public void createAdmission(Admission admission) {
        try (Connection connection = HospitalManagementDB.getConnection(); // Connection opened here
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Admission (patientId, roomId, doctorId, admissionDate, admissionType, status) " +
                             "VALUES (?, ?, ?, ?, ?, ?)")) {

            statement.setInt(1, admission.getPatientID());
            statement.setInt(2, admission.getRoomID());
            statement.setInt(3, admission.getDoctorID()); // Setting doctorId
            statement.setObject(4, admission.getAdmissionDate());
            statement.setString(5, admission.getAdmissionType());
            statement.setString(6, admission.getStatus());

            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    admission.setAdmissionID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error creating admission: " + e.getMessage());
        } // Connection automatically closed here
    }

    public Admission getAdmissionById(int admissionId) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM Admission WHERE admissionId = ?")) {

            statement.setInt(1, admissionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Admission(
                            resultSet.getInt("admissionId"),
                            resultSet.getInt("patientId"),
                            resultSet.getInt("roomId"),
                            resultSet.getInt("doctorId"), // Retrieving doctorId
                            resultSet.getObject("admissionDate", LocalDate.class),
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

            statement.setInt(1, admission.getPatientID());
            statement.setInt(2, admission.getRoomID());
            statement.setInt(3, admission.getDoctorID()); // Updating doctorId
            statement.setObject(4, admission.getAdmissionDate());
            statement.setString(5, admission.getAdmissionType());
            statement.setString(6, admission.getStatus());
            statement.setInt(7, admission.getAdmissionID());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating admission: " + e.getMessage());
        }
    }

    public void deleteAdmission(int admissionId) {
        try (Connection connection = HospitalManagementDB.getConnection()) {
            // 1. Delete related bills with paymentStatus = 'Paid'
            try (PreparedStatement billStatement = connection.prepareStatement(
                    "DELETE FROM Bill WHERE admissionId = ? AND paymentStatus = 'Paid'")) {
    
                billStatement.setInt(1, admissionId);
                billStatement.executeUpdate();
            }
    
            // 2. Check for pending bills
            try (PreparedStatement checkStatement = connection.prepareStatement(
                    "SELECT COUNT(*) FROM Bill WHERE admissionId = ? AND paymentStatus != 'Paid'")) {
    
                checkStatement.setInt(1, admissionId);
                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) > 0) {
                        System.err.println("Error: Cannot delete admission with pending bills.");
                        return; // Don't proceed with deletion
                    }
                }
            }
    
            // 3. Delete the admission (if no pending bills)
            try (PreparedStatement admissionStatement = connection.prepareStatement(
                    "DELETE FROM Admission WHERE admissionId = ?")) {
    
                admissionStatement.setInt(1, admissionId);
                admissionStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error deleting admission: " + e.getMessage());
        }
    }

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
                        resultSet.getInt("doctorId"), // Retrieving doctorId
                        resultSet.getObject("admissionDate", LocalDate.class),
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