public class HospitalRecords {
    private PatientRecords patientRecords;
    private DoctorRecords doctorRecords;
    private AppointmentRecords appointmentRecords;
    private RoomRecords roomRecords;

    public HospitalRecords() {
        this.patientRecords = new PatientRecords();
        this.doctorRecords = new DoctorRecords();
        this.appointmentRecords = new AppointmentRecords();
        this.roomRecords = new RoomRecords();
    }

    public PatientRecords getPatientRecords() {
        return patientRecords;
    }

    public DoctorRecords getDoctorRecords() {
        return doctorRecords;
    }

    public AppointmentRecords getAppointmentRecords() {
        return appointmentRecords;
    }

    public RoomRecords getRoomRecords() {
        return roomRecords;
    }
}