import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DoctorProductivityReport {

    private DoctorDAO doctorDAO;

    public DoctorProductivityReport() {
        doctorDAO = new DoctorDAO();
    }

    public void generateReport(int month, int year) {
        try {
            List<Doctor> doctors = doctorDAO.getAllDoctors();

            System.out.println("Doctor Productivity Report");
            System.out.println("Month: " + month);
            System.out.println("Year: " + year);
            System.out.println();
            System.out.println("Doctor ID | Doctor Name         | Total Admissions");
            System.out.println("----------------------------------------------");

            for (Doctor doctor : doctors) {
                int totalAdmissions = 0;

                try (Connection connection = HospitalManagementDB.getConnection();
                     PreparedStatement statement = connection.prepareStatement(
                             "SELECT COUNT(*) FROM Admission " +
                                     "WHERE doctorID = ? AND MONTH(admissionDate) = ? AND YEAR(admissionDate) = ?")) {

                    statement.setInt(1, doctor.getDoctorID());
                    statement.setInt(2, month);
                    statement.setInt(3, year);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            totalAdmissions = resultSet.getInt(1);
                        }
                    }
                } catch (SQLException e) {
                    System.err.println("Error retrieving admission data: " + e.getMessage());
                }

                System.out.printf("%-10d | %-20s | %-15d%n", doctor.getDoctorID(), doctor.getFirstName() + " " + doctor.getLastName(), totalAdmissions);
            }

        } catch (Exception e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }
}
