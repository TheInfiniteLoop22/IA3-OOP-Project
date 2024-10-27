package hospitalmanagementsystem;

import java.util.Scanner;

public class Registration {
    private BedAllocation bedAllocationSystem = new BedAllocation(); // Instance to manage registrations

    public void registerPatient() {
        Scanner scanner = new Scanner(System.in);

        // Collect patient details individually
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter your contact number: ");
        String contactNumber = scanner.nextLine();

        System.out.print("Enter your blood group: ");
        String bloodGroup = scanner.nextLine();

        System.out.print("Enter your date of birth (DD/MM/YYYY): ");
        String dob = scanner.nextLine();

        System.out.print("Enter your medical history: ");
        String medicalHistory = scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        // Create a new patient object
        Patient patient = new Patient(name, gender, contactNumber, bloodGroup, dob, medicalHistory, age); // Pass age to the constructor

        // Add patient to bed allocation system
        BedAllocation.addPatient(patient);
        
        // Display confirmation to the patient
        patient.displayConfirmation();
    }

    public static void main(String[] args) {
        Registration registration = new Registration();
        registration.registerPatient();
    }
}
