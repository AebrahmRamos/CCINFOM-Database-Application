import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class BillDAO {

    public void createBill(Bill bill) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Bill (admissionID, patientID, billDate, totalAmount, paymentStatus, paymentMethod) " +
                             "VALUES (?, ?, ?, ?, ?, ?)")) {

            statement.setInt(1, bill.getAdmissionID());
            statement.setInt(2, bill.getPatientID());
            statement.setTimestamp(3, Timestamp.valueOf(bill.getBillDate().atStartOfDay()));
            statement.setBigDecimal(4, BigDecimal.valueOf(bill.getTotalAmount()));
            statement.setString(5, bill.getPaymentStatus());
            statement.setString(6, bill.getPaymentMethod());

            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    bill.setBillID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error creating bill: " + e.getMessage());
        }
    }

    public Bill getBillByID(int billID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Bill WHERE billID = ?")) {

            statement.setInt(1, billID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Bill(
                        resultSet.getInt("billID"),
                        resultSet.getInt("admissionID"),
                        resultSet.getInt("patientID"),
                        resultSet.getTimestamp("billDate").toLocalDateTime().toLocalDate(),
                        resultSet.getDouble("totalAmount"),
                        resultSet.getString("paymentStatus"),
                        resultSet.getString("paymentMethod")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting bill by ID: " + e.getMessage());
        }
        return null;
    }

    public void updateBill(Bill bill) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Bill SET admissionID = ?, patientID = ?, billDate = ?, totalAmount = ?, " +
                             "paymentStatus = ?, paymentMethod = ? WHERE billID = ?")) {
    
            statement.setInt(1, bill.getAdmissionID());
            statement.setInt(2, bill.getPatientID());
            statement.setTimestamp(3, Timestamp.valueOf(bill.getBillDate().atStartOfDay()));
            statement.setDouble(4, bill.getTotalAmount());
            statement.setString(5, bill.getPaymentStatus());
            statement.setString(6, bill.getPaymentMethod());
            statement.setInt(7, bill.getBillID());
    
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating bill: " + e.getMessage());
        }
    }

    public void deleteBill(int billID) {
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Bill WHERE billID = ?")) {

            statement.setInt(1, billID);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting bill: " + e.getMessage());
        }
    }

    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        try (Connection connection = HospitalManagementDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Bill");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                bills.add(new Bill(
                    resultSet.getInt("billID"),
                    resultSet.getInt("admissionID"),
                    resultSet.getInt("patientID"),
                    resultSet.getTimestamp("billDate").toLocalDateTime().toLocalDate(),
                    resultSet.getDouble("totalAmount"),
                    resultSet.getString("paymentStatus"),
                    resultSet.getString("paymentMethod")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all bills: " + e.getMessage());
        }
        return bills;
    }
}
