package hospitalmanagementsystem;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class HardCodedData {

    public static void displayInfo() {
        StringBuilder infoBuilder = new StringBuilder();

       
        infoBuilder.append("\n----- Hospital Information -----\n");

        // Display Consultation and Medicine Costs
        infoBuilder.append("\nConsultation Fee: $").append(Info.CONSULTATION_FEE).append("\n");
        infoBuilder.append("Medicine Cost (fixed): $").append(Info.MEDICINE_COST).append("\n");

      


        // Display discount schemes
        infoBuilder.append("\nDiscount Schemes:\n");
        infoBuilder.append("1. 10% discount for senior citizens.(above 65 yrs old)\n");
        infoBuilder.append("2. 20% discount for infants.(below 5 yrs old)\n");
        infoBuilder.append("3. 15% discount for patients with cardiac illnesses(cardiac in medical history)\n");
        infoBuilder.append("4. 5% discount for female patients.\n");

        infoBuilder.append("\n--------------------------------");

      
        Alert infoAlert = new Alert(AlertType.INFORMATION);
        infoAlert.setTitle("Hospital Information");
        infoAlert.setHeaderText(null);
        infoAlert.setContentText(infoBuilder.toString());
        infoAlert.showAndWait();
    }
}
