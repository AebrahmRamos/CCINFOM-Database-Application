import java.time.LocalDate;
import java.util.List;

public class Driver { 

    public static void main(String[] args) {
        AdmissionDAO admissionDAO = new AdmissionDAO();

        // Test createAdmission()
        Admission admission = new Admission(1, 1, 1, 1, LocalDate.of(2024, 11, 20), "In-patient", "Admitted");
        admissionDAO.createAdmission(admission);
        System.out.println("Admission created with ID: " + admission.getAdmissionID());

        // Test getAdmissionById()
        Admission retrievedAdmission = admissionDAO.getAdmissionById(admission.getAdmissionID());
        if (retrievedAdmission != null) {
            System.out.println("Retrieved admission: " + retrievedAdmission); 
        } else {
            System.out.println("Admission not found.");
        }

        // Test updateAdmission()
        retrievedAdmission.setAdmissionType("ER"); 
        admissionDAO.updateAdmission(retrievedAdmission);
        System.out.println("Admission updated.");

        // Test deleteAdmission()
        admissionDAO.deleteAdmission(admission.getAdmissionID());
        System.out.println("Admission deleted.");

        // Test getAllAdmissions() (after creating a few more)
        admissionDAO.createAdmission(new Admission(2, 2, 2, 2, LocalDate.of(2024, 11, 21), "ER", "Discharged")); 
        admissionDAO.createAdmission(new Admission(3, 1, 3, 3, LocalDate.of(2024, 11, 22), "Out-patient", "Completed")); 

        List<Admission> admissions = admissionDAO.getAllAdmissions();
        System.out.println("All admissions:");
        for (Admission a : admissions) {
            System.out.println(a);
        }
    }
}