/*
 * Handles requests and performance of lab procedures. 
 * Creating lab requests.
 * Assigning requests to labs and technicians. 
 * Tracking the status of requests. 
 * Recording lab results.
 */

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LabRequestService {
    // import relevant DAO's
	private LabRequestDAO labrequestDAO;
	private BillDAO billDAO;
	private AdmissionDAO admissionDAO;
	
	public LabRequestService(){
		this.labrequestDAO = new LabRequestDAO();
		this.billDAO = new BillDAO();
		this.admissionDAO = new AdmissionDAO();
	}

	public void createLabRequest(int patientID, int doctorID, int laboratoryID, LocalDateTime requestDate, double cost) {
        // create a request
    	LabRequest labrequest = new LabRequest(0, patientID, doctorID, laboratoryID, requestDate, cost);
    	labrequestDAO.createLabRequest(labrequest);
    	
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
    }

    public void updateLabRequest(int labRequestID, int laboratoryID, double cost) {
        
    	// retrieve lab request record
    	LabRequest labrequest = labrequestDAO.getLabRequestByID(labRequestID);
    	
    	// get bill info
    	Bill bill = billDAO.getBillByID(labrequest.getPatientID());
    	
    	// subtract initial cost from total
    	bill.setTotalAmount(bill.getTotalAmount() - labrequest.getCost());
    	
    	// update lab request
    	labrequest.setLaboratoryID(laboratoryID);
    	labrequest.setCost(cost);
    	labrequestDAO.updateLabRequest(labrequest);
    	
    	// add new cost to total bill
    	bill.setTotalAmount(labrequest.getCost() + bill.getTotalAmount());
    	billDAO.updateBill(bill);
    }
}
