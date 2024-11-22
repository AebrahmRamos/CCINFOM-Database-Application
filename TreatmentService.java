/*
 * Manages treatment for both out-patients and in-patients.
 * Scheduling treatments.
 * Recording treatment details and outcomes.
 * Updating patient records.
 * Potentially updating billing information.
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TreatmentService {
    // import Relevant DAO's
	private TreatmentDAO treatmentDAO;
	private PatientDAO patientDAO;
	private BillDAO billDAO;
	private DoctorDAO doctorDAO;
	private RoomDAO roomDAO;
	private Scanner scanner;
	
	public TreatmentService() {
		this.treatmentDAO = new TreatmentDAO();
		this.patientDAO = new PatientDAO();
		this.billDAO = new BillDAO();
		this.doctorDAO = new DoctorDAO();
		this.roomDAO = new RoomDAO();
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
    	
    	// ask the user for the doctorID until valid
    	int doctorID;
    	while (true) {
    		System.out.println("Enter Doctor ID (or 0 to go back): ");
    		doctorID = scanner.nextInt();
    		scanner.nextLine(); // consume newline
    		
    		//if input is 0, exits the function
    		if (doctorID == 0) {
    			System.out.println("** Going back to the main menu. **");
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
    			System.out.println("** Going back to the main menu. **");
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
    	}
    	
    	System.out.println("** Treatment scheduled successfully! **\n");
    }

    public void updateTreatment(int treatmentID, String treatmentType, String description, double cost) {
    	
    	// retrieve treatment record
    	Treatment treatment = treatmentDAO.getTreatmentByID(treatmentID);
    	
    	// get bill info
    	Bill bill = billDAO.getBillByID(treatment.getPatientID());
    	
    	// update treatment record
    	if (treatment != null) {
    		treatment.setTreatmentType(treatmentType);
    		treatment.setDescription(description);
    		treatment.setCost(cost);
    		treatmentDAO.updateTreatment(treatment);
    		
    		// update the total cost from bill
    		bill.setTotalAmount(cost + bill.getTotalAmount());
    		billDAO.updateBill(bill);
    		
    		System.out.println("Treatment updated successfully!\n");
    	} else {
    		System.out.println("Treatment ID not found.\n");
    	}
    }
}

