import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ERProductivityReport {

    private RoomDAO roomDAO;

    public ERProductivityReport() {
        roomDAO = new RoomDAO();
    }

    public void generateReport(int month, int year) {
        try {
            List<Room> rooms = roomDAO.getAllRooms();
            System.out.println("ER Productivity Report");
            System.out.println("Month: " + month);
            System.out.println("Year: " + year);
            System.out.println();
            System.out.println("Room ID  | Total Admissions ");
            System.out.println("----------------------------");

            for (Room room : rooms) {
                int totalAdmissions = 0;

                try (Connection connection = HospitalManagementDB.getConnection();
                     PreparedStatement statement = connection.prepareStatement(
                             "SELECT COUNT(*) FROM Admission " +
                                     "WHERE roomID = ? AND admissionType = ? AND MONTH(admissionDate) = ? AND YEAR(admissionDate) = ?")) {

                    statement.setInt(1, room.getRoomID());
                    statement.setString(2, "ER");
                    statement.setInt(3, month);
                    statement.setInt(4, year);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            totalAdmissions = resultSet.getInt(1);
                        }
                    }
                } catch (SQLException e) {
                    System.err.println("Error retrieving admission data: " + e.getMessage());
                }

                System.out.printf("%-8d | %-15d\n", room.getRoomID(), totalAdmissions);
            }

        } catch (Exception e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }
}
