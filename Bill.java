import java.time.LocalDate;
public class Bill {
    private int billID;
    private int admissionID;
    private int patientID;
    private LocalDate billDate;
    private double totalAmount;
    private String paymentStatus;
    private String paymentMethod;

    public Bill(int billID, int admissionID, int patientID, LocalDate billDate, double totalAmount, String paymentStatus, String paymentMethod) {
        this.billID = billID;
        this.admissionID = admissionID;
        this.patientID = patientID;
        this.billDate = billDate;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getAdmissionID() {
        return admissionID;
    }

    public void setAdmissionID(int admissionID) {
        this.admissionID = admissionID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

}
