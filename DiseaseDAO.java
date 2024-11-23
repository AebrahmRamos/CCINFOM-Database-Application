import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class DiseaseDAO {

    public void createDisease(Disease disease) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Disease (name, description) VALUES (?, ?)", 
                     Statement.RETURN_GENERATED_KEYS)) { 

            statement.setString(1, disease.getName());
            statement.setString(2, disease.getDescription());

            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    disease.setDiseaseID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error creating disease: " + e.getMessage());
        }
    }

    public Disease getDiseaseByID(int diseaseID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Disease WHERE diseaseID = ?")) {

            statement.setInt(1, diseaseID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Disease(
                            resultSet.getInt("diseaseID"),
                            resultSet.getString("name"),
                            resultSet.getString("category"),
                            resultSet.getString("icdCode"),
                            resultSet.getString("description")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting disease by ID: " + e.getMessage());
        }
        return null;
    }

    public void updateDisease(Disease disease) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Disease SET name = ?, description = ? WHERE diseaseID = ?")) {

            statement.setString(1, disease.getName());
            statement.setString(2, disease.getDescription());
            statement.setInt(3, disease.getDiseaseID());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating disease: " + e.getMessage());
        }
    }

    public void deleteDisease(int diseaseID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Disease WHERE diseaseID = ?")) {

            statement.setInt(1, diseaseID);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting disease: " + e.getMessage());
        }
    }

    public List<Disease> getAllDiseases() {
        List<Disease> diseases = new ArrayList<>();
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Disease");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                diseases.add(new Disease(
                        resultSet.getInt("diseaseID"),
                        resultSet.getString("name"),
                        resultSet.getString("category"),
                        resultSet.getString("icdCode"),
                        resultSet.getString("description")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all diseases: " + e.getMessage());
        }
        return diseases;
    }
}