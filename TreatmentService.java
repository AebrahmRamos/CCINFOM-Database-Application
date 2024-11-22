/*
 * Manages treatment for both out-patients and in-patients.
 * Scheduling treatments.
 * Recording treatment details and outcomes.
 * Updating patient records.
 * Potentially updating billing information.
 */

import java.time.LocalDate;
import java.util.Scanner;

public class TreatmentService {
    // import Relevant DAO's
	private TreatmentDAO treatmentDAO;
	private PatientDAO patientDAO;
	private BillDAO billDAO;
	private AdmissionDAO admissionDAO;
	private Scanner scanner;
	
	public TreatmentService() {
		this.treatmentDAO = new TreatmentDAO();
		this.patientDAO = new PatientDAO();
		this.billDAO = new BillDAO();
		this.admissionDAO = new AdmissionDAO();
		this.scanner = new Scanner(System.in);
	}

    public void scheduleTreatment() {
    	System.out.println("** Schedule Treatment **");
    	
    	// ask the user for the patientID until valid
    	int patientID;
    	while (true) {
    		System.out.println("Enter Patient ID (or 0 to go back): ");
    		patientID = scanner.nextInt();
    		scanner.nextLine(); // consume newline
    		
    		// if input is 0, exits the function
    		if (patientID == 0) {
    			System.out.println("** Going back to the main menu. **");
    			return; // exits the scheduling function
    		}
    		
    		Patient patient = patientDAO.getPatientByID(patientID);
    		if (patient != null) {
    			break; // exits loop if patient is found
    		} else {
    			System.out.println("Patient ID " + patientID + " does not exist. Please try again");
    		}
    	}
    	// retrieve admission record to copy values over
    	Admission admission = admissionDAO.getAdmissionById(patientID);
    	int doctorID = admission.getDoctorID();
    	int roomID = admission.getRoomID();
    	LocalDate admissionDate = admission.getAdmissionDate();
    	
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
    	
    	// create a new record using the inputs from the user
    	// use 0 as placeholder for treatmentID (will increment by itself)
    	Treatment treatment = new Treatment(0, patientID, doctorID, roomID, admissionDate, treatmentType, description, cost);
    	treatmentDAO.createTreatment(treatment);
    	
    	// update billing information
    	Bill bill = billDAO.getBillByID(patientID);
    	if (bill != null) {
    		bill.setTotalAmount(cost + bill.getTotalAmount());
    		billDAO.updateBill(bill);
    		System.out.println("Existing bill updated for Patient ID: " + patientID + ". Added cost: " + cost);
    	} else { // creates new bill if patient has none yet
    		System.out.println("No existing bill found for Patient ID: " + patientID + ". Creating a new bill.");
    		bill = new Bill(0, admission.getAdmissionID(), patientID, LocalDate.now(), cost, "Pending", "Cash");
    		billDAO.createBill(bill);
    	}
    	
    	System.out.println("** Treatment scheduled successfully! **\n");
    }

    public void updateTreatment() {
    	System.out.println("** Update Treatment **");
    	
    	// ask the user to input a valid treatment id
    	int treatmentID;
    	while (true) {
    		System.out.println("Input a Treatment ID (or 0 to go back): ");
    		treatmentID = scanner.nextInt();
    		scanner.nextLine(); // consume newline
    		
    		// if input is 0 exits the function
    		if (treatmentID == 0) {
    			System.out.println("** Going back to the main menu. **");
    			return; // exits the scheduling function
    		}
    		
    		Treatment treatment = treatmentDAO.getTreatmentByID(treatmentID);
    		if (treatment != null) {
    			break; // exits loop if treatment record is found
    		} else {
    			System.out.println("Treatment ID " + treatmentID + " does not exist. Please try again.");
    		}
    	}
    	
    	
    	// retrieve treatment record
    	Treatment treatment = treatmentDAO.getTreatmentByID(treatmentID);
    	
    	// get bill info
    	Bill bill = billDAO.getBillByID(treatment.getPatientID());
    	
    	// subtract old cost from total bill
    	bill.setTotalAmount(bill.getTotalAmount() - treatment.getCost());
    	
    	// update treatment record
    	// ask the user to input the treatment type
    	System.out.println("Update Treatment Type: ");
    	treatment.setTreatmentType(scanner.nextLine());
    	
    	// ask the user to input description
    	System.out.println("Update Description: ");
    	treatment.setDescription(scanner.nextLine());
    	
    	// ask the user to input cost
    	System.out.println("Update Cost: ");
    	treatment.setCost(scanner.nextDouble());
    	scanner.nextLine(); // consume newline
    	treatmentDAO.updateTreatment(treatment);
    		
    	// update the total cost from bill
    	bill.setTotalAmount(treatment.getCost() + bill.getTotalAmount());
    	billDAO.updateBill(bill);
    		
    	System.out.println("** Treatment updated successfully! **\n");
    }
}

