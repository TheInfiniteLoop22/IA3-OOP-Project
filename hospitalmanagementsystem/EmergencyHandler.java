package hospitalmanagementsystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class EmergencyHandler extends Application {
    public static final int ICU_BED_LIMIT = 3;
    public static int occupiedICUBeds = 0;
    public static final Map<Integer, String> icuPatients = new HashMap<>();
    public static final List<String> availableSpecialists = Arrays.asList("Dr. Smith - Cardiology", "Dr. Johnson - Orthopedics", "Dr. Lee - Pediatrics");

    public void handleEmergency(int severityLevel) {
        int patientId = registerPatient();
        if (assignICUBed(patientId)) {
            String specialist = assignSpecialist(severityLevel);
            performQuickScreening(patientId, specialist);
            generateEstimateBill(patientId);
        } else {
            showAlert("ICU Bed Allocation", "All ICU beds are currently occupied. Please try again later or transfer the patient.");
        }
    }

    public int registerPatient() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Patient Registration");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter patient name:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String patientName = result.get();
            int patientId = new Random().nextInt(9000) + 1000;
            icuPatients.put(patientId, patientName);
            showAlert("Registration", "Patient registered with ID: " + patientId);
            return patientId;
        }
        return -1; // Return an invalid ID if registration is canceled
    }

    public boolean assignICUBed(int patientId) {
        if (occupiedICUBeds < ICU_BED_LIMIT) {
            occupiedICUBeds++;
            showAlert("ICU Bed Allocation", "ICU bed allocated to Patient ID " + patientId + ". Remaining ICU beds: " + (ICU_BED_LIMIT - occupiedICUBeds));
            return true;
        } else {
            return false;
        }
    }

    public String assignSpecialist(int severityLevel) {
        String specialist = availableSpecialists.get(severityLevel % availableSpecialists.size());
        showAlert("Assigned Specialist", "Assigned Specialist: " + specialist + "\nEstimated arrival in 5 minutes.");
        return specialist;
    }

    public void performQuickScreening(int patientId, String specialist) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Symptom Input");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter symptoms (comma-separated):");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String[] symptoms = result.get().split(",");

            String disease = diagnoseDisease(symptoms);
            String severity = (disease != null) ? "Severe" : "Unknown";
            List<String> medicines = (disease != null) ? getMedications(disease) : List.of("Painkillers", "IV Fluids");

            StringBuilder screeningResult = new StringBuilder();
            screeningResult.append("\n--- Initial Screening Result ---\n");
            screeningResult.append("Patient ID: ").append(patientId).append("\n");
            screeningResult.append("Condition: ").append(severity).append("\n");
            screeningResult.append("Assigned Specialist: ").append(specialist).append("\n");
            screeningResult.append("Suggested Initial Medication: ").append(String.join(", ", medicines)).append("\n");
            screeningResult.append("Precautions: Bed rest, monitor vitals closely.");

            showAlert("Initial Screening Result", screeningResult.toString());
        }
    }

    public String diagnoseDisease(String[] symptoms) {
        Map<String, String> symptomToDiseaseMap = new HashMap<>();
        symptomToDiseaseMap.put("chest pain", "Heart Attack");
        symptomToDiseaseMap.put("high fever", "Severe Infection");
        symptomToDiseaseMap.put("difficulty breathing", "Asthma Attack");

        for (String symptom : symptoms) {
            String disease = symptomToDiseaseMap.get(symptom.trim().toLowerCase());
            if (disease != null) {
                return disease;
            }
        }
        return null;
    }

    public List<String> getMedications(String disease) {
        Map<String, List<String>> diseaseMedications = new HashMap<>();
        diseaseMedications.put("Heart Attack", Arrays.asList("Aspirin", "Nitroglycerin"));
        diseaseMedications.put("Severe Infection", Arrays.asList("Antibiotics", "IV Fluids"));
        diseaseMedications.put("Asthma Attack", Arrays.asList("Inhaler", "Oxygen Therapy"));

        return diseaseMedications.getOrDefault(disease, List.of("Painkillers", "IV Fluids"));
    }

    public void generateEstimateBill(int patientId) {
        int icuBedFee = 300;
        int consultationFee = 150;
        int medicationFee = 75;
        int estimatedTotal = icuBedFee + consultationFee + medicationFee;

        StringBuilder billDetails = new StringBuilder();
        billDetails.append("\n--- Billing Estimate ---\n");
        billDetails.append("Patient ID: ").append(patientId).append("\n");
        billDetails.append("ICU Bed Fee: $").append(icuBedFee).append("\n");
        billDetails.append("Consultation Fee: $").append(consultationFee).append("\n");
        billDetails.append("Medication Cost: $").append(medicationFee).append("\n");
        billDetails.append("Estimated Total Bill: $").append(estimatedTotal);

        showAlert("Billing Estimate", billDetails.toString());
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Emergency Handler");
        VBox vbox = new VBox(10);
        
        Button handleEmergencyButton = new Button("Handle Emergency");
        handleEmergencyButton.setOnAction(e -> handleEmergency(1)); // Sample severity level input

        vbox.getChildren().addAll(handleEmergencyButton);
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
