package hospitalmanagementsystem;

import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.*;

public class Appointment {
    public static final Map<String, List<String>> doctorAvailability = new HashMap<>();

    public Appointment() {
        // Initialize available time slots for each doctor
        for (String dept : Info.departments) {
            for (String doctor : Info.doctors.get(dept)) {
                doctorAvailability.put(doctor, new ArrayList<>(Arrays.asList("9:00 AM", "10:00 AM", "11:00 AM", "2:00 PM", "3:00 PM")));
            }
        }
    }

    public void bookAppointment(String department, String doctorType) {
        int patientId = getPatientId();

        if (!BedAllocation.patientRecords.containsKey(patientId)) {
            showAlert("Invalid Patient ID", "Please register first.");
            return;
        }

        Patient patient = BedAllocation.patientRecords.get(patientId); // Retrieve patient details
        int age = patient.getAge(); // Get the patient's age

        if (!Info.doctors.containsKey(department)) {
            showAlert("Error", "Department not found.");
            return;
        }

        List<String> doctorList = getAvailableDoctors(department, doctorType);

        if (doctorList.isEmpty()) {
            showAlert("No Doctors Available", "No doctors available with the specified type in this department.");
            return;
        }

        String doctor = selectDoctor(doctorList);
        List<String> availableSlots = doctorAvailability.get(doctor);

        if (availableSlots.isEmpty()) {
            showAlert("No Slots Available", "No slots available for this doctor today.");
            return;
        }

        String chosenSlot = selectTimeSlot(availableSlots);
        if (chosenSlot == null) return; // Exit if no slot is selected

        showAlert("Appointment Confirmed", "Appointment confirmed with " + doctor + " at " + chosenSlot + ".");

        // Perform symptom screening after booking the appointment
        ScreeningResult result = performScreening(patientId);

        // Generate a report after screening
        generateReport(patientId, doctor, result);

        // Generate a bill based on the doctor's type and apply discounts if applicable
        generateBill(patientId, doctorType, age);
    }

    private int getPatientId() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Patient ID Input");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter your Patient ID:");

        Optional<String> result = dialog.showAndWait();
        return result.map(Integer::parseInt).orElse(-1); // Return -1 if no input
    }

    private List<String> getAvailableDoctors(String department, String doctorType) {
        return Info.doctors.get(department).stream()
            .filter(doc -> {
                if (doctorType.equals("Specialist")) {
                    return doc.toLowerCase().contains("specialist");
                } else {
                    return !doc.toLowerCase().contains("specialist");
                }
            })
            .toList();
    }

    private String selectDoctor(List<String> doctorList) {
        StringBuilder doctorOptions = new StringBuilder("Choose a doctor:\n");
        for (int i = 0; i < doctorList.size(); i++) {
            doctorOptions.append((i + 1)).append(". ").append(doctorList.get(i)).append("\n");
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Doctor Selection");
        dialog.setHeaderText(null);
        dialog.setContentText(doctorOptions.toString() + "Enter the number:");

        Optional<String> result = dialog.showAndWait();
        if (result.isEmpty()) return null; // Exit if no input is provided

        int docChoice = Integer.parseInt(result.get());
        return doctorList.get(docChoice - 1);
    }

    private String selectTimeSlot(List<String> availableSlots) {
        StringBuilder slotOptions = new StringBuilder("Available time slots:\n");
        for (int i = 0; i < availableSlots.size(); i++) {
            slotOptions.append((i + 1)).append(". ").append(availableSlots.get(i)).append("\n");
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Time Slot Selection");
        dialog.setHeaderText(null);
        dialog.setContentText(slotOptions.toString() + "Choose a time slot by number:");

        Optional<String> result = dialog.showAndWait();
        if (result.isEmpty()) return null; // Exit if no input is provided

        int slotChoice = Integer.parseInt(result.get());
        return availableSlots.remove(slotChoice - 1);
    }

    // Inner class to store screening results
    public static class ScreeningResult {
        String disease;
        String severity;
        List<String> medicines;

        public ScreeningResult(String disease, String severity, List<String> medicines) {
            this.disease = disease;
            this.severity = severity;
            this.medicines = medicines;
        }
    }

    public ScreeningResult performScreening(int patientId) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Symptom Input");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter your symptoms (comma-separated):");

        Optional<String> result = dialog.showAndWait();
        if (result.isEmpty()) {
            return new ScreeningResult(null, "Unknown", Collections.emptyList()); // Exit if no input
        }

        String[] symptoms = result.get().split(",");
        String matchedDisease = null;
        String severity = "Mild";
        List<String> medicines = List.of("Rest and observe symptoms."); // Default if no match

        for (String symptom : symptoms) {
            String disease = Info.symptomToDisease.get(symptom.trim().toLowerCase());
            if (disease != null) {
                matchedDisease = disease;
                severity = Info.diseaseSeverity.get(disease);
                medicines = Info.treatments.getOrDefault(disease, medicines);
                break;  // Stop at first matching disease for simplicity
            }
        }

        if (matchedDisease != null) {
            showAlert("Possible Disease", "Possible disease: " + matchedDisease + "\nSeverity Level: " + severity);
            List<String> precautions = Info.diseasePrecautions.get(matchedDisease);
            if (precautions != null) {
                showAlert("Precautions", "Precautions: " + String.join(", ", precautions));
            }

            if (severity.equals("Severe")) {
                showAlert("Consultation Advice", "Please consult a specialist.");
            } else {
                showAlert("Consultation Advice", "General doctor consultation is sufficient.");
            }

            showAlert("Suggested Treatments", "Suggested Treatment(s): " + medicines);
        } else {
            showAlert("No Matching Disease", "No known diseases match these symptoms.");
        }

        return new ScreeningResult(matchedDisease, severity, medicines);
    }

    public void generateReport(int patientId, String doctor, ScreeningResult result) {
        String patientName = BedAllocation.patientRecords.get(patientId).getName();

        String report = "--- Appointment Report ---\n" +
                "Patient ID: " + patientId + "\n" +
                "Patient Name: " + patientName + "\n" +
                "Doctor: " + doctor + "\n" +
                "Disease: " + result.disease + "\n" +
                "Severity: " + result.severity + "\n" +
                "Medicines prescribed: " + result.medicines + "\n" +
                "---------------------------";
        showAlert("Appointment Report", report);
    }

    // Method for generating the bill based on the doctor type and applying discounts
    public void generateBill(int patientId, String doctorType, int age) {
        int consultationFee = doctorType.equals("Specialist") ? 100 : Info.CONSULTATION_FEE;
        int medicineCost = Info.MEDICINE_COST;
        int totalBill = consultationFee + medicineCost;

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
        Patient patient = BedAllocation.patientRecords.get(patientId); // Retrieve patient details

        if (patient.getMedicalHistory().toLowerCase().contains("cardiac")) { // Example condition
            totalBill *= 0.85; // 15% discount for patients with cardiac illnesses
            discountMessage.append("15% Discount for Cardiac Illness\n");
        }

        if (patient.getGender().equalsIgnoreCase("female")) { // Example condition
            totalBill *= 0.95; // 5% discount for female patients
            discountMessage.append("5% Discount for Female Patients\n");
        }

        String billDetails = "--- Billing Information ---\n" +
                "Patient ID: " + patientId + "\n" +
                "Doctor Type: " + doctorType + "\n" +
                "Consultation Fee: $" + consultationFee + "\n" +
                "Medicine Cost: $" + medicineCost + "\n" +
                discountMessage.toString() +
                "Total Bill after discounts: $" + totalBill + "\n" +
                "---------------------------";

        showAlert("Billing Information", billDetails);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
