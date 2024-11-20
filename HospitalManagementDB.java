import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HospitalManagementDB {
    // javac -cp lib/mysql-connector-j-8.0.33.jar:. Driver.java
    // java -cp lib/mysql-connector-j-8.0.33.jar:. Driver   
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_management_database";
    private static final String USER = "root";
    private static final String PASSWORD = "Cci1nfompassword!";

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Load the MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver"); 

                // Establish the connection
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connected to the database!");
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("Error connecting to the database: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Error closing the database connection: " + e.getMessage());
            }
        }
    }
}

// Connection connection = null;
//         try {
//             Class.forName("com.mysql.cj.jdbc.Driver");

//             //Put your url, user, and password for your database
//             String url = "jdbc:mysql://localhost:3306/hospital_management_database";
//             String user = "root";
//             String password = "Cci1nfompassword!";
//             connection = DriverManager.getConnection(url, user, password);

//             System.out.println("Connecting database...");
//             System.out.println("Database connected!");

//             if (connection != null) {
//                 try (Statement statement = connection.createStatement()) {
//                     String query = "SELECT * FROM Patient";
//                     ResultSet resultSet = statement.executeQuery(query);
            
//                     while (resultSet.next()) {
//                         int patientId = resultSet.getInt("patientId");
//                         String firstName = resultSet.getString("firstName");
//                         String lastName = resultSet.getString("lastName");
//                         Date birthDate = resultSet.getDate("birthDate");
//                         String HMO = resultSet.getString("HMO");
//                         String medicalHistory = resultSet.getString("medicalHistory");
//                         // Add other columns as needed
//                         System.out.println("Patient ID: " + patientId + ", First Name: " + firstName + ", Last Name: " + lastName + ", Birth Date: " + birthDate + ", HMO: " + HMO + ", Medical History: " + medicalHistory);
//                     }
//                 } catch (SQLException e) {
//                     throw new RuntimeException("Error executing query!", e);
//                 }
//             }

//         } catch (ClassNotFoundException e) {
//             throw new RuntimeException("Cannot find the database driver!", e);
//         } catch (SQLException e) {
//             throw new RuntimeException("Cannot connect the database!", e);
//         } finally {
//             System.out.println("Closing the connection.");
//             if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
//         }