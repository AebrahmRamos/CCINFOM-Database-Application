import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LabStaffDAO {

    public void createLabStaff(LabStaff labStaff) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO LabStaff (laboratoryID, name, role, shift) " +
                             "VALUES (?, ?, ?, ?)")) {

            statement.setInt(1, labStaff.getLaboratoryID());
            statement.setString(2, labStaff.getName());
            statement.setString(3, labStaff.getRole());
            statement.setString(4, labStaff.getShift());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating lab staff: " + e.getMessage());
        }
    }

    public LabStaff getLabStaffByID(int staffID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM LabStaff WHERE staffID = ?")) {

            statement.setInt(1, staffID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new LabStaff(
                            resultSet.getInt("staffID"),
                            resultSet.getInt("laboratoryID"),
                            resultSet.getString("name"),
                            resultSet.getString("role"),
                            resultSet.getString("shift")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting lab staff by ID: " + e.getMessage());
        }
        return null;
    }

    public void updateLabStaff(LabStaff labStaff) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE LabStaff SET laboratoryID = ?, name = ?, role = ?, shift = ? " +
                             "WHERE staffID = ?")) {

            statement.setInt(1, labStaff.getLaboratoryID());
            statement.setString(2, labStaff.getName());
            statement.setString(3, labStaff.getRole());
            statement.setString(4, labStaff.getShift());
            statement.setInt(5, labStaff.getStaffID());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating lab staff: " + e.getMessage());
        }
    }

    public void deleteLabStaff(int staffID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM LabStaff WHERE staffID = ?")) {

            statement.setInt(1, staffID);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting lab staff: " + e.getMessage());
        }
    }

    public List<LabStaff> getAllLabStaff() {
        List<LabStaff> labStaffMembers = new ArrayList<>();
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM LabStaff");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                labStaffMembers.add(new LabStaff(
                        resultSet.getInt("staffID"),
                        resultSet.getInt("laboratoryID"),
                        resultSet.getString("name"),
                        resultSet.getString("role"),
                        resultSet.getString("shift")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all lab staff: " + e.getMessage());
        }
        return labStaffMembers;
    }
}