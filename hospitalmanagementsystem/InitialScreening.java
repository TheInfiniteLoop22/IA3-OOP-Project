package hospitalmanagementsystem;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InitialScreening {

    public void performScreening(Patient patient) {
        // Welcome message
        Alert welcomeAlert = new Alert(Alert.AlertType.INFORMATION);
        welcomeAlert.setTitle("Welcome to Initial Screening");
        welcomeAlert.setHeaderText(null);
        welcomeAlert.setContentText("Welcome to the Initial Screening, " + patient.getName() + ". Dr. Johnson will assist you.");
        welcomeAlert.showAndWait();

        // Input: Symptoms
        TextInputDialog symptomsDialog = new TextInputDialog();
        symptomsDialog.setTitle("Initial Screening");
        symptomsDialog.setHeaderText(null);
        symptomsDialog.setContentText("Enter your symptoms (comma-separated):");
        Optional<String> symptomsResult = symptomsDialog.showAndWait();
        if (symptomsResult.isEmpty()) return;
        String[] symptoms = symptomsResult.get().split(",");

        // Process symptoms and map to diseases
        List<String> possibleDiseases = new ArrayList<>();
        String severity = "Mild";

        for (String symptom : symptoms) {
            String disease = Info.symptomToDisease.get(symptom.trim().toLowerCase());
            if (disease != null) {
                possibleDiseases.add(disease);
                severity = Info.diseaseSeverity.getOrDefault(disease, "Mild");
            }
        }

        // Show possible diseases and severity
        if (possibleDiseases.isEmpty()) {
            Alert noDiseaseAlert = new Alert(Alert.AlertType.INFORMATION);
            noDiseaseAlert.setTitle("Screening Result");
            noDiseaseAlert.setHeaderText(null);
            noDiseaseAlert.setContentText("No known diseases match these symptoms.");
            noDiseaseAlert.showAndWait();
            return;
        }

        Alert diseaseAlert = new Alert(Alert.AlertType.INFORMATION);
        diseaseAlert.setTitle("Possible Disease(s)");
        diseaseAlert.setHeaderText(null);
        diseaseAlert.setContentText("Possible disease(s): " + String.join(", ", possibleDiseases) + 
                                    "\nSeverity Level: " + severity);
        diseaseAlert.showAndWait();

        // Department recommendation
        String recommendedDepartment = Info.diseaseToDepartment.get(possibleDiseases.get(0));
        if (recommendedDepartment != null && !Info.departments.contains(recommendedDepartment)) {
            Alert noDepartmentAlert = new Alert(Alert.AlertType.INFORMATION);
            noDepartmentAlert.setTitle("Department Recommendation");
            noDepartmentAlert.setHeaderText(null);
            noDepartmentAlert.setContentText("Note: This hospital does not have a dedicated " + recommendedDepartment +
                                             " department. You may consider seeking consultation at another hospital for specialized care.");
            noDepartmentAlert.showAndWait();
        } else {
            Alert departmentAlert = new Alert(Alert.AlertType.INFORMATION);
            departmentAlert.setTitle("Department Recommendation");
            departmentAlert.setHeaderText(null);
            departmentAlert.setContentText("You should consult a doctor from the " + recommendedDepartment + " department.");
            departmentAlert.showAndWait();
        }

        // Display precautions for the first disease
        List<String> precautions = Info.diseasePrecautions.get(possibleDiseases.get(0));
        if (precautions != null) {
            Alert precautionsAlert = new Alert(Alert.AlertType.INFORMATION);
            precautionsAlert.setTitle("Precautions");
            precautionsAlert.setHeaderText(null);
            precautionsAlert.setContentText("Precautions: " + String.join(", ", precautions));
            precautionsAlert.showAndWait();
        }

        // Guidance based on severity
        String guidanceMessage = severity.equals("Severe") 
            ? "Please schedule an appointment with a specialist doctor." 
            : "You may schedule an appointment with a general doctor.";
        Alert guidanceAlert = new Alert(Alert.AlertType.INFORMATION);
        guidanceAlert.setTitle("Guidance");
        guidanceAlert.setHeaderText(null);
        guidanceAlert.setContentText(guidanceMessage);
        guidanceAlert.showAndWait();

        // Suggested treatments
        List<String> treatments = Info.treatments.getOrDefault(possibleDiseases.get(0), List.of("Rest and observe symptoms."));
        Alert treatmentAlert = new Alert(Alert.AlertType.INFORMATION);
        treatmentAlert.setTitle("Suggested Treatments");
        treatmentAlert.setHeaderText(null);
        treatmentAlert.setContentText("Suggested Treatment(s): " + String.join(", ", treatments));
        treatmentAlert.showAndWait();
    }
}
