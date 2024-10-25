package hospitalmanagementsystem;

import java.util.*;

public class InitialScreening {

    public void performScreening(Patient patient) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Initial Screening. Dr. Johnson will assist you.");
        System.out.print("Please enter your Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your Patient ID: ");
        String patientID = scanner.nextLine();

        System.out.print("Enter your symptoms (comma-separated): ");
        String[] symptoms = scanner.nextLine().split(",");

        // Map symptoms to possible diseases and severity
        List<String> possibleDiseases = new ArrayList<>();
        String severity = "Mild";

        for (String symptom : symptoms) {
            String disease = Info.symptomToDisease.get(symptom.trim().toLowerCase());
            if (disease != null) {
                possibleDiseases.add(disease);
                severity = Info.diseaseSeverity.get(disease);
            }
        }

        if (possibleDiseases.isEmpty()) {
            System.out.println("No known diseases match these symptoms.");
            return;
        }

        System.out.println("Possible disease(s): " + String.join(", ", possibleDiseases));
        System.out.println("Severity Level: " + severity);

        // Suggest the appropriate department for consultation
        String recommendedDepartment = Info.diseaseToDepartment.get(possibleDiseases.get(0));
        
        // Check if the department exists in the hospital
        if (recommendedDepartment != null && !Info.departments.contains(recommendedDepartment)) {
            System.out.println("Note: This hospital does not have a dedicated " + recommendedDepartment + " department. "
                    + "You may consider seeking consultation at another hospital for specialized care.");
        } else {
            System.out.println("You should consult a doctor from the " + recommendedDepartment + " department.");
        }

        // Display precautions for the first disease
        List<String> precautions = Info.diseasePrecautions.get(possibleDiseases.get(0));
        if (precautions != null) {
            System.out.println("Precautions: " + String.join(", ", precautions));
        }

        // Guidance based on severity
        if (severity.equals("Severe")) {
            System.out.println("Please schedule an appointment with a specialist doctor.");
        } else {
            System.out.println("You may schedule an appointment with a general doctor.");
        }

        System.out.println("Suggested Treatment(s): " + Info.treatments.getOrDefault(possibleDiseases.get(0), List.of("Rest and observe symptoms.")));
    }
}
