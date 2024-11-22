import java.time.LocalDateTime;

public class LabRequest {
    private int labRequestID;
    private int patientID;
    private int doctorID;
    private int laboratoryID;
    private LocalDateTime labRequestDate;
    private double cost;

    public LabRequest(int labRequestID, int patientID, int doctorID, int laboratoryID, LocalDateTime labRequestDate, double cost) {
        this.labRequestID = labRequestID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.laboratoryID = laboratoryID;
        this.labRequestDate = labRequestDate;
        this.cost = cost;
    }

    public int getLabRequestID() {
        return labRequestID;
    }

    public void setLabRequestID(int labRequestID) {
        this.labRequestID = labRequestID;
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

    public int getLaboratoryID() {
        return laboratoryID;
    }

    public void setLaboratoryID(int laboratoryID) {
        this.laboratoryID = laboratoryID;
    }

    public LocalDateTime getLabRequestDate() {
        return labRequestDate;
    }

    public void setLabRequestDate(LocalDateTime labRequestDate) {
        this.labRequestDate = labRequestDate;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}