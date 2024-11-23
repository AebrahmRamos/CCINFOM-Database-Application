import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class LaboratoryDAO {
    

    public void createLaboratory(Laboratory laboratory) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Laboratory (name, contactNumber) VALUES (?, ?)", 
                     Statement.RETURN_GENERATED_KEYS)) { 

            statement.setString(1, laboratory.getLaboratoryName());
            statement.setString(2, laboratory.getContactNumber());

            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    laboratory.setLaboratoryID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error creating laboratory: " + e.getMessage());
        }
    }

    public Laboratory getLaboratoryByID(int laboratoryID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Laboratory WHERE laboratoryID = ?")) {

            statement.setInt(1, laboratoryID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Laboratory(
                            resultSet.getInt("laboratoryID"),
                            resultSet.getString("name"),
                            resultSet.getString("contactNumber")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting laboratory by ID: " + e.getMessage());
        }
        return null;
    }

    public void updateLaboratory(Laboratory laboratory) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Laboratory SET name = ?, contactNumber = ? WHERE laboratoryID = ?")) {

            statement.setString(1, laboratory.getLaboratoryName());
            statement.setString(2, laboratory.getContactNumber());
            statement.setInt(3, laboratory.getLaboratoryID());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating laboratory: " + e.getMessage());
        }
    }

    public void deleteLaboratory(int laboratoryID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Laboratory WHERE laboratoryID = ?")) {

            statement.setInt(1, laboratoryID);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting laboratory: " + e.getMessage());
        }
    }

    public List<Laboratory> getAllLaboratories() {
        List<Laboratory> laboratories = new ArrayList<>();
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Laboratory");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                laboratories.add(new Laboratory(
                        resultSet.getInt("laboratoryID"),
                        resultSet.getString("name"),
                        resultSet.getString("contactNumber")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all laboratories: " + e.getMessage());
        }
        return laboratories;
    }
}