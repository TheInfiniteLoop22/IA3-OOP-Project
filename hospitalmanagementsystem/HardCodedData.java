package hospitalmanagementsystem;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class HardCodedData {

    public static void displayInfo() {
        StringBuilder infoBuilder = new StringBuilder();

        // Hospital Information Header
        infoBuilder.append("\n----- Hospital Information -----\n");

        // Display Departments and Doctors
        infoBuilder.append("Departments and Doctors:\n");
        Info.departments.forEach(department -> {
            infoBuilder.append("\n").append(department).append(" Department:\n");
            Info.doctors.get(department).forEach(doctor -> infoBuilder.append(" - ").append(doctor).append("\n"));
        });

        // Display Consultation and Medicine Costs
        infoBuilder.append("\nConsultation Fee: $").append(Info.CONSULTATION_FEE).append("\n");
        infoBuilder.append("Medicine Cost (fixed): $").append(Info.MEDICINE_COST).append("\n");

        // Display symptom-to-disease mapping
        infoBuilder.append("\nSymptom to Disease Mapping:\n");
        Info.symptomToDisease.forEach((symptom, disease) -> {
            infoBuilder.append("Symptom: ").append(symptom).append(" -> Disease: ").append(disease).append("\n");
        });

        // Display disease severity levels
        infoBuilder.append("\nDisease Severity Levels:\n");
        Info.diseaseSeverity.forEach((disease, severity) -> {
            infoBuilder.append("Disease: ").append(disease).append(" -> Severity: ").append(severity).append("\n");
        });

        // Display available treatments
        infoBuilder.append("\nTreatments:\n");
        Info.treatments.forEach((disease, treatmentList) -> {
            infoBuilder.append("Disease: ").append(disease).append(" -> Treatment: ")
                       .append(String.join(", ", treatmentList)).append("\n");
        });

        // Display discount schemes
        infoBuilder.append("\nDiscount Schemes:\n");
        infoBuilder.append("1. 10% discount for senior citizens.(above 65 yrs old)\n");
        infoBuilder.append("2. 20% discount for infants.(below 5 yrs old)\n");
        infoBuilder.append("3. 15% discount for patients with cardiac illnesses(cardiac in medical history)\n");
        infoBuilder.append("4. 5% discount for female patients.\n");

        infoBuilder.append("\n--------------------------------");

        // Show information in a JavaFX Alert
        Alert infoAlert = new Alert(AlertType.INFORMATION);
        infoAlert.setTitle("Hospital Information");
        infoAlert.setHeaderText(null);
        infoAlert.setContentText(infoBuilder.toString());
        infoAlert.showAndWait();
    }
}
