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
	private PatientDAO patientDAO;
	private BillDAO billDAO;
	
	public TreatmentService() {
		this.treatmentDAO = new TreatmentDAO();
		this.patientDAO = new PatientDAO();
		this.billDAO = new BillDAO();
	}

    public void scheduleTreatment(int treatmentID, int patientID, int doctorID, int roomID, LocalDate admissionDate, String treatmentType, String description, double cost) {
    	
    	// create a new record
    	Treatment treatment = new Treatment(treatmentID, patientID, doctorID, roomID, admissionDate, treatmentType, description, cost);
    	treatmentDAO.createTreatment(treatment);
    	
    	// update billing information
    	Bill bill = billDAO.getBillByID(patientID);
    	if (bill != null) {
    		bill.setTotalAmount(cost + bill.getTotalAmount());
    		billDAO.updateBill(bill);
    		System.out.println("Existing bill updated for Patient ID: " + patientID + ". Added cost: " + cost);
    	}
    	
    	System.out.println("Treatment scheduled successfully!\n");
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

