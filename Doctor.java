public class Doctor {
    private int doctorID;
    private String firstname;
    private String lastname;
    private String specialization;
    private String deparmtent;

    public Doctor(int doctorID, String firstname, String lastname, String specialization, String deparmtent) {
        this.doctorID = doctorID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.specialization = specialization;
        this.deparmtent = deparmtent;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getDepartment() {
        return deparmtent;
    }

    public void setDepartment(String deparmtent) {
        this.deparmtent = deparmtent;
    }

    @Override
    public String toString() {
        return "Doctor [doctorID=" + doctorID + ", firstname=" + firstname + ", lastname=" + lastname
                + ", specialization=" + specialization + ", deparmtent=" + deparmtent + "]";
    }

}