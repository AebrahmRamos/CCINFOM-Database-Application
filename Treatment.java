import java.time.LocalDate;
public class Treatment {
    private int treatmentID;
    private int patientID;
    private int doctorID;
    private int roomID;
    private LocalDate admissionDate;
    private String treatmentType;
    private String description;
    private Double cost;

    public Treatment(int treatmentID, int patientID, int doctorID, int roomID, LocalDate admissionDate, String treatmentType, String description, Double cost) {
        this.treatmentID = treatmentID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.roomID = roomID;
        this.admissionDate = admissionDate;
        this.treatmentType = treatmentType;
        this.description = description;
        this.cost = cost;
    }

    public int getTreatmentID() {
        return treatmentID;
    }

    public void setTreatmentID(int treatmentID) {
        this.treatmentID = treatmentID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }


}
