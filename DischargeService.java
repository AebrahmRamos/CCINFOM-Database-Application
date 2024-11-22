import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class DischargeService {

    private AdmissionDAO admissionDAO;
    private RoomDAO roomDAO;
    private PatientDAO patientDAO;
    private BillDAO billDAO;
    private TreatmentDAO treatmentDAO;
    private LabRequestDAO labRequestDAO;

    public DischargeService() {
        admissionDAO = new AdmissionDAO();
        roomDAO = new RoomDAO();
        patientDAO = new PatientDAO();
        billDAO = new BillDAO();
        treatmentDAO = new TreatmentDAO();
        labRequestDAO = new LabRequestDAO();
    }

    public void dischargePatient(int admissionID) {
        try {
            Admission admission = admissionDAO.getAdmissionById(admissionID);
            if (admission == null) {
                System.err.println("Error: Admission not found.");
                return;
            }

            admission.setStatus("Discharged");
            admissionDAO.updateAdmission(admission);

            if (admission.getAdmissionType().equalsIgnoreCase("in-patient")) {
                Room room = roomDAO.getRoomByID(admission.getRoomID());
                if (room != null) {
                    room.setAvailable(true);
                    roomDAO.updateRoom(room);
                }
            }

            generateFinalBill(admission);


            System.out.println("Patient discharged successfully.");

        } catch (Exception e) {
            System.err.println("Error discharging patient: " + e.getMessage());
        }
    }

    private void generateFinalBill(Admission admission) {
        // Calculate total charges
        double totalCharges = calculateTotalCharges(admission);

        // Create a new bill
        Bill finalBill = new Bill(
                1,
                admission.getAdmissionID(),
                admission.getPatientID(),
                LocalDate.now(), // or admission.getDischargeDate() if you have it
                totalCharges,
                "Pending", // Initial payment status
                "N/A" // Payment method not applicable yet
        );

        billDAO.createBill(finalBill);

        System.out.println("Final bill generated with ID: " + finalBill.getBillID());
        // ... (Potentially display bill details)
    }

    private double calculateTotalCharges(Admission admission) {
        double totalCharges = 0;

        // 1. Retrieve treatment costs
        List<Treatment> treatments = treatmentDAO.getAllTreatments();
        for (Treatment treatment : treatments) {
            if (treatment.getPatientID() == admission.getPatientID() &&
                    treatment.getAdmissionDate().equals(admission.getAdmissionDate())) {
                totalCharges += treatment.getCost();
            }
        }

        // 2. Retrieve lab test costs
        List<LabRequest> labRequests = labRequestDAO.getAllLabRequests();
        for (LabRequest labRequest : labRequests) {
            if (labRequest.getPatientID() == admission.getPatientID() &&
                    labRequest.getLabRequestDate().isAfter(admission.getAdmissionDate().atStartOfDay()) &&  
                    labRequest.getLabRequestDate().isAfter(admission.getAdmissionDate().atStartOfDay())) { 

                totalCharges += labRequest.getCost();
            }
        }

        // 3. Calculate room charges (if in-patient)
        if (admission.getAdmissionType().equalsIgnoreCase("in-patient")) {
            Room room = roomDAO.getRoomByID(admission.getRoomID());
            if (room != null) {
                // Calculate the number of days between admission and discharge (or current date)
                LocalDate dischargeDate = LocalDate.now();
                long days = ChronoUnit.DAYS.between(admission.getAdmissionDate(), dischargeDate);

                totalCharges += room.getCost() * days;
            }
        }

        return totalCharges;
    }

}