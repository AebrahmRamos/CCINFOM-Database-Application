import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// javac -cp lib/mysql-connector-j-8.0.33.jar:. Driver.java
// java -cp lib/mysql-connector-j-8.0.33.jar:. Driver
public class Driver {

    private static final String INVALID_COMMAND_FORMAT = "Invalid command format.";

    private PatientDAO patientDAO;
    private DoctorDAO doctorDAO;
    private RoomDAO roomDAO;
    private LaboratoryDAO laboratoryDAO;
    private AdmissionDAO admissionDAO;
    private AppointmentDAO appointmentDAO;
    private BillDAO billDAO;
    private DiagnosisDAO diagnosisDAO;
    private DiseaseDAO diseaseDAO;
    private LabActivityDAO labActivityDAO;
    private LabRequestDAO labRequestDAO;
    private LabStaffDAO labStaffDAO;
    private MaintenanceDAO maintenanceDAO;
    private TreatmentDAO treatmentDAO;
    private AdmissionService admissionService;
    private TreatmentService treatmentService;
    private LabRequestService labRequestService;
    private DischargeService dischargeService;
    private LaboratoryProductivityReport labReport;


    

    
    public Driver() {
        patientDAO = new PatientDAO();
        doctorDAO = new DoctorDAO();
        roomDAO = new RoomDAO();
        laboratoryDAO = new LaboratoryDAO();
        admissionDAO = new AdmissionDAO();
        appointmentDAO = new AppointmentDAO();
        billDAO = new BillDAO();
        diagnosisDAO = new DiagnosisDAO();
        diseaseDAO = new DiseaseDAO();
        labActivityDAO = new LabActivityDAO();
        labRequestDAO = new LabRequestDAO();
        labStaffDAO = new LabStaffDAO();
        maintenanceDAO = new MaintenanceDAO();
        treatmentDAO = new TreatmentDAO();
        admissionService = new AdmissionService();
        treatmentService = new TreatmentService();
        labRequestService = new LabRequestService();
        dischargeService = new DischargeService();
        labReport = new LaboratoryProductivityReport();
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
                handleUpdateCommand(parts);
                break;
            case "delete":
                handleDeleteCommand(parts);
                break;
            case "admit":
                // handleAdmitCommand(parts);
                break;
            case "treat":
                handleTreatCommand(parts);
                break;
            case "request":
                handleRequestCommand(parts);
                break;
            case "discharge":
                handleDischargeCommand(parts);
                break;
            case "generate":
                handleGenerateCommand(parts);
                break;
            case "help":
                handleHelpCommand(parts);
                break;
            case "list":
                handleListCommand(parts);
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
private void handleUpdateCommand(String[] parts) {
    if (parts.length < 3) {
        System.out.println(INVALID_COMMAND_FORMAT + " Usage: update <entity> <id> <data>");
        return;
    }

    String entity = parts[1];
    int id;
    try {
        id = Integer.parseInt(parts[2]);
    } catch (NumberFormatException e) {
        System.out.println("Invalid ID. Please enter a number.");
        return;
    }

    switch (entity.toLowerCase()) {
        case "patient":
            updatePatient(parts);
            break;
        case "doctor":
            updateDoctor(parts);
            break;
        case "room":
            updateRoom(parts);
            break;
        case "laboratory":
            updateLaboratory(parts);
            break;
        case "admission":
            updateAdmission(parts);
            break;
        case "appointment":
            updateAppointment(parts);
            break;
        case "bill":
            updateBill(parts);
            break;
        case "diagnosis":
            updateDiagnosis(parts);
            break;
        case "disease":
            updateDisease(parts);
            break;
        case "labactivity":
            updateLabActivity(parts);
            break;
        case "labrequest":
            updateLabRequest(parts);
            break;
        case "labstaff":
            updateLabStaff(parts);
            break;
        case "maintenance":
            updateMaintenance(parts);
            break;
        case "treatment":
            updateTreatment(parts);
            break;
        default:
            System.out.println("Invalid entity type.");
    }
}

private void handleDeleteCommand(String[] parts) {
    if (parts.length != 3) {
        System.out.println(INVALID_COMMAND_FORMAT + " Usage: delete <entity> <id>");
        return;
    }

    String entity = parts[1];
    int id;
    try {
        id = Integer.parseInt(parts[2]);
    } catch (NumberFormatException e) {
        System.out.println("Invalid ID. Please enter a number.");
        return;
    }

    switch (entity.toLowerCase()) {
        case "patient":
            deletePatient(id);
            break;
        case "doctor":
            deleteDoctor(id);
            break;
        case "room":
            deleteRoom(id);
            break;
        case "laboratory":
            deleteLaboratory(id);
            break;
        case "admission":
            deleteAdmission(id);
            break;
        case "appointment":
            deleteAppointment(id);
            break;
        case "bill":
            deleteBill(id);
            break;
        case "diagnosis":
            deleteDiagnosis(id);
            break;
        case "disease":
            deleteDisease(id);
            break;
        case "labactivity":
            deleteLabActivity(id);
            break;
        case "labrequest":
            deleteLabRequest(id);
            break;
        case "labstaff":
            deleteLabStaff(id);
            break;
        case "maintenance":
            deleteMaintenance(id);
            break;
        case "treatment":
            deleteTreatment(id);
            break;
        default:
            System.out.println("Invalid entity type.");
    }
}

private void deletePatient(int patientID) {
    patientDAO.deletePatient(patientID);
    System.out.println("Patient deleted successfully.");
}

private void deleteDoctor(int doctorID) {
    doctorDAO.deleteDoctor(doctorID);
    System.out.println("Doctor deleted successfully.");
}

private void deleteRoom(int roomID) {
    roomDAO.deleteRoom(roomID);
    System.out.println("Room deleted successfully.");
}

private void deleteLaboratory(int laboratoryID) {
    laboratoryDAO.deleteLaboratory(laboratoryID);
    System.out.println("Laboratory deleted successfully.");
}

private void deleteAdmission(int admissionID) {
    admissionDAO.deleteAdmission(admissionID);
    System.out.println("Admission deleted successfully.");
}

private void deleteAppointment(int appointmentID) {
    appointmentDAO.deleteAppointment(appointmentID);
    System.out.println("Appointment deleted successfully.");
}

private void deleteBill(int billID) {
    billDAO.deleteBill(billID);
    System.out.println("Bill deleted successfully.");
}

private void deleteDiagnosis(int diagnosisID) {
    diagnosisDAO.deleteDiagnosis(diagnosisID);
    System.out.println("Diagnosis deleted successfully.");
}

private void deleteDisease(int diseaseID) {
    diseaseDAO.deleteDisease(diseaseID);
    System.out.println("Disease deleted successfully.");
}

private void deleteLabActivity(int labActivityID) {
    labActivityDAO.deleteLabActivity(labActivityID);
    System.out.println("Lab Activity deleted successfully.");
}

private void deleteLabRequest(int labRequestID) {
    labRequestDAO.deleteLabRequest(labRequestID);
    System.out.println("Lab Request deleted successfully.");
}

private void deleteLabStaff(int labStaffID) {
    labStaffDAO.deleteLabStaff(labStaffID);
    System.out.println("Lab Staff deleted successfully.");
}

private void deleteMaintenance(int maintenanceID) {
    maintenanceDAO.deleteMaintenance(maintenanceID);
    System.out.println("Maintenance deleted successfully.");
}

private void deleteTreatment(int treatmentID) {
    treatmentDAO.deleteTreatment(treatmentID);
    System.out.println("Treatment deleted successfully.");
}

// Update methods for each entity

    private void updatePatient(String[] parts) {
        if (parts.length == 5) {
            // Update a single field
            try {
                int patientID = Integer.parseInt(parts[2]);
                String field = parts[3];
                String value = parts[4];

                Patient patient = patientDAO.getPatientByID(patientID);
                if (patient == null) {
                    System.out.println("Patient not found.");
                    return;
                }

                switch (field.toLowerCase()) {
                    case "firstname":
                        patient.setFirstName(value);
                        break;
                    case "lastname":
                        patient.setLastName(value);
                        break;
                    case "birthdate":
                        patient.setBirthDate(LocalDate.parse(value));
                        break;
                    case "hmo":
                        patient.setHmoProvider(value);
                        break;
                    case "medicalhistory":
                        patient.setMedicalHistory(value);
                        break;
                    default:
                        System.out.println("Invalid field name for Patient.");
                        return;
                }

                patientDAO.updatePatient(patient); // Update in the database
                System.out.println("Patient updated successfully.");

            } catch (NumberFormatException e) {
                System.out.println("Invalid patient ID. Please enter a number.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        } else if (parts.length == 8) {
            // Update all fields
            try {
                int patientID = Integer.parseInt(parts[2]);
                String firstName = parts[3];
                String lastName = parts[4];
                LocalDate birthDate = LocalDate.parse(parts[5]);
                String HMO = parts[6];
                String medicalHistory = parts[7];

                Patient patient = new Patient(patientID, firstName, lastName, birthDate, HMO, medicalHistory);
                patientDAO.updatePatient(patient); // Update in the database
                System.out.println("Patient updated successfully.");

            } catch (NumberFormatException e) {
                System.out.println("Invalid patient ID. Please enter a number.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        } else {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: update patient <id> <field> <value> OR update patient <id> <firstName> <lastName> <birthDate> <HMO> <medicalHistory>");
        }
    }

    private void updateDoctor(String[] parts) {
        if (parts.length == 5) {
            // Update a single field
            try {
                int doctorID = Integer.parseInt(parts[2]);
                String field = parts[3];
                String value = parts[4];
    
                Doctor doctor = doctorDAO.getDoctorByID(doctorID);
                if (doctor == null) {
                    System.out.println("Doctor not found.");
                    return;
                }
    
                switch (field.toLowerCase()) {
                    case "firstname":
                        doctor.setFirstname(value);
                        break;
                    case "lastname":
                        doctor.setLastname(value);
                        break;
                    case "specialization":
                        doctor.setSpecialization(value);
                        break;
                    case "department":
                        doctor.setDepartment(value);
                        break;
                    default:
                        System.out.println("Invalid field name for Doctor.");
                        return;
                }
    
                doctorDAO.updateDoctor(doctor); // Persist changes to the database
                System.out.println("Doctor updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid doctor ID. Please enter a number.");
            }
        } else if (parts.length == 7) {
            // Update all fields
            try {
                int doctorID = Integer.parseInt(parts[2]);
                String firstName = parts[3];
                String lastName = parts[4];
                String specialization = parts[5];
                String department = parts[6];
    
                Doctor doctor = new Doctor(doctorID, firstName, lastName, specialization, department);
                doctorDAO.updateDoctor(doctor); // Persist changes to the database
                System.out.println("Doctor updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid doctor ID. Please enter a number.");
            }
        } else {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: update doctor <id> <field> <value> OR update doctor <id> <firstName> <lastName> <specialization> <department>");
        }
    }
    
    private void updateRoom(String[] parts) {
        if (parts.length == 5) {
            // Update a single field
            try {
                int roomID = Integer.parseInt(parts[2]);
                String field = parts[3];
                String value = parts[4];
    
                Room room = roomDAO.getRoomByID(roomID);
                if (room == null) {
                    System.out.println("Room not found.");
                    return;
                }
    
                switch (field.toLowerCase()) {
                    case "roomtype":
                        room.setRoomType(value);
                        break;
                    case "isavailable":
                        room.setAvailable(Boolean.parseBoolean(value));
                        break;
                    case "lastmaintenance":
                        room.setLastMaintenance(LocalDate.parse(value));
                        break;
                    case "cost":
                        room.setCost(Double.parseDouble(value));
                        break;
                    default:
                        System.out.println("Invalid field name for Room.");
                        return;
                }
    
                roomDAO.updateRoom(room); // Persist changes to the database
                System.out.println("Room updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid room ID or cost. Please enter numbers.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format for lastMaintenance. Please use YYYY-MM-DD.");
            }
        } else if (parts.length == 7) {
            // Update all fields
            try {
                int roomID = Integer.parseInt(parts[2]);
                String roomType = parts[3];
                boolean isAvailable = Boolean.parseBoolean(parts[4]);
                LocalDate lastMaintenance = LocalDate.parse(parts[5]);
                double cost = Double.parseDouble(parts[6]);
    
                Room room = new Room(roomID, roomType, isAvailable, lastMaintenance, cost);
                roomDAO.updateRoom(room); // Persist changes to the database
                System.out.println("Room updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid room ID or cost. Please enter numbers.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format for lastMaintenance. Please use YYYY-MM-DD.");
            }
        } else {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: update room <id> <field> <value> OR update room <id> <roomType> <isAvailable> <lastMaintenance> <cost>");
        }
    }
    
    private void updateLaboratory(String[] parts) {
        if (parts.length == 5) {
            // Update a single field
            try {
                int laboratoryID = Integer.parseInt(parts[2]);
                String field = parts[3];
                String value = parts[4];
    
                Laboratory laboratory = laboratoryDAO.getLaboratoryByID(laboratoryID);
                if (laboratory == null) {
                    System.out.println("Laboratory not found.");
                    return;
                }
    
                switch (field.toLowerCase()) {
                    case "name":
                        laboratory.setLaboratoryName(value);
                        break;
                    case "contactnumber":
                        laboratory.setContactNumber(value);
                        break;
                    default:
                        System.out.println("Invalid field name for Laboratory.");
                        return;
                }
    
                laboratoryDAO.updateLaboratory(laboratory); // Persist changes to the database
                System.out.println("Laboratory updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid laboratory ID. Please enter a number.");
            }
        } else if (parts.length == 5) {
            // Update all fields
            try {
                int laboratoryID = Integer.parseInt(parts[2]);
                String name = parts[3];
                String contactNumber = parts[4];
    
                Laboratory laboratory = new Laboratory(laboratoryID, name, contactNumber);
                laboratoryDAO.updateLaboratory(laboratory); // Persist changes to the database
                System.out.println("Laboratory updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid laboratory ID. Please enter a number.");
            }
        } else {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: update laboratory <id> <field> <value> OR update laboratory <id> <name> <contactNumber>");
        }
    }
    
    private void updateAdmission(String[] parts) {
        if (parts.length == 5) {
            // Update a single field
            try {
                int admissionID = Integer.parseInt(parts[2]);
                String field = parts[3];
                String value = parts[4];
    
                Admission admission = admissionDAO.getAdmissionById(admissionID);
                if (admission == null) {
                    System.out.println("Admission not found.");
                    return;
                }
    
                switch (field.toLowerCase()) {
                    case "patientid":
                        admission.setPatientID(Integer.parseInt(value));
                        break;
                    case "roomid":
                        admission.setRoomID(Integer.parseInt(value));
                        break;
                    case "doctorid":
                        admission.setDoctorID(Integer.parseInt(value));
                        break;
                    case "admissiondate":
                        admission.setAdmissionDate(LocalDate.parse(value));
                        break;
                    case "admissiontype":
                        admission.setAdmissionType(value);
                        break;
                    case "status":
                        admission.setStatus(value);
                        break;
                    default:
                        System.out.println("Invalid field name for Admission.");
                        return;
                }
    
                admissionDAO.updateAdmission(admission); // Persist changes to the database
                System.out.println("Admission updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers for IDs.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format for admissionDate. Please use YYYY-MM-DD.");
            }
        } else if (parts.length == 9) { // Adjusted for 8 data fields + ID
            // Update all fields
            try {
                int admissionID = Integer.parseInt(parts[2]);
                int patientID = Integer.parseInt(parts[3]);
                int roomID = Integer.parseInt(parts[4]);
                int doctorID = Integer.parseInt(parts[5]);
                LocalDate admissionDate = LocalDate.parse(parts[6]);
                String admissionType = parts[7];
                String status = parts[8];
    
                Admission admission = new Admission(admissionID, patientID, roomID, doctorID, admissionDate, admissionType, status);
                admissionDAO.updateAdmission(admission); // Persist changes tothe database
                System.out.println("Admission updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers for IDs.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format for admissionDate. Please use YYYY-MM-DD.");
            }
        } else {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: update admission <id> <field> <value> OR update admission <id> <patientID> <roomID> <doctorID> <admissionDate> <admissionType> <status>");
        }
    }
    
    private void updateAppointment(String[] parts) {
        if (parts.length == 5) {
            // Update a single field
            try {
                int appointmentID = Integer.parseInt(parts[2]);
                String field = parts[3];
                String value = parts[4];
    
                Appointment appointment = appointmentDAO.getAppointmentById(appointmentID);
                if (appointment == null) {
                    System.out.println("Appointment not found.");
                    return;
                }
    
                switch (field.toLowerCase()) {
                    case "patientid":
                        appointment.setPatientID(Integer.parseInt(value));
                        break;
                    case "doctorid":
                        appointment.setDoctorID(Integer.parseInt(value));
                        break;
                    case "roomid":
                        appointment.setRoomID(Integer.parseInt(value));
                        break;
                    case "appointmenttime":
                        appointment.setAppointmentTime(LocalDateTime.parse(value));
                        break;
                    case "status":
                        appointment.setStatus(value);
                        break;
                    case "appointmenttype":
                        appointment.setAppointmentType(value);
                        break;
                    case "priority":
                        appointment.setPriority(value);
                        break;
                    default:
                        System.out.println("Invalid field name for Appointment.");
                        return;
                }
    
                appointmentDAO.updateAppointment(appointment); // Persist changes to the database
                System.out.println("Appointment updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers for IDs.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid datetime format for appointmentTime. Please use YYYY-MM-DDTHH:mm:ss");
            }
        } else if (parts.length == 10) { // Adjusted for 9 data fields + ID
            // Update all fields
            try {
                int appointmentID = Integer.parseInt(parts[2]);
                int patientID = Integer.parseInt(parts[3]);
                int doctorID = Integer.parseInt(parts[4]);
                int roomID = Integer.parseInt(parts[5]);
                LocalDateTime appointmentTime = LocalDateTime.parse(parts[6]);
                String status = parts[7];
                String appointmentType = parts[8];
                String priority = parts[9];
    
                Appointment appointment = new Appointment(appointmentID, patientID, doctorID, roomID, appointmentTime, status, appointmentType, priority);
                appointmentDAO.updateAppointment(appointment); // Persist changes to the database
                System.out.println("Appointment updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers for IDs.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid datetime format for appointmentTime. Please use YYYY-MM-DDTHH:mm:ss");
            }
        } else {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: update appointment <id> <field> <value> OR update appointment <id> <patientID> <doctorID> <roomID> <appointmentTime> <status> <appointmentType> <priority>");
        }
    }
    
    private void updateBill(String[] parts) {
        if (parts.length == 5) {
            // Update a single field
            try {
                int billID = Integer.parseInt(parts[2]);
                String field = parts[3];
                String value = parts[4];
    
                Bill bill = billDAO.getBillByID(billID);
                if (bill == null) {
                    System.out.println("Bill not found.");
                    return;
                }
    
                switch (field.toLowerCase()) {
                    case "admissionid":
                        bill.setAdmissionID(Integer.parseInt(value));
                        break;
                    case "patientid":
                        bill.setPatientID(Integer.parseInt(value));
                        break;
                    case "billdate":
                        bill.setBillDate(LocalDate.parse(value));
                        break;
                    case "totalamount":
                        bill.setTotalAmount(Double.parseDouble(value));
                        break;
                    case "paymentstatus":
                        bill.setPaymentStatus(value);
                        break;
                    case "paymentmethod":
                        bill.setPaymentMethod(value);
                        break;
                    default:
                        System.out.println("Invalid field name for Bill.");
                        return;
                }
    
                billDAO.updateBill(bill); // Persist changes to the database
                System.out.println("Bill updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers for IDs and totalAmount.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format for billDate. Please use YYYY-MM-DD.");
            }
        } else if (parts.length == 9) { // Adjusted for 8 data fields + ID
            // Update all fields
            try {
                int billID = Integer.parseInt(parts[2]);
                int admissionID = Integer.parseInt(parts[3]);
                int patientID = Integer.parseInt(parts[4]);
                LocalDate billDate = LocalDate.parse(parts[5]);
                double totalAmount = Double.parseDouble(parts[6]);
                String paymentStatus = parts[7];
                String paymentMethod = parts[8];
    
                Bill bill = new Bill(billID, admissionID, patientID, billDate, totalAmount, paymentStatus, paymentMethod);
                billDAO.updateBill(bill); // Persist changes to the database
                System.out.println("Bill updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers for IDs and totalAmount.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format for billDate. Please use YYYY-MM-DD.");
            }
        } else {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: update bill <id> <field> <value> OR update bill <id> <admissionID> <patientID> <billDate> <totalAmount> <paymentStatus> <paymentMethod>");
        }
    }

    private void updateDiagnosis(String[] parts) {
        if (parts.length == 5) {
            // Update a single field
            try {
                int diagnosisID = Integer.parseInt(parts[2]);
                String field = parts[3];
                String value = parts[4];
    
                Diagnosis diagnosis = diagnosisDAO.getDiagnosisByID(diagnosisID);
                if (diagnosis == null) {
                    System.out.println("Diagnosis not found.");
                    return;
                }
    
                switch (field.toLowerCase()) {
                    case "diseaseid":
                        diagnosis.setDiseaseID(Integer.parseInt(value));
                        break;
                    case "admissionid":
                        diagnosis.setAdmissionID(Integer.parseInt(value));
                        break;
                    case "admissiontype":
                        diagnosis.setAdmissionType(value);
                        break;
                    case "diagnosisdate":
                        diagnosis.setDiagnosisDate(LocalDate.parse(value));
                        break;
                    case "severity":
                        diagnosis.setSeverity(value);
                        break;
                    case "status":
                        diagnosis.setStatus(value);
                        break;
                    default:
                        System.out.println("Invalid field name for Diagnosis.");
                        return;
                }
    
                diagnosisDAO.updateDiagnosis(diagnosis); // Persist changes to the database
                System.out.println("Diagnosis updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers for IDs.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format for diagnosisDate. Please use YYYY-MM-DD.");
            }
        } else if (parts.length == 9) { // Adjusted for 8 data fields + ID
            // Update all fields
            try {
                int diagnosisID = Integer.parseInt(parts[2]);
                int diseaseID = Integer.parseInt(parts[3]);
                int admissionID = Integer.parseInt(parts[4]);
                String admissionType = parts[5];
                LocalDate diagnosisDate = LocalDate.parse(parts[6]);
                String severity = parts[7];
                String status = parts[8];
    
                Diagnosis diagnosis = new Diagnosis(diagnosisID, diseaseID, admissionID, admissionType, diagnosisDate, severity, status);
                diagnosisDAO.updateDiagnosis(diagnosis); // Persist changes to the database
                System.out.println("Diagnosis updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers for IDs.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format for diagnosisDate. Please use YYYY-MM-DD.");
            }
        } else {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: update diagnosis <id> <field> <value> OR update diagnosis <id> <diseaseID> <admissionID> <admissionType> <diagnosisDate> <severity> <status>");
        }
    }
    
    private void updateDisease(String[] parts) {
        if (parts.length == 5) {
            // Update a single field
            try {
                int diseaseID = Integer.parseInt(parts[2]);
                String field = parts[3];
                String value = parts[4];
    
                Disease disease = diseaseDAO.getDiseaseByID(diseaseID);
                if (disease == null) {
                    System.out.println("Disease not found.");
                    return;
                }
    
                switch (field.toLowerCase()) {
                    case "name":
                        disease.setName(value);
                        break;
                    case "category":
                        disease.setCategory(value);
                        break;
                    case "icdcode":
                        disease.setIcdCode(value);
                        break;
                    case "description":
                        disease.setDescription(value);
                        break;
                    default:
                        System.out.println("Invalid field name for Disease.");
                        return;
                }
    
                diseaseDAO.updateDisease(disease); // Persist changes to the database
                System.out.println("Disease updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid disease ID. Please enter a number.");
            }
        } else if (parts.length == 7) { // Adjusted for 6 data fields + ID
            // Update all fields
            try {
                int diseaseID = Integer.parseInt(parts[2]);
                String name = parts[3];
                String category = parts[4];
                String icdCode = parts[5];
                String description = parts[6];
    
                Disease disease = new Disease(diseaseID, name, category, icdCode, description);
                diseaseDAO.updateDisease(disease); // Persist changes to the database
                System.out.println("Disease updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid disease ID. Please enter a number.");
            }
        } else {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: update disease <id> <field> <value> OR update disease <id> <name> <category> <icdCode> <description>");
        }
    }
    
    private void updateLabActivity(String[] parts) {
        if (parts.length == 5) {
            // Update a single field
            try {
                int labActivityID = Integer.parseInt(parts[2]);
                String field = parts[3];
                String value = parts[4];
    
                LabActivity labActivity = labActivityDAO.getLabActivityByID(labActivityID);
                if (labActivity == null) {
                    System.out.println("Lab Activity not found.");
                    return;
                }
    
                switch (field.toLowerCase()) {
                    case "labrequestid":
                        labActivity.setLabRequestID(Integer.parseInt(value));
                        break;
                    case "labstaffid":
                        labActivity.setLabStaffID(Integer.parseInt(value));
                        break;
                    case "starttime":
                        labActivity.setStartTime(LocalDateTime.parse(value));
                        break;
                    case "endtime":
                        labActivity.setEndTime(LocalDateTime.parse(value));
                        break;
                    case "activitytype":
                        labActivity.setActivityType(value);
                        break;
                    case "complexity":
                        labActivity.setComplexity(Integer.parseInt(value));
                        break;
                    case "status":
                        labActivity.setStatus(value);
                        break;
                    default:
                        System.out.println("Invalid field name for Lab Activity.");
                        return;
                }
    
                labActivityDAO.updateLabActivity(labActivity); // Persist changes to the database
                System.out.println("Lab Activity updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers for IDs and complexity.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid datetime format for startTime or endTime. Please use YYYY-MM-DDTHH:mm:ss");
            }
        } else if (parts.length == 10) { // Adjusted for 9 data fields + ID
            // Update all fields
            try {
                int labActivityID = Integer.parseInt(parts[2]);
                int labRequestID = Integer.parseInt(parts[3]);
                int labStaffID = Integer.parseInt(parts[4]);
                LocalDateTime startTime = LocalDateTime.parse(parts[5]);
                LocalDateTime endTime = LocalDateTime.parse(parts[6]);
                String activityType = parts[7];
                int complexity = Integer.parseInt(parts[8]);
                String status = parts[9];
    
                LabActivity labActivity = new LabActivity(labActivityID, labRequestID, labStaffID, startTime, endTime, activityType, complexity, status);
                labActivityDAO.updateLabActivity(labActivity); // Persist changes to the database
                System.out.println("Lab Activity updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers for IDs and complexity.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid datetime format for startTime or endTime. Please use YYYY-MM-DDTHH:mm:ss");
            }
        } else {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: update labactivity <id> <field> <value> OR update labactivity <id> <labRequestID> <labStaffID> <startTime> <endTime> <activityType> <complexity> <status>");
        }
    }

    private void updateLabRequest(String[] parts) {
        if (parts.length == 5) {
            // Update a single field
            try {
                int labRequestID = Integer.parseInt(parts[2]);
                String field = parts[3];
                String value = parts[4];
    
                LabRequest labRequest = labRequestDAO.getLabRequestByID(labRequestID);
                if (labRequest == null) {
                    System.out.println("Lab Request not found.");
                    return;
                }
    
                switch (field.toLowerCase()) {
                    case "patientid":
                        labRequest.setPatientID(Integer.parseInt(value));
                        break;
                    case "doctorid":
                        labRequest.setDoctorID(Integer.parseInt(value));
                        break;
                    case "laboratoryid":
                        labRequest.setLaboratoryID(Integer.parseInt(value));
                        break;
                    case "requestdate":
                        labRequest.setLabRequestDate(LocalDateTime.parse(value));
                        break;
                    case "cost":
                        labRequest.setCost(Double.parseDouble(value));
                        break;
                    default:
                        System.out.println("Invalid field name for Lab Request.");
                        return;
                }
    
                labRequestDAO.updateLabRequest(labRequest);
                System.out.println("Lab Request updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers for IDs and cost.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format for requestDate. Please use YYYY-MM-DD.");
            }
        } else if (parts.length == 8) {
            // Update all fields
            try {
                int labRequestID = Integer.parseInt(parts[2]);
                int patientID = Integer.parseInt(parts[3]);
                int doctorID = Integer.parseInt(parts[4]);
                int laboratoryID = Integer.parseInt(parts[5]);
                LocalDateTime requestDate = LocalDateTime.parse(parts[6]);
                double cost = Double.parseDouble(parts[7]);
    
                LabRequest labRequest = new LabRequest(labRequestID, patientID, doctorID, laboratoryID, requestDate, cost);
                labRequestDAO.updateLabRequest(labRequest);
                System.out.println("Lab Request updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers for IDs and cost.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format for requestDate. Please use YYYY-MM-DD.");
            }
        } else {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: update labrequest <id> <field> <value> OR update labrequest <id> <patientID> <doctorID> <laboratoryID> <requestDate> <cost>");
        }
    }

    private void updateLabStaff(String[] parts) {
        if (parts.length == 5) {
            // Update a single field
            try {
                int labStaffID = Integer.parseInt(parts[2]);
                String field = parts[3];
                String value = parts[4];
    
                LabStaff labStaff = labStaffDAO.getLabStaffByID(labStaffID);
                if (labStaff == null) {
                    System.out.println("Lab Staff not found.");
                    return;
                }
    
                switch (field.toLowerCase()) {
                    case "laboratoryid":
                        labStaff.setLaboratoryID(Integer.parseInt(value));
                        break;
                    case "name":
                        labStaff.setName(value);
                        break;
                    case "role":
                        labStaff.setRole(value);
                        break;
                    case "shift":
                        labStaff.setShift(value);
                        break;
                    default:
                        System.out.println("Invalid field name for Lab Staff.");
                        return;
                }
    
                labStaffDAO.updateLabStaff(labStaff);
                System.out.println("Lab Staff updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid lab staff ID. Please enter a number.");
            }
        } else if (parts.length == 7) {
            // Update all fields
            try {
                int labStaffID = Integer.parseInt(parts[2]);
                int laboratoryID = Integer.parseInt(parts[3]);
                String name = parts[4];
                String role = parts[5];
                String shift = parts[6];
    
                LabStaff labStaff = new LabStaff(labStaffID, laboratoryID, name, role, shift);
                labStaffDAO.updateLabStaff(labStaff);
                System.out.println("Lab Staff updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid lab staff ID or laboratory ID. Please enter numbers.");
            }
        } else {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: update labstaff <id> <field> <value> OR update labstaff <id> <laboratoryID> <name> <role> <shift>");
        }
    }

    private void updateMaintenance(String[] parts) {
        if (parts.length == 5) {
            // Update a single field
            try {
                int maintenanceID = Integer.parseInt(parts[2]);
                String field = parts[3];
                String value = parts[4];
    
                Maintenance maintenance = maintenanceDAO.getMaintenanceByID(maintenanceID);
                if (maintenance == null) {
                    System.out.println("Maintenance not found.");
                    return;
                }
    
                switch (field.toLowerCase()) {
                    case "roomid":
                        maintenance.setRoomID(Integer.parseInt(value));
                        break;
                    case "scheduledate":
                        maintenance.setScheduleDate(LocalDateTime.parse(value));
                        break;
                    case "completiondate":
                        maintenance.setCompletionDate(LocalDateTime.parse(value));
                        break;
                    case "status":
                        maintenance.setStatus(value);
                        break;
                    case "notes":
                        maintenance.setNotes(value);
                        break;
                    default:
                        System.out.println("Invalid field name for Maintenance.");
                        return;
                }
    
                maintenanceDAO.updateMaintenance(maintenance);
                System.out.println("Maintenance updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid maintenance ID or room ID. Please enter numbers.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid datetime format for scheduleDate or completionDate. Please use YYYY-MM-DDTHH:mm:ss");
            }
        } else if (parts.length == 8) {
            // Update all fields
            try {
                int maintenanceID = Integer.parseInt(parts[2]);
                int roomID = Integer.parseInt(parts[3]);
                LocalDateTime scheduleDate = LocalDateTime.parse(parts[4]);
                LocalDateTime completionDate = LocalDateTime.parse(parts[5]);
                String status = parts[6];
                String notes = parts[7];
    
                Maintenance maintenance = new Maintenance(maintenanceID, roomID, scheduleDate, completionDate, status, notes);
                maintenanceDAO.updateMaintenance(maintenance);
                System.out.println("Maintenance updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid maintenance ID or room ID. Please enter numbers.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid datetime format for scheduleDate or completionDate. Please use YYYY-MM-DDTHH:mm:ss");
            }
        } else {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: update maintenance <id> <field> <value> OR update maintenance <id> <roomID> <scheduleDate> <completionDate> <status> <notes>");
        }
    }
    
    private void updateTreatment(String[] parts) {
        if (parts.length == 5) {
            // Update a single field
            try {
                int treatmentID = Integer.parseInt(parts[2]);
                String field = parts[3];
                String value = parts[4];
    
                Treatment treatment = treatmentDAO.getTreatmentByID(treatmentID);
                if (treatment == null) {
                    System.out.println("Treatment not found.");
                    return;
                }
    
                switch (field.toLowerCase()) {
                    case "patientid":
                        treatment.setPatientID(Integer.parseInt(value));
                        break;
                    case "doctorid":
                        treatment.setDoctorID(Integer.parseInt(value));
                        break;
                    case "roomid":
                        treatment.setRoomID(Integer.parseInt(value));
                        break;
                    case "admissiondate":
                        treatment.setAdmissionDate(LocalDate.parse(value));
                        break;
                    case "treatmenttype":
                        treatment.setTreatmentType(value);
                        break;
                    case "description":
                        treatment.setDescription(value);
                        break;
                    case "cost":
                        treatment.setCost(Double.parseDouble(value));
                        break;
                    default:
                        System.out.println("Invalid field name for Treatment.");
                        return;
                }
    
                treatmentDAO.updateTreatment(treatment);
                System.out.println("Treatment updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers for IDs and cost.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format for admissionDate. Please use YYYY-MM-DD.");
            }
        } else if (parts.length == 10) {
            // Update all fields
            try {
                int treatmentID = Integer.parseInt(parts[2]);
                int patientID = Integer.parseInt(parts[3]);
                int doctorID = Integer.parseInt(parts[4]);
                int roomID = Integer.parseInt(parts[5]);
                LocalDate admissionDate = LocalDate.parse(parts[6]);
                String treatmentType = parts[7];
                String description = parts[8];
                double cost = Double.parseDouble(parts[9]);
    
                Treatment treatment = new Treatment(treatmentID, patientID, doctorID, roomID, admissionDate, treatmentType, description, cost);
                treatmentDAO.updateTreatment(treatment);
                System.out.println("Treatment updated successfully.");
    
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers for IDs and cost.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format for admissionDate. Please use YYYY-MM-DD.");
            }
        } else {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: update treatment <id> <field> <value> OR update treatment <id> <patientID> <doctorID> <roomID> <admissionDate> <treatmentType> <description> <cost>");
        }
    }
 

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

    private void handleTreatCommand(String[] parts) {
    	Scanner scanner = new Scanner(System.in);
			if (parts.length != 2 ) {
				System.out.println(INVALID_COMMAND_FORMAT + " Usage: treat <schedule/update>");
			    return;
			}
			
			String entity = parts[1];
			switch (entity.toLowerCase()) {
				case "schedule":
					// ask the user for the patientID until valid
			    	int patientID;
			    	while (true) {
			    		System.out.println("Enter Patient ID (or 0 to go back): ");
			    		patientID = scanner.nextInt();
			    		scanner.nextLine(); // consume newline
			    		
			    		// if input is 0, exits the function
			    		if (patientID == 0) {
			    			System.out.println("** Exitting command treat. **");
			    			return; // exits the scheduling function
			    		}
			    		
			    		Patient patient = patientDAO.getPatientByID(patientID);
			    		if (patient != null) {
			    			break; // exits loop if patient is found
			    		} else {
			    			System.out.println("Patient ID " + patientID + " does not exist. Please try again");
			    		}
			    	}
			    	
			    	// ask the user for the doctorID until valid
			    	int doctorID;
			    	while (true) {
			    		System.out.println("Enter Doctor ID (or 0 to go back): ");
			    		doctorID = scanner.nextInt();
			    		scanner.nextLine(); // consume newline
			    		
			    		//if input is 0, exits the function
			    		if (doctorID == 0) {
			    			System.out.println("** Exitting command treat. **");
			    			return; // exits the scheduling function
			    		}
			    		
			    		Doctor doctor = doctorDAO.getDoctorByID(doctorID);
			    		if (doctor != null) {
			    			break; // exits loop if doctor is found
			    		} else {
			    			System.out.println("Doctor ID " + doctorID + " does not exist. Please try again.");
			    		}
			    	}
			    	
			    	// ask the user for the roomID until valid
			    	int roomID;
			    	while (true) {
			    		System.out.println("Enter Room ID (or 0 to go back): ");
			    		roomID = scanner.nextInt();
			    		scanner.nextLine(); // consume newline
			    		
			    		//if input is 0, exits the function
			    		if (roomID == 0) {
			    			System.out.println("** Exitting command treat. **");
			    			return; // exits the scheduling function
			    		}
			    		
			    		Room room = roomDAO.getRoomByID(roomID);
			    		if (room != null) {
			    			break; // exits loop if room is found
			    		} else {
			    			System.out.println("Room ID " + roomID + " does not exist. Please try again.");
			    		}
			    	}
			    	
			    	// ask the user to input the admission date
			    	LocalDate admissionDate;
			    	while (true) {
			    		System.out.println("Enter Admission Date (yyyy-MM-dd): ");
			    		String dateInput = scanner.nextLine();
			    		try {
			    			admissionDate = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			    			break; // exit loop if date is valid
			    		} catch (DateTimeParseException e) {
			    			System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd");
			    		}
			    	}
			    	// ask the user to input the treatment type
			    	System.out.println("Enter Treatment Type: ");
			    	String treatmentType = scanner.nextLine();
			    	
			    	// ask the user to input description
			    	System.out.println("Enter Description: ");
			    	String description = scanner.nextLine();
			    	
			    	// ask the user to input cost
			    	System.out.println("Enter Cost: ");
			    	double cost = scanner.nextDouble();
			    	scanner.nextLine(); // consume newline
			    	
			    	treatmentService.scheduleTreatment(patientID, doctorID, roomID, admissionDate, treatmentType, description, cost);
			    	
					break;
					
				case "update":
					// ask the user to input a valid treatment id
			    	int treatmentID;
			    	while (true) {
			    		System.out.println("Input a Treatment ID (or 0 to go back): ");
			    		treatmentID = scanner.nextInt();
			    		scanner.nextLine(); // consume newline
			    		
			    		// if input is 0 exits the function
			    		if (treatmentID == 0) {
			    			System.out.println("** Exitting command treat. **");
			    			return; // exits the scheduling function
			    		}
			    		
						Treatment treatment = treatmentDAO.getTreatmentByID(treatmentID);
			    		if (treatment != null) {
			    			break; // exits loop if treatment record is found
			    		} else {
			    			System.out.println("Treatment ID " + treatmentID + " does not exist. Please try again.");
			    		}
			    	}
			    	// ask the user to update the treatment type
			    	System.out.println("Enter Treatment Type: ");
			    	treatmentType = scanner.nextLine();
			    	
			    	// ask the user to update description
			    	System.out.println("Enter Description: ");
			    	description = scanner.nextLine();
			    	
			    	// ask the user to update cost
			    	System.out.println("Enter Cost: ");
			    	cost = scanner.nextDouble();
			    	scanner.nextLine(); // consume newline
			    	
			    	// ask the user to update the admissionDate
			    	while (true) {
			    		System.out.println("Update Admission Date (yyyy-MM-dd): ");
			    		String dateInput = scanner.nextLine();
			    		try {
			    			admissionDate = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			    			break; // exit loop if date is valid
			    		} catch (DateTimeParseException e) {
			    			System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd");
			    		}
			    	}
			    	
			    	treatmentService.updateTreatment(treatmentID, treatmentType, description, cost, admissionDate);
			    	
					break;
					
				default:
					System.out.println("Invalid entity type. Usage: treat <schedule/update>");
			}
    }

    private void handleRequestCommand(String[] parts) {
        Scanner scanner = new Scanner(System.in);
        if (parts.length != 2) {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: request <schedule/update>");
            return;
        }

        String action = parts[1];
        switch (action.toLowerCase()) {
            case "schedule":
                // Ask the user for patientID until valid
                int patientID;
                while (true) {
                    System.out.println("Enter Patient ID (or 0 to go back): ");
                    patientID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (patientID == 0) {
                        System.out.println("** Exiting command request. **");
                        return;
                    }

                    Patient patient = patientDAO.getPatientByID(patientID);
                    if (patient != null) {
                        break;
                    } else {
                        System.out.println("Patient ID " + patientID + " does not exist. Please try again.");
                    }
                }

                // Ask the user for doctorID until valid
                int doctorID;
                while (true) {
                    System.out.println("Enter Doctor ID (or 0 to go back): ");
                    doctorID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (doctorID == 0) {
                        System.out.println("** Exiting command request. **");
                        return;
                    }

                    Doctor doctor = doctorDAO.getDoctorByID(doctorID);
                    if (doctor != null) {
                        break;
                    } else {
                        System.out.println("Doctor ID " + doctorID + " does not exist. Please try again.");
                    }
                }

                // Ask the user for laboratoryID until valid
                int laboratoryID;
                while (true) {
                    System.out.println("Enter Laboratory ID (or 0 to go back): ");
                    laboratoryID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (laboratoryID == 0) {
                        System.out.println("** Exiting command request. **");
                        return;
                    }

                    Laboratory lab = laboratoryDAO.getLaboratoryByID(laboratoryID);
                    if (lab != null) {
                        break;
                    } else {
                        System.out.println("Laboratory ID " + laboratoryID + " does not exist. Please try again.");
                    }
                }

                // Ask the user to input the lab request date
                LocalDateTime labRequestDate;
                while (true) {
                    System.out.println("Enter Lab Request Date and Time (yyyy-MM-ddTHH:mm): ");
                    String dateInput = scanner.nextLine();
                    try {
                        labRequestDate = LocalDateTime.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
                        break; // Exit loop if date and time are valid
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Please enter the date and time in the format yyyy-MM-ddTHH:mm.");
                    }
                }

                // Ask the user to input the cost
                System.out.println("Enter Cost: ");
                double cost = scanner.nextDouble();
                scanner.nextLine(); // Consume newline

                labRequestService.createLabRequest(patientID, doctorID, laboratoryID, labRequestDate, cost);

                break;

            case "update":
                // Ask the user for labRequestID until valid
                int labRequestID;
                while (true) {
                    System.out.println("Enter Lab Request ID (or 0 to go back): ");
                    labRequestID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (labRequestID == 0) {
                        System.out.println("** Exiting command request. **");
                        return;
                    }

                    LabRequest labRequest = labRequestDAO.getLabRequestByID(labRequestID);
                    if (labRequest != null) {
                        break;
                    } else {
                        System.out.println("Lab Request ID " + labRequestID + " does not exist. Please try again.");
                    }
                }

                // Ask the user to update the lab request date details
                System.out.println("Enter New Lab Request Date and Time (yyyy-MM-ddTHH:mm): ");
                String newDateInput = scanner.nextLine();
                LocalDateTime newLabRequestDate;
                try {
                    newLabRequestDate = LocalDateTime.parse(newDateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Keeping the previous date.");
                    newLabRequestDate = labRequestDAO.getLabRequestByID(labRequestID).getLabRequestDate();
                }

                // Ask the user to update the cost
                System.out.println("Enter New Cost: ");
                double newCost = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                
                // Ask the user to update laboratoryID
                while (true) {
                    System.out.println("Update Laboratory ID (or 0 to go back): ");
                    laboratoryID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (laboratoryID == 0) {
                        System.out.println("** Exiting command request. **");
                        return;
                    }

                    Laboratory lab = laboratoryDAO.getLaboratoryByID(laboratoryID);
                    if (lab != null) {
                        break;
                    } else {
                        System.out.println("Laboratory ID " + laboratoryID + " does not exist. Please try again.");
                    }
                }
                

                labRequestService.updateLabRequest(labRequestID, laboratoryID, newCost);

                break;

            default:
                System.out.println("Invalid action type. Usage: request <schedule/update>");
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
        if (parts.length != 3) {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: read <entity> <id>");
            return;
        }
    
        String entity = parts[1];
        int id;
        try {
            id = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a number.");
            return;
        }
    
        switch (entity.toLowerCase()) {
            case "patient":
                readPatient(id);
                break;
            case "doctor":
                readDoctor(id);
                break;
            case "room":
                readRoom(id);
                break;
            case "laboratory":
                readLaboratory(id);
                break;
            case "admission":
                readAdmission(id);
                break;
            case "appointment":
                readAppointment(id);
                break;
            case "bill":
                readBill(id);
                break;
            case "diagnosis":
                readDiagnosis(id);
                break;
            case "disease":
                readDisease(id);
                break;
            case "labactivity": // Note: This is one word due to the table name
                readLabActivity(id);
                break;
            case "labrequest":
                readLabRequest(id);
                break;
            case "labstaff":
                readLabStaff(id);
                break;
            case "maintenance":
                readMaintenance(id);
                break;
            case "treatment":
                readTreatment(id);
                break;
            default:
                System.out.println("Invalid entity type.");
        }
    }

    private void readPatient(int patientID) {
        Patient patient = patientDAO.getPatientByID(patientID);
        if (patient == null) {
            System.out.println("Patient not found.");
        } else {
            System.out.println(patient);
        }
    }
    
    private void readDoctor(int doctorID) {
        Doctor doctor = doctorDAO.getDoctorByID(doctorID);
        if (doctor == null) {
            System.out.println("Doctor not found.");
        } else {
            System.out.println(doctor);
        }
    }
    
    private void readRoom(int roomID) {
        Room room = roomDAO.getRoomByID(roomID);
        if (room == null) {
            System.out.println("Room not found.");
        } else {
            System.out.println(room);
        }
    }
    
    private void readLaboratory(int laboratoryID) {
        Laboratory laboratory = laboratoryDAO.getLaboratoryByID(laboratoryID);
        if (laboratory == null) {
            System.out.println("Laboratory not found.");
        } else {
            System.out.println(laboratory);
        }
    }
    
        private void readAdmission(int admissionID) {
        Admission admission = admissionDAO.getAdmissionById(admissionID);
        if (admission == null) {
            System.out.println("Admission not found.");
        } else {
            System.out.println("Admission ID: " + admission.getAdmissionID());
            System.out.println("Patient ID: " + admission.getPatientID());
            System.out.println("Admission Date: " + admission.getAdmissionDate());
        }
    }
    
    private void readAppointment(int appointmentID) {
        Appointment appointment = appointmentDAO.getAppointmentById(appointmentID);
        if (appointment == null) {
            System.out.println("Appointment not found.");
        } else {
            System.out.println(appointment);
        }
    }
    
    private void readBill(int billID) {
        Bill bill = billDAO.getBillByID(billID);
        if (bill == null) {
            System.out.println("Bill not found.");
        } else {
            System.out.println(bill);
        }
    }
    
    private void readDiagnosis(int diagnosisID) {
        Diagnosis diagnosis = diagnosisDAO.getDiagnosisByID(diagnosisID);
        if (diagnosis == null) {
            System.out.println("Diagnosis not found.");
        } else {
            System.out.println(diagnosis);
        }
    }
    
    private void readDisease(int diseaseID) {
        Disease disease = diseaseDAO.getDiseaseByID(diseaseID);
        if (disease == null) {
            System.out.println("Disease not found.");
        } else {
            System.out.println(disease);
        }
    }
    
    private void readLabActivity(int labActivityID) {
        LabActivity labActivity = labActivityDAO.getLabActivityByID(labActivityID);
        if (labActivity == null) {
            System.out.println("Lab Activity not found.");
        } else {
            System.out.println(labActivity);
        }
    }
    
    private void readLabRequest(int labRequestID) {
        LabRequest labRequest = labRequestDAO.getLabRequestByID(labRequestID);
        if (labRequest == null) {
            System.out.println("Lab Request not found.");
        } else {
            System.out.println(labRequest);
        }
    }
    
    private void readLabStaff(int labStaffID) {
        LabStaff labStaff = labStaffDAO.getLabStaffByID(labStaffID);
        if (labStaff == null) {
            System.out.println("Lab Staff not found.");
        } else {
            System.out.println(labStaff);
        }
    }
    
    private void readMaintenance(int maintenanceID) {
        Maintenance maintenance = maintenanceDAO.getMaintenanceByID(maintenanceID);
        if (maintenance == null) {
            System.out.println("Maintenance not found.");
        } else {
            System.out.println(maintenance);
        }
    }
    
    private void readTreatment(int treatmentID) {
        Treatment treatment = treatmentDAO.getTreatmentByID(treatmentID);
        if (treatment == null) {
            System.out.println("Treatment not found.");
        } else {
            System.out.println(treatment);
        }
    }

    private void handleListCommand(String[] parts) {
        if (parts.length != 2) {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: list <entity>");
            return;
        }
    
        String entity = parts[1];
        switch (entity.toLowerCase()) {
            case "patient":
                listAllPatients();
                break;
            case "doctor":
                listAllDoctors();
                break;
            case "room":
                listAllRooms();
                break;
            case "laboratory":
                listAllLaboratories();
                break;
            case "admission":
                listAllAdmissions();
                break;
            case "appointment":
                listAllAppointments();
                break;
            case "bill":
                listAllBills();
                break;
            case "diagnosis":
                listAllDiagnoses();
                break;
            case "disease":
                listAllDiseases();
                break;
            case "labactivity":
                listAllLabActivities();
                break;
            case "labrequest":
                listAllLabRequests();
                break;
            case "labstaff":
                listAllLabStaff();
                break;
            case "maintenance":
                listAllMaintenanceRecords();
                break;
            case "treatment":
                listAllTreatments();
                break;
            default:
                System.out.println("Invalid entity type.");
        }
    }
    
    private void listAllPatients() {
        List<Patient> patients = patientDAO.getAllPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            for (Patient patient : patients) {
                System.out.println(patient.getPatientID() + ". " + patient.getFirstName() + " " + patient.getLastName());
            }
        }
    }
    
    private void listAllDoctors() {
        List<Doctor> doctors = doctorDAO.getAllDoctors();
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
        } else {
            for (Doctor doctor : doctors) {
                System.out.println(doctor.getDoctorID() + ". " + doctor.getFirstName() + " " + doctor.getLastName());
            }
        }
    }
    
    private void listAllRooms() {
        List<Room> rooms = roomDAO.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            for (Room room : rooms) {
                System.out.println(room.getRoomID() + ". " + room.getRoomType());
            }
        }
    }
    
    private void listAllLaboratories() {
        List<Laboratory> laboratories = laboratoryDAO.getAllLaboratories();
        if (laboratories.isEmpty()) {
            System.out.println("No laboratories found.");
        } else {
            for (Laboratory laboratory : laboratories) {
                System.out.println(laboratory.getLaboratoryID() + ". " + laboratory.getLaboratoryName());
            }
        }
    }

    private void listAllAdmissions() {
        List<Admission> admissions = admissionDAO.getAllAdmissions();
        if (admissions.isEmpty()) {
            System.out.println("No admissions found.");
        } else {
            for (Admission admission : admissions) {
                Patient patient = patientDAO.getPatientByID(admission.getPatientID()); // Assuming you want to display patient name
                System.out.println(admission.getAdmissionID() + ". Patient: " + patient.getFirstName() + " " + patient.getLastName() +
                                   ", Type: " + admission.getAdmissionType() + ", Status: " + admission.getStatus());
            }
        }
    }
    
    private void listAllAppointments() {
        List<Appointment> appointments = appointmentDAO.getAllAppointments();
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            for (Appointment appointment : appointments) {
                Patient patient = patientDAO.getPatientByID(appointment.getPatientID());
                Doctor doctor = doctorDAO.getDoctorByID(appointment.getDoctorID());
                System.out.println(appointment.getAppointmentID() + ". Patient: " + patient.getFirstName() + " " + patient.getLastName() +
                                   ", Doctor: " + doctor.getFirstName() + " " + doctor.getLastName() +
                                   ", Time: " + appointment.getAppointmentTime());
            }
        }
    }
    
    private void listAllBills() {
        List<Bill> bills = billDAO.getAllBills();
        if (bills.isEmpty()) {
            System.out.println("No bills found.");
        } else {
            for (Bill bill : bills) {
                System.out.println(bill.getBillID() + ". Amount: " + bill.getTotalAmount() +
                                   ", Status: " + bill.getPaymentStatus());
            }
        }
    }
    
    private void listAllDiagnoses() {
        List<Diagnosis> diagnoses = diagnosisDAO.getAllDiagnoses();
        if (diagnoses.isEmpty()) {
            System.out.println("No diagnoses found.");
        } else {
            for (Diagnosis diagnosis : diagnoses) {
                Disease disease = diseaseDAO.getDiseaseByID(diagnosis.getDiseaseID());
                System.out.println(diagnosis.getDiagnosisID() + ". Disease: " + disease.getName() +
                                   ", Severity: " + diagnosis.getSeverity() + ", Status: " + diagnosis.getStatus());
            }
        }
    }
    
    private void listAllDiseases() {
        List<Disease> diseases = diseaseDAO.getAllDiseases();
        if (diseases.isEmpty()) {
            System.out.println("No diseases found.");
        } else {
            for (Disease disease : diseases) {
                System.out.println(disease.getDiseaseID() + ". " + disease.getName());
            }
        }
    }
    
    private void listAllLabActivities() {
        List<LabActivity> labActivities = labActivityDAO.getAllLabActivities();
        if (labActivities.isEmpty()) {
            System.out.println("No lab activities found.");
        } else {
            for (LabActivity labActivity : labActivities) {
                System.out.println(labActivity.getLabActivityID() + ". Type: " + labActivity.getActivityType() +
                                   ", Status: " + labActivity.getStatus());
            }
        }
    }
    
    private void listAllLabRequests() {
        List<LabRequest> labRequests = labRequestDAO.getAllLabRequests();
        if (labRequests.isEmpty()) {
            System.out.println("No lab requests found.");
        } else {
            for (LabRequest labRequest : labRequests) {
                Patient patient = patientDAO.getPatientByID(labRequest.getPatientID());
                System.out.println(labRequest.getLabRequestID() + ". Patient: " + patient.getFirstName() + " " + patient.getLastName() +
                                   ", Date: " + labRequest.getLabRequestDate());
            }
        }
    }
    
    private void listAllLabStaff() {
        List<LabStaff> labStaffMembers = labStaffDAO.getAllLabStaff();
        if (labStaffMembers.isEmpty()) {
            System.out.println("No lab staff found.");
        } else {
            for (LabStaff labStaff : labStaffMembers) {
                System.out.println(labStaff.getStaffID() + ". " + labStaff.getName() + ", Role: " + labStaff.getRole());
            }
        }
    }
    
    private void listAllMaintenanceRecords() {
        List<Maintenance> maintenanceRecords = maintenanceDAO.getAllMaintenanceRecords();
        if (maintenanceRecords.isEmpty()) {
            System.out.println("No maintenance records found.");
        } else {
            for (Maintenance maintenance : maintenanceRecords) {
                System.out.println(maintenance.getMaintenanceID() + ". Room ID: " + maintenance.getRoomID() +
                                   ", Status: " + maintenance.getStatus());
            }
        }
    }
    
    private void listAllTreatments() {
        List<Treatment> treatments = treatmentDAO.getAllTreatments();
        if (treatments.isEmpty()) {
            System.out.println("No treatments found.");
        } else {
            for (Treatment treatment : treatments) {
                Patient patient = patientDAO.getPatientByID(treatment.getPatientID());
                System.out.println(treatment.getTreatmentID() + ". Patient: " + patient.getFirstName() + " " + patient.getLastName() +
                                   ", Type: " + treatment.getTreatmentType() + ", Cost: " + treatment.getCost());
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

    private void handleHelpCommand(String[] parts) {
        if (parts.length == 1) {
            // Show general help message with all available commands
            System.out.println("Available commands:");
            System.out.println("  create <entity> - Create a new entity (patient, doctor, room, laboratory)");
            System.out.println("  read <entity> - Read all entities of a given type");
            System.out.println("  update <entity> <id> - Update an existing entity");
            System.out.println("  delete <entity> <id> - Delete an entity");
            System.out.println("  admit <admissionType> <patientID> <roomID> <doctorID> <admissionDate> <status> - Admit a patient");
            System.out.println("  treat <patientID> <doctorID> <treatmentDate> <treatmentType> <description> <cost> - Schedule a treatment");
            System.out.println("  request <patientID> <doctorID> <laboratoryID> <requestDate> <cost> - Create a lab request");
            System.out.println("  discharge <admissionID> - Discharge a patient");
            System.out.println("  generate report <report_type> <arguments> - Generate a report");
            System.out.println("  exit - Quit the program");
            System.out.println("Type 'help <command>' for more information on a specific command.");
    
        } else if (parts.length == 2) {
            String commandName = parts[1];
            switch (commandName.toLowerCase()) {
                case "create":
                    System.out.println("Usage: create <entity> <data>");
                    System.out.println("  Creates a new entity of the specified type.");
                    System.out.println("  Supported entities: patient, doctor, room, laboratory");
                    System.out.println("  Example: create patient John Doe 1990-01-01 HMO-A \"No allergies\"");
                    break;
                case "read":
                    System.out.println("Usage: read <entity>");
                    System.out.println("  Reads all entities of the specified type.");
                    System.out.println("  Supported entities: patient, doctor, room, laboratory");
                    System.out.println("  Example: read patient");
                    break;
                case "update":
                    System.out.println("Usage: update <entity> <id> <data>");
                    System.out.println("  Updates an existing entity with the given ID.");
                    System.out.println("  Supported entities: patient, doctor, room, laboratory");
                    System.out.println("  Example: update patient 1 Jane Doe 1990-01-01 HMO-A \"No allergies\"");
                    break;
                case "delete":
                    System.out.println("Usage: delete <entity> <id>");
                    System.out.println("  Deletes the entity with the given ID.");
                    System.out.println("  Supported entities: patient, doctor, room, laboratory");
                    System.out.println("  Example: delete patient 1");
                    break;
                case "admit":
                    System.out.println("Usage: admit <admissionType> <patientID> <roomID> <doctorID> <admissionDate> <status>");
                    System.out.println("  Admits a patient.");
                    System.out.println("  admissionType: in-patient or out-patient");
                    System.out.println("  Example: admit in-patient 1 101 5 2024-11-23 Admitted");
                    break;
                case "treat":
                    System.out.println("Usage: treat <patientID> <doctorID> <treatmentDate> <treatmentType> <description> <cost>");
                    System.out.println("  Schedules a treatment for a patient.");
                    System.out.println("  Example: treat 1 5 2024-11-24 \"Check-up\" \"General check-up\" 100.00");
                    break;
                case "request":
                    System.out.println("Usage: request <patientID> <doctorID> <laboratoryID> <requestDate> <cost>");
                    System.out.println("  Creates a lab request for a patient.");
                    System.out.println("  Example: request 1 5 1 2024-11-23 50.00");
                    break;
                case "discharge":
                    System.out.println("Usage: discharge <admissionID>");
                    System.out.println("  Discharges a patient.");
                    System.out.println("  Example: discharge 123");
                    break;
                case "generate":
                    System.out.println("Usage: generate report <report_type> <arguments>");
                    System.out.println("  Generates a report of the specified type.");
                    System.out.println("  Supported report types:");
                    System.out.println("    disease-activity <month> <year>");
                    System.out.println("    doctor-productivity <month> <year>");
                    System.out.println("    er-productivity <month> <year>");
                    System.out.println("    laboratory <month> <year>");
                    System.out.println("  Example: generate report laboratory 11 2024");
                    break;
                case "exit":
                    System.out.println("Usage: exit");
                    System.out.println("  Quits the program.");
                    break;
                default:
                    System.out.println("Invalid command name.");
            }
        } else {
            System.out.println(INVALID_COMMAND_FORMAT + " Usage: help [command]");
        }
    }
    
}
