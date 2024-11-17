import java.time.LocalDate;
import java.util.ArrayList;

public class Patient {
    private int patientID;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String hmoProvider;
    private ArrayList<Treatment> medicalHistory;
    private int assignedDoctorID;

    public Patient (int patientID, String firstName, String lastName, LocalDate birthdate, String hmoProvider, int assignedDoctorID) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.hmoProvider = hmoProvider;
        this.medicalHistory = new ArrayList<Treatment>();
        this.assignedDoctorID = assignedDoctorID;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getHmoProvider() {
        return hmoProvider;
    }

    public void setHmoProvider(String hmoProvider) {
        this.hmoProvider = hmoProvider;
    }

    public ArrayList<Treatment> getMedicalHistory() {
        return medicalHistory;
    }

    public void addTreatment(Treatment treatment) {
        medicalHistory.add(treatment);
    }

    public int getAssignedDoctorID() {
        return assignedDoctorID;
    }

    public void setAssignedDoctorID(int assignedDoctorID) {
        this.assignedDoctorID = assignedDoctorID;
    }

}