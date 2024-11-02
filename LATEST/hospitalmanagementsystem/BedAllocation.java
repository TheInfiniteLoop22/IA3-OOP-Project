package hospitalmanagementsystem;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.util.HashMap;
import java.util.Optional;

public class BedAllocation {
    public static final int TOTAL_NORMAL_BEDS = 5;
    public int availableNormalBeds = TOTAL_NORMAL_BEDS;
    public static HashMap<Integer, Patient> patientRecords = new HashMap<>();

    // Retrieve patient by ID
    public static Patient getPatientById(int id) {
        return patientRecords.get(id);
    }

    // Check in a patient to a normal room
    public void checkInNormalRoom() {
        // Prompt for Patient ID
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Check-In (Normal Room)");
        idDialog.setHeaderText(null);
        idDialog.setContentText("Enter Patient ID to check in:");
        Optional<String> idResult = idDialog.showAndWait();

        if (idResult.isEmpty()) return;

        int patientID = Integer.parseInt(idResult.get());
        Patient patient = patientRecords.get(patientID);

        if (patient != null) {
            if (patient.getRoomNumber() != 0) {
                showAlert("Check-In Error", "Patient ID " + patientID + " is already checked into Room " + patient.getRoomNumber() + ".");
                return;
            }

            if (availableNormalBeds > 0) {
                int roomNumber = TOTAL_NORMAL_BEDS - availableNormalBeds + 1;
                patient.setRoomNumber(roomNumber);
                availableNormalBeds--;
                showAlert("Check-In Success", "Normal Room " + roomNumber + " allocated to Patient ID: " + patientID);
            } else {
                showAlert("Check-In Error", "No normal beds available. Please try later.");
            }
        } else {
            showAlert("Check-In Error", "No patient found with ID: " + patientID);
        }
    }

    // Check out a patient and release their room
    public void checkOutRoom() {
        // Prompt for Patient ID
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Check-Out (Room Release)");
        idDialog.setHeaderText(null);
        idDialog.setContentText("Enter Patient ID to check out:");
        Optional<String> idResult = idDialog.showAndWait();

        if (idResult.isEmpty()) return;

        int patientID = Integer.parseInt(idResult.get());
        Patient patient = patientRecords.get(patientID);

        if (patient != null && patient.getRoomNumber() != 0) {
            int roomNumber = patient.getRoomNumber();
            patient.setRoomNumber(0);
            availableNormalBeds++;

            int roomCharge = 200;
            int totalBill = roomCharge;

            generateBill(patientID, totalBill, patient.getAge());
            showAlert("Check-Out Success", "Patient ID " + patientID + " checked out from Normal Room " + roomNumber + ".");
        } else {
            showAlert("Check-Out Error", "Invalid Patient ID or the patient is not checked into any room.");
        }
    }

    // Generate the bill based on the room charge and discounts
    public void generateBill(int patientId, int totalBill, int age) {
        StringBuilder discountMessage = new StringBuilder("Discounts Applied:\n");

        // Apply discounts based on age
        if (age > 65) {
            totalBill *= 0.9;
            discountMessage.append("10% Senior Citizen Discount\n");
        } else if (age <= 5) {
            totalBill *= 0.8;
            discountMessage.append("20% Child Discount\n");
        }

        Patient patient = patientRecords.get(patientId);
        if (patient.getMedicalHistory().toLowerCase().contains("chronic")) {
            totalBill *= 0.85;
            discountMessage.append("15% Discount for Chronic Illness\n");
        }

        if (patient.getGender().equalsIgnoreCase("female")) {
            totalBill *= 0.95;
            discountMessage.append("5% Discount for Female Patients\n");
        }

        showAlert("Billing Information",
                "Patient ID: " + patientId +
                "\nRoom Charge: $" + 200 +
                "\n" + discountMessage.toString() +
                "Total Bill after discounts: $" + totalBill);
    }

    // Display menu
    public void displayMenu() {
        while (true) {
            TextInputDialog menuDialog = new TextInputDialog();
            menuDialog.setTitle("Bed Allocation System");
            menuDialog.setHeaderText(null);
            menuDialog.setContentText("Choose an option:\n1. Check-In (Normal Room)\n2. Check-Out (Room Release)\n3. Exit");
            Optional<String> menuResult = menuDialog.showAndWait();

            if (menuResult.isEmpty()) return;

            int choice = Integer.parseInt(menuResult.get());

            switch (choice) {
                case 1:
                    checkInNormalRoom();
                    break;
                case 2:
                    checkOutRoom();
                    break;
                case 3:
                    showAlert("Exit", "Exiting Bed Allocation System.");
                    return;
                default:
                    showAlert("Invalid Option", "Please try again.");
            }
        }
    }

    // Utility to show alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Add a new patient to records
    public static void addPatient(Patient patient) {
        patientRecords.put(patient.getPatientID(), patient);
    }

    public static void main(String[] args) {
        BedAllocation bedAllocationSystem = new BedAllocation();
        bedAllocationSystem.displayMenu();
    }
}
