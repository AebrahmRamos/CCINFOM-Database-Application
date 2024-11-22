import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class LaboratoryProductivityReport {

    private LaboratoryDAO laboratoryDAO;
    private LabRequestDAO labRequestDAO;

    public LaboratoryProductivityReport() {
        laboratoryDAO = new LaboratoryDAO();
        labRequestDAO = new LabRequestDAO();
    }

    public void generateReport(int month, int year) {
        try {
            List<Laboratory> laboratories = laboratoryDAO.getAllLaboratories();

            System.out.println("Laboratory Productivity Report");
            System.out.println("Month: " + month);
            System.out.println("Year: " + year);
            System.out.println();
            System.out.println("Laboratory | Total Tests Performed | Total Revenue Generated");
            System.out.println("----------------------------------------------------------");

            int totalTests = 0;
            double totalRevenue = 0;

            for (Laboratory lab : laboratories) {
                int labTests = 0;
                double labRevenue = 0;

                try (Connection connection = HospitalManagementDB.getConnection();
                     PreparedStatement statement = connection.prepareStatement(
                             "SELECT COUNT(*), SUM(cost) FROM LabRequest " +
                                     "WHERE laboratoryID = ? AND MONTH(requestDate) = ? AND YEAR(requestDate) = ?")) {

                    statement.setInt(1, lab.getLaboratoryID());
                    statement.setInt(2, month);
                    statement.setInt(3, year);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            labTests = resultSet.getInt(1);
                            labRevenue = resultSet.getDouble(2);
                        }
                    }
                } catch (SQLException e) {
                    System.err.println("Error retrieving lab request data: " + e.getMessage());
                }

                System.out.printf("%-12s| %-21d | $%-20.2f%n", lab.getLaboratoryName(), labTests, labRevenue);

                totalTests += labTests;
                totalRevenue += labRevenue;
            }

            System.out.println("----------------------------------------------------------");
            System.out.printf("%-12s| %-21d | $%-20.2f%n", "Total", totalTests, totalRevenue);

        } catch (Exception e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }
}