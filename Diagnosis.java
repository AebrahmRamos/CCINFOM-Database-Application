import java.time.LocalDate;

public class Diagnosis {
    private int diagnosisID;
    private int diseaseID;
    private int admissionID;
    private String admissionType;
    private LocalDate diagnosisDate;
    private String severity;
    private String status;

    public Diagnosis(int diagnosisID, int diseaseID, int admissionID, String admissionType, LocalDate diagnosisDate, String severity, String status) {
        this.diagnosisID = diagnosisID;
        this.diseaseID = diseaseID;
        this.admissionID = admissionID;
        this.admissionType = admissionType;
        this.diagnosisDate = diagnosisDate;
        this.severity = severity;
        this.status = status;
    }

    public int getDiagnosisID() {
        return diagnosisID;
    }

    public void setDiagnosisID(int diagnosisID) {
        this.diagnosisID = diagnosisID;
    }

    public int getDiseaseID() {
        return diseaseID;
    }

    public void setDiseaseID(int diseaseID) {
        this.diseaseID = diseaseID;
    }

    public int getAdmissionID() {
        return admissionID;
    }

    public void setAdmissionID(int admissionID) {
        this.admissionID = admissionID;
    }

    public String getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public LocalDate getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(LocalDate diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
