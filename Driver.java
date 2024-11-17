import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        System.out.println("Welcome to the Hospital Management System");
        System.out.println("Please choose which records you want to manage");

        HospitalRecords hospitalRecords = new HospitalRecords();
        Scanner input = new Scanner(System.in);
        int choice = -1;

        while (true) {
            System.out.println("1. Patient Records Management");
            System.out.println("2. Doctor Records Management");
            System.out.println("3. Appointment Records Management");
            System.out.println("4. Room and Facilities Records Management");
            System.out.println("5. Exit");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    // Call methods from hospitalRecords.getPatientRecords()
                    break;
                case 2:
                    // Call methods from hospitalRecords.getDoctorRecords()
                    break;
                case 3:
                    // Call methods from hospitalRecords.getAppointmentRecords()
                    break;
                case 4:
                    // Call methods from hospitalRecords.getRoomRecords()
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}