/*
 * Handles both ER Out-Patient and In-Patient admissions.
 * Validating patient information.
 * Checking room availability (for in-patients).
 * Assigning doctors (potentially based on specialization or availability).
 * Creating the admission record.
 * Updating patient status.
 * Potentially generating an initial bill.
 */

 import java.time.LocalDate;
 import java.util.List;

public class AdmissionService {
    private AdmissionDAO admissionDAO;
    private RoomDAO roomDAO;
    private PatientDAO patientDAO;
    // private DoctorDAO doctorDAO;

    public AdmissionService() {
        admissionDAO = new AdmissionDAO();
        roomDAO = new RoomDAO();
        patientDAO = new PatientDAO();
        // doctorDAO = new DoctorDAO();
    }

    public void admitPatient(int patientID, String roomType, int doctorID, LocalDate admissionDate, String admissionType, String status) {
        if (!isValidPatient(patientID)) {
            System.out.println("Invalid patient ID.");
            return;
        }

        if (!isValidRoomType(roomType)) {
            System.out.println("Invalid room type.");
            return;
        }

        Room room = null;
        if (admissionType.equalsIgnoreCase("in-patient")) {
            room = findAvailableRoom(roomType);
            if(room == null) {
                System.out.println("No available " + roomType + " room");
                return;
            }
        }

        Admission admission = new Admission(
            1,
            patientID,
            room != null ? room.getRoomID() : null, 
            doctorID,
            admissionDate,
            admissionType,
            status
        );
        admissionDAO.createAdmission(admission);

        if(room != null) {
            room.setAvailable(false);
            roomDAO.updateRoom(room);
        }

        System.out.println("Patient admitted successfully with the Admission ID: " + admission.getAdmissionID());
    }


    // Helper methods
    private boolean isValidPatient(int patientID) {
        return patientDAO.getPatientByID(patientID) != null;
    }

    private boolean isValidRoomType(String roomType) {
        List<String> roomTypes = List.of("Private", "Semi-Private", "Ward", "ICU");
        return roomTypes.contains(roomType);
    }

    private Room findAvailableRoom(String roomType){
        List<Room> rooms = roomDAO.getAllRooms();
        for (Room room : rooms) {
            if (room.getRoomType().equals(roomType) && room.isAvailable()) {
                return room;
            }
        }
        return null;
    }
}
