public class HospitalRecords {
    private patientRecords patientRecords;
    private doctorRecords doctorRecords;
    private appointmentRecords appointmentRecords;
    private roomRecords roomRecords;

    public HospitalRecords() {
        this.patientRecords = new patientRecords();
        this.doctorRecords = new doctorRecords();
        this.appointmentRecords = new appointmentRecords();
        this.roomRecords = new roomRecords();
    }

    public patientRecords getPatientRecords() {
        return patientRecords;
    }

    public doctorRecords getDoctorRecords() {
        return doctorRecords;
    }

    public appointmentRecords getAppointmentRecords() {
        return appointmentRecords;
    }

    public roomRecords getRoomRecords() {
        return roomRecords;
    }
}