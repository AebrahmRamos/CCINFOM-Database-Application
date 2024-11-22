import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Driver {

    private static final String INVALID_COMMAND_FORMAT = "Invalid command format.";

    private PatientDAO patientDAO;
    private DoctorDAO doctorDAO;
    private RoomDAO roomDAO;
    private LaboratoryDAO laboratoryDAO;
    private AdmissionService admissionService;
    private TreatmentService treatmentService;
    private LabRequestService labRequestService;
    private DischargeService dischargeService;
    private LaboratoryProductivityReport labReport;
    // ... (Add other service/report instances as needed)

    public Driver() {
        patientDAO = new PatientDAO();
        doctorDAO = new DoctorDAO();
        roomDAO = new RoomDAO();
        laboratoryDAO = new LaboratoryDAO();
        admissionService = new AdmissionService();
        treatmentService = new TreatmentService();
        labRequestService = new LabRequestService();
        dischargeService = new DischargeService();
        labReport = new LaboratoryProductivityReport();
        // ADD MISSING DAO'S
    }

    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            try {
                processCommand(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers where required.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please check the data types.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private void processCommand(String input) {
        String[] parts = input.split(" ");
        String command = parts[0];

        switch (command.toLowerCase()) {
            case "create":
                handleCreateCommand(parts);
                break;
            case "read":
                handleReadCommand(parts);
                break;
            case "update":
                // handleUpdateCommand(parts);
                break;
            case "delete":
                // handleDeleteCommand(parts);
                break;
            case "admit":
                // handleAdmitCommand(parts);
                break;
            case "treat":
                // handleTreatCommand(parts);
                break;
            case "request":
                // handleRequestCommand(parts);
                break;
            case "discharge":
                handleDischargeCommand(parts);
                break;
            case "generate":
                handleGenerateCommand(parts);
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                System.out.println("Invalid command.");
        }
    }

// HELPERS

// COMMAND HANDLERS
    private void handleCreateCommand(String[] parts) {
        if (parts.length < 2) {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: create <entity> <data>");
            return;
        }

        String entity = parts[1];
        switch (entity.toLowerCase()) {
            case "patient":
                System.out.println("");
                createPatient(parts);
                break;

            case "doctor":
                createDoctor(parts);
                break;

            case "room":
                createRoom(parts);
                break;

            case "laboratory":
                createLaboratory(parts);
                break;
                
            default:
                System.out.println("Invalid entity type.");
        }
    }

    private void handleDischargeCommand(String[] parts) {
        if (parts.length == 2) {
            try {
                int admissionID = Integer.parseInt(parts[1]);
                dischargeService.dischargePatient(admissionID);
            } catch (NumberFormatException e) {
                System.out.println("Invalid admission ID. Please enter a number.");
            }
        } else {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: discharge <admissionID>");
        }
    }
    
    private void handleGenerateCommand(String[] parts) {
        if (parts.length < 4 || !parts[1].equalsIgnoreCase("report")) {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: generate report <report_type> <arguments>");
            return;
        }
    
        String reportType = parts[2];
        try {
            switch (reportType.toLowerCase()) {
                case "disease-activity":
                    if (parts.length == 5) {
                        int month = Integer.parseInt(parts[3]);
                        int year = Integer.parseInt(parts[4]);
                        // Create a DiseaseActivityReport class

                        // DiseaseActivityReport diseaseReport = new DiseaseActivityReport(); 
                        // diseaseReport.generateReport(month, year);
                    } else {
                        System.out.println(INVALID_COMMAND_FORMAT + " Usage: generate report disease-activity <month> <year>");
                    }
                    break;
    
                case "doctor-productivity":
                    if (parts.length == 5) {
                        int month = Integer.parseInt(parts[3]);
                        int year = Integer.parseInt(parts[4]);
                        // Create a DoctorProducivityReport class

                        // DoctorProductivityReport doctorReport = new DoctorProductivityReport(); 
                        // doctorReport.generateReport(month, year);
                    } else {
                        System.out.println(INVALID_COMMAND_FORMAT + " Usage: generate report doctor-productivity <month> <year>");
                    }
                    break;
    
                case "er-productivity":
                    if (parts.length == 5) {
                        int month = Integer.parseInt(parts[3]);
                        int year = Integer.parseInt(parts[4]);
                        // Create ERProducivityReport class

                        // ERProductivityReport erReport = new ERProductivityReport(); 
                        // erReport.generateReport(month, year);
                    } else {
                        System.out.println(INVALID_COMMAND_FORMAT + " Usage: generate report er-productivity <month> <year>");
                    }
                    break;
    
                case "laboratory":
                    if (parts.length == 5) {
                        int month = Integer.parseInt(parts[3]);
                        int year = Integer.parseInt(parts[4]);
                        labReport.generateReport(month, year);
                    } else {
                        System.out.println(INVALID_COMMAND_FORMAT + " Usage: generate report laboratory <month> <year>");
                    }
                    break;
    
                default:
                    System.out.println("Invalid report type.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid month or year. Please enter numbers.");
        }
    }

    private void handleReadCommand(String[] parts) {
        if (parts.length < 2) {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: read <entity>");
            return;
        }
    
        String entity = parts[1];
        switch (entity.toLowerCase()) {
            case "patient":
                readAllPatients();
                break;
            case "doctor":
                readAllDoctors();
                break;
            case "room":
                readAllRooms();
                break;
            case "laboratory":
                readAllLaboratories();
                break;
            default:
                System.out.println("Invalid entity type.");
        }
    }
    
    private void readAllPatients() {
        List<Patient> patients = patientDAO.getAllPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            for (Patient patient : patients) {
                System.out.println(patient);
            }
        }
    }
    
    private void readAllDoctors() {
        List<Doctor> doctors = doctorDAO.getAllDoctors();
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
        } else {
            for (Doctor doctor : doctors) {
                System.out.println(doctor);
            }
        }
    }
    
    private void readAllRooms() {
        List<Room> rooms = roomDAO.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            for (Room room : rooms) {
                System.out.println(room.getRoomID());
            }
        }
    }
    
    private void readAllLaboratories() {
        List<Laboratory> laboratories = laboratoryDAO.getAllLaboratories();
        if (laboratories.isEmpty()) {
            System.out.println("No laboratories found.");
        } else {
            for (Laboratory laboratory : laboratories) {
                System.out.println(laboratory.getLaboratoryName());
            }
        }
    }


    // create commands
    private void createPatient(String[] parts) {
        if (parts.length != 7) { // Adjusted for 6 data fields
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: create patient <firstName> <lastName> <birthDate(YYYY-MM-DD)> <HMO> <medicalHistory>");
            return;
        }
    
        String firstName = parts[2];
        String lastName = parts[3];
        LocalDate birthDate = LocalDate.parse(parts[4]);
        String HMO = parts[5];
        String medicalHistory = parts[6]; 
    
        Patient patient = new Patient(1, firstName, lastName, birthDate, HMO, medicalHistory);
        patientDAO.createPatient(patient);
        System.out.println("Patient created successfully");
    }
    
    private void createDoctor(String[] parts) {
        if (parts.length != 6) {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: create doctor <firstName> <lastName> <specialization> <department>");
            return;
        }
    
        String firstName = parts[2];
        String lastName = parts[3];
        String specialization = parts[4];
        String department = parts[5];
    
        Doctor doctor = new Doctor(1, firstName, lastName, specialization, department);
        doctorDAO.createDoctor(doctor);
        System.out.println("Doctor created successfully");
    }
    
    private void createRoom(String[] parts) {
        if (parts.length != 6) {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: create room <roomType> <isAvailable(true/false)> <lastMaintenance(YYYY-MM-DD)> <cost>");
            return;
        }
    
        String roomType = parts[2];
        boolean isAvailable = Boolean.parseBoolean(parts[3]);
        LocalDate lastMaintenance = LocalDate.parse(parts[4]);
        double cost = Double.parseDouble(parts[5]);
    
        Room room = new Room(1, roomType, isAvailable, lastMaintenance, cost);
        roomDAO.createRoom(room);
        System.out.println("Room created successfully");
    }
    
    private void createLaboratory(String[] parts) {
        if (parts.length != 4) {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: create laboratory <name> <contactNumber>");
            return;
        }
    
        String name = parts[2];
        String contactNumber = parts[3];
    
        Laboratory laboratory = new Laboratory(1, name, contactNumber);
        laboratoryDAO.createLaboratory(laboratory);
        System.out.println("Laboratory created successfully with id " + laboratory.getLaboratoryID());
    }
    
}