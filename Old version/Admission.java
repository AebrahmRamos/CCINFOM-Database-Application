import java.time.LocalDate;

public class Admission {
    private int admissionID;
    private int patientID;
    private int roomID;
    private LocalDate admissionDate;
    private String admission_type;
    private String status;

    public Admission(int admissionID, int patientID, int roomID, LocalDate admissionDate, String admission_type, String status) {
        this.admissionID = admissionID;
        this.patientID = patientID;
        this.roomID = roomID;
        this.admissionDate = admissionDate;
        this.admission_type = admission_type;
        this.status = status;
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

    public String getAdmissionType() {
        return admission_type;
    }

    public void setAdmissionType(String admission_type) {
        this.admission_type = admission_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
