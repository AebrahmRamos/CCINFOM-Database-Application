import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Billing {
    private int billingID;
    private int patientID;
    private String treatmentDetails;
    private double totalCost;
    private String paymentStatus;

    public Billing(int patientID, String treatmentDetails, double totalCost, String paymentStatus) {
        this.patientID = patientID;
        this.treatmentDetails = treatmentDetails;
        this.totalCost = totalCost;
        this.paymentStatus = paymentStatus;
    }

    public void generateBill(Connection connection) {
        String sql = "INSERT INTO Billing (patientID, treatmentDetails, totalCost, paymentStatus) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, this.patientID);
            pstmt.setString(2, this.treatmentDetails);
            pstmt.setDouble(3, this.totalCost);
            pstmt.setString(4, this.paymentStatus);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
