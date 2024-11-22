/*
 * Manages treatment for both out-patients and in-patients.
 * Scheduling treatments.
 * Recording treatment details and outcomes.
 * Updating patient records.
 * Potentially updating billing information.
 */

import java.time.LocalDate;

public class TreatmentService {
    // import Relevant DAO's
	private TreatmentDAO treatmentDAO;
	private BillDAO billDAO;
	private AdmissionDAO admissionDAO;
	
	public TreatmentService() {
		this.treatmentDAO = new TreatmentDAO();
		this.billDAO = new BillDAO();
		this.admissionDAO = new AdmissionDAO();
	}

public void scheduleTreatment(int patientID, int doctorID, int roomID, LocalDate admissionDate, String treatmentType, String description, double cost) {
		System.out.println("** Schedule Treatment **");
	
    	// create a new record
    	Treatment treatment = new Treatment(0, patientID, doctorID, roomID, admissionDate, treatmentType, description, cost);
    	treatmentDAO.createTreatment(treatment);
    	
    	// retrieve admission record for when bill record doesn't exist for patientID
    	Admission admission = admissionDAO.getAdmissionById(patientID);
    	
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

public void updateTreatment(int treatmentID, String treatmentType, String description, double cost) {
    	System.out.println("** Update Treatment **");

    	// retrieve treatment record
    	Treatment treatment = treatmentDAO.getTreatmentByID(treatmentID);
    	if (treatment == null) {
    		System.out.println("Treatment ID record does not exist.");
    		return; // if treatment record does not exist, exits function
    	}
    	
    	// get bill info
    	Bill bill = billDAO.getBillByID(treatment.getPatientID());
    	
    	// subtract old cost from total bill
    	bill.setTotalAmount(bill.getTotalAmount() - treatment.getCost());
    	
    	// update treatment record
    	treatment.setTreatmentType(treatmentType);
    	treatment.setDescription(description);
    	treatment.setCost(cost);
    	treatmentDAO.updateTreatment(treatment);
    		
    	// update the total cost from bill
    	bill.setTotalAmount(treatment.getCost() + bill.getTotalAmount());
    	billDAO.updateBill(bill);
    		
    	System.out.println("** Treatment updated successfully! **\n");
    }
}

