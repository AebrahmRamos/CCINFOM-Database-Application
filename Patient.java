import java.time.LocalDate;

public class Patient {
    private int patientID;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String hmoProvider;
    private String medicalHistory;

    public Patient(int patientID, String firstName, String lastName, LocalDate birthDate, String hmoProvider, String medicalHistory) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.hmoProvider = hmoProvider;
        this.medicalHistory = medicalHistory;
    }

    public int getPatientID() {
        return patientID;
    }
    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public String getHmoProvider() {
        return hmoProvider;
    }
    public void setHmoProvider(String hmoProvider) {
        this.hmoProvider = hmoProvider;
    }
    public String getMedicalHistory() {
        return medicalHistory;
    }
    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    @Override
    public String toString() {
        return "Patient [patientID=" + patientID + ", firstName=" + firstName + ", lastName=" + lastName
                + ", birthDate=" + birthDate + ", hmoProvider=" + hmoProvider + ", medicalHistory=" + medicalHistory
                + "]";
    }    
}
