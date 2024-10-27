package hospitalmanagementsystem;

import java.util.HashMap;
import java.util.Scanner;

public class BedAllocation {
    private static final int TOTAL_NORMAL_BEDS = 5;
    private int availableNormalBeds = TOTAL_NORMAL_BEDS;
    public static HashMap<Integer, Patient> patientRecords = new HashMap<>(); // Changed to public
    private Scanner scanner = new Scanner(System.in);

    // Method to retrieve patient by ID
    public static Patient getPatientById(int id) {
        return patientRecords.get(id); // Retrieve patient from records by ID
    }

    // Method to check in a patient to a normal room
    public void checkInNormalRoom() {
        System.out.print("Enter Patient ID to check in: ");
        int patientID = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        // Retrieve patient record using patient ID
        Patient patient = patientRecords.get(patientID);

        if (patient != null) {
            // Check if the patient is already checked into a room
            if (patient.getRoomNumber() != 0) {
                System.out.println("Patient ID " + patientID + " is already checked into Room " + patient.getRoomNumber() + ".");
                return; // Exit the method if already checked in
            }

            if (availableNormalBeds > 0) {
                int roomNumber = TOTAL_NORMAL_BEDS - availableNormalBeds + 1;
                patient.setRoomNumber(roomNumber);
                availableNormalBeds--;
                System.out.println("Normal Room " + roomNumber + " allocated to Patient ID: " + patient.getPatientID());
            } else {
                System.out.println("No normal beds available. Please try later.");
            }
        } else {
            System.out.println("No patient found with ID: " + patientID);
        }
    }

    // Method to check out a patient and release their room
    public void checkOutRoom() {
        System.out.print("Enter Patient ID to check out: ");
        int patientID = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        Patient patient = patientRecords.get(patientID);

        if (patient != null && patient.getRoomNumber() != 0) {
            int roomNumber = patient.getRoomNumber();
            patient.setRoomNumber(0); // Reset room number for patient
            availableNormalBeds++;

            // Calculate total billing
            int roomCharge = 200; // Room charge per day
            int totalBill = roomCharge; // Starting total bill

            // Call generateBill method to apply discounts and display billing information
            generateBill(patientID, totalBill, patient.getAge());

            System.out.println("Patient ID " + patientID + " checked out from Normal Room " + roomNumber + ".");
        } else {
            System.out.println("Invalid Patient ID or the patient is not checked into any room.");
        }
    }

    // Method for generating the bill based on the room charge and applying discounts
    private void generateBill(int patientId, int totalBill, int age) {
        StringBuilder discountMessage = new StringBuilder("Discounts Applied: ");

        // Apply discounts based on age
        if (age > 65) {
            totalBill *= 0.9; // 10% discount for seniors
            discountMessage.append("10% Senior Citizen Discount\n");
        } else if (age <= 5) {
            totalBill *= 0.8; // 20% discount for children
            discountMessage.append("20% Child Discount\n");
        }

        // Additional discount schemes
        Patient patient = patientRecords.get(patientId); // Retrieve patient details
        // Check for other schemes based on additional conditions (add your conditions here)
        
        if (patient.getMedicalHistory().toLowerCase().contains("chronic")) { // Example condition
            totalBill *= 0.85; // 15% discount for patients with chronic illnesses
            discountMessage.append("15% Discount for Chronic Illness\n");
        }

        if (patient.getGender().equalsIgnoreCase("female")) { // Example condition
            totalBill *= 0.95; // 5% discount for female patients
            discountMessage.append("5% Discount for Female Patients\n");
        }

        // Display billing information
        System.out.println("\n--- Billing Information ---");
        System.out.println("Patient ID: " + patientId);
        System.out.println("Room Charge: $" + 200);
        System.out.println(discountMessage.toString());
        System.out.println("Total Bill after discounts: $" + totalBill);
        System.out.println("----------------------------");
    }

    // Main menu for Bed Allocation
    public void displayMenu() {
        while (true) {
            System.out.println("\n--- Bed Allocation System ---");
            System.out.println("1. Check-In (Normal Room)");
            System.out.println("2. Check-Out (Room Release)");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    checkInNormalRoom();
                    break;
                case 2:
                    checkOutRoom();
                    break;
                case 3:
                    System.out.println("Exiting Bed Allocation System.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Method to add a new patient to records (for registration use)
    public static void addPatient(Patient patient) {
        patientRecords.put(patient.getPatientID(), patient);
    }

    public static void main(String[] args) {
        BedAllocation bedAllocationSystem = new BedAllocation();
        bedAllocationSystem.displayMenu();
    }
}
