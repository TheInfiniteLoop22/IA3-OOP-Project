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
            System.out.println("Patient ID " + patientID + " checked out from Normal Room " + roomNumber + ".");
            System.out.println("Billing: Room Type - Normal, Room Charge - $200/day");
        } else {
            System.out.println("Invalid Patient ID or the patient is not checked into any room.");
        }
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
