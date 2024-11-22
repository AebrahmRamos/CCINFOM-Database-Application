import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    public void createAppointment(Appointment appointment) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Appointment (patientId, doctorId, roomId, appointmentTime, status, appointmentType, priority) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            statement.setInt(1, appointment.getPatientID());
            statement.setInt(2, appointment.getDoctorID());
            statement.setInt(3, appointment.getRoomID());
            statement.setTimestamp(4, Timestamp.valueOf(appointment.getAppointmentTime()));
            statement.setString(5, appointment.getStatus());
            statement.setString(6, appointment.getAppointmentType());
            statement.setString(7, appointment.getPriority());

            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    appointment.setAppointmentID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error creating appointment: " + e.getMessage());
        }
    }

    public Appointment getAppointmentById(int appointmentId) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Appointment WHERE appointmentId = ?")) {

            statement.setInt(1, appointmentId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Appointment(
                            resultSet.getInt("appointmentId"),
                            resultSet.getInt("patientId"),
                            resultSet.getInt("doctorId"),
                            resultSet.getInt("roomId"),
                            resultSet.getTimestamp("appointmentTime").toLocalDateTime(),
                            resultSet.getString("status"),
                            resultSet.getString("appointmentType"),
                            resultSet.getString("priority")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting appointment by ID: " + e.getMessage());
        }
        return null;
    }

    public void updateAppointment(Appointment appointment) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Appointment SET patientId = ?, doctorId = ?, roomId = ?, appointmentTime = ?, " +
                             "status = ?, appointmentType = ?, priority = ? WHERE appointmentId = ?")) {

            statement.setInt(1, appointment.getPatientID());
            statement.setInt(2, appointment.getDoctorID());
            statement.setInt(3, appointment.getRoomID());
            statement.setTimestamp(4, Timestamp.valueOf(appointment.getAppointmentTime()));
            statement.setString(5, appointment.getStatus());
            statement.setString(6, appointment.getAppointmentType());
            statement.setString(7, appointment.getPriority());
            statement.setInt(8, appointment.getAppointmentID());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating appointment: " + e.getMessage());
        }
    }

    public void deleteAppointment(int appointmentId) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Appointment WHERE appointmentId = ?")) {

            statement.setInt(1, appointmentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting appointment: " + e.getMessage());
        }
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Appointment");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                appointments.add(new Appointment(
                        resultSet.getInt("appointmentId"),
                        resultSet.getInt("patientId"),
                        resultSet.getInt("doctorId"),
                        resultSet.getInt("roomId"),
                        resultSet.getTimestamp("appointmentTime").toLocalDateTime(),
                        resultSet.getString("status"),
                        resultSet.getString("appointmentType"),
                        resultSet.getString("priority")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all appointments: " + e.getMessage());
        }
        return appointments;
    }
}