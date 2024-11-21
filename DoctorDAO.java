import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public void createDoctor(Doctor doctor) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Doctor (firstName, lastName, specialization, department) " +
                             "VALUES (?, ?, ?, ?)")) {

            statement.setString(1, doctor.getFirstName());
            statement.setString(2, doctor.getLastName());
            statement.setString(3, doctor.getSpecialization());
            statement.setString(4, doctor.getDepartment());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating doctor: " + e.getMessage());
        }
    }

    public Doctor getDoctorByID(int doctorID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Doctor WHERE doctorID = ?")) {

            statement.setInt(1, doctorID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Doctor(
                            resultSet.getInt("doctorID"),
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            resultSet.getString("specialization"),
                            resultSet.getString("department")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting doctor by ID: " + e.getMessage());
        }
        return null;
    }

    public void updateDoctor(Doctor doctor) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Doctor SET firstName = ?, lastName = ?, specialization = ?, department = ? " +
                             "WHERE doctorID = ?")) {

            statement.setString(1, doctor.getFirstName());
            statement.setString(2, doctor.getLastName());
            statement.setString(3, doctor.getSpecialization());
            statement.setString(4, doctor.getDepartment());
            statement.setInt(5, doctor.getDoctorID());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating doctor: " + e.getMessage());
        }
    }

    public void deleteDoctor(int doctorID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Doctor WHERE doctorID = ?")) {

            statement.setInt(1, doctorID);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting doctor: " + e.getMessage());
        }
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Doctor");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                doctors.add(new Doctor(
                        resultSet.getInt("doctorID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("specialization"),
                        resultSet.getString("department")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all doctors: " + e.getMessage());
        }
        return doctors;
    }
}