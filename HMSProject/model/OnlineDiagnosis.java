package model;

import model.Patient;
import java.util.Scanner;

public class OnlineDiagnosis {
    private String[] symptoms;
    private String[] diseases;
    private String[] precautions;

    // Constructor
    public OnlineDiagnosis() {
        initializeDiagnosisData();
    }

    // Method to initialize symptoms, diseases, and precautions
    private void initializeDiagnosisData() {
        symptoms = new String[]{"Fever", "Cough", "Headache", "Sore Throat", "Body Aches", "Nausea"};
        diseases = new String[]{"Flu", "Cold", "Migraine", "Strep Throat", "Flu", "Food Poisoning"};
        precautions = new String[]{
            "Rest, hydration, and over-the-counter medications for relief.", // Flu
            "Stay hydrated, use throat lozenges, and rest.", // Cold
            "Rest in a dark room, stay hydrated, and consider pain relievers.", // Migraine
            "Antibiotics, warm salt water gargle, and rest.", // Strep Throat
            "Rest, hydration, and over-the-counter medications for relief.", // Flu (again)
            "Stay hydrated and eat bland foods until recovery." // Food Poisoning
        };
    }

    // Method to conduct online diagnosis
    public void diagnose(Patient patient) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select your symptoms from the following options:");
        for (String symptom : symptoms) {
            System.out.println("- " + symptom);
        }

        System.out.print("Enter your symptoms (comma-separated): ");
        String input = scanner.nextLine();
        String[] selectedSymptoms = input.split(",");

        // Array to count disease occurrences
        int[] diseaseCount = new int[diseases.length];

        for (String symptom : selectedSymptoms) {
            String trimmedSymptom = symptom.trim();
            for (int i = 0; i < symptoms.length; i++) {
                if (symptoms[i].equalsIgnoreCase(trimmedSymptom)) {
                    diseaseCount[i]++;
                    break; // Exit the loop once we find a matching symptom
                }
            }
        }

        // Display diagnosis
        boolean foundDisease = false;
        for (int i = 0; i < diseaseCount.length; i++) {
            if (diseaseCount[i] > 0) {
                foundDisease = true;
                System.out.println("Based on your symptoms, you may have: " + diseases[i]);
                System.out.println("Precautions for " + diseases[i] + ": " + precautions[i]);
            }
        }

        if (!foundDisease) {
            System.out.println("No matching disease found based on the selected symptoms.");
        }
    }
}
