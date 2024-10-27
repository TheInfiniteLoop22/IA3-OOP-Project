package hospitalmanagementsystem;

import java.util.*;

public class EmergencyHandler {
    private static final int ICU_BED_LIMIT = 3;
    private static int occupiedICUBeds = 0;
    private static final Map<Integer, String> icuPatients = new HashMap<>();
    private static final List<String> availableSpecialists = Arrays.asList("Dr. Smith - Cardiology", "Dr. Johnson - Orthopedics", "Dr. Lee - Pediatrics");

    public void handleEmergency(int severityLevel) {
        int patientId = registerPatient();
        if (assignICUBed(patientId)) {
            String specialist = assignSpecialist(severityLevel);
            performQuickScreening(patientId, specialist);
            generateEstimateBill(patientId);
        } else {
            System.out.println("All ICU beds are currently occupied. Please try again later or transfer the patient.");
        }
    }

    private int registerPatient() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter patient name: ");
        String patientName = scanner.nextLine();

        int patientId = new Random().nextInt(9000) + 1000;
        icuPatients.put(patientId, patientName);
        System.out.println("Patient registered with ID: " + patientId);
        return patientId;
    }

    private boolean assignICUBed(int patientId) {
        if (occupiedICUBeds < ICU_BED_LIMIT) {
            occupiedICUBeds++;
            System.out.println("ICU bed allocated to Patient ID " + patientId + ". Remaining ICU beds: " + (ICU_BED_LIMIT - occupiedICUBeds));
            return true;
        } else {
            return false;
        }
    }

    private String assignSpecialist(int severityLevel) {
        String specialist = availableSpecialists.get(severityLevel % availableSpecialists.size());
        System.out.println("Assigned Specialist: " + specialist);
        System.out.println("Estimated arrival in 5 minutes.");
        return specialist;
    }

    private void performQuickScreening(int patientId, String specialist) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter symptoms (comma-separated): ");
        String[] symptoms = scanner.nextLine().split(",");

        String disease = diagnoseDisease(symptoms);
        String severity = (disease != null) ? "Severe" : "Unknown";
        List<String> medicines = (disease != null) ? getMedications(disease) : List.of("Painkillers", "IV Fluids");

        System.out.println("\n--- Initial Screening Result ---");
        System.out.println("Patient ID: " + patientId);
        System.out.println("Condition: " + severity);
        System.out.println("Assigned Specialist: " + specialist);
        System.out.println("Suggested Initial Medication: " + String.join(", ", medicines));
        System.out.println("Precautions: Bed rest, monitor vitals closely.");
    }

    private String diagnoseDisease(String[] symptoms) {
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

    private List<String> getMedications(String disease) {
        Map<String, List<String>> diseaseMedications = new HashMap<>();
        diseaseMedications.put("Heart Attack", Arrays.asList("Aspirin", "Nitroglycerin"));
        diseaseMedications.put("Severe Infection", Arrays.asList("Antibiotics", "IV Fluids"));
        diseaseMedications.put("Asthma Attack", Arrays.asList("Inhaler", "Oxygen Therapy"));

        return diseaseMedications.getOrDefault(disease, List.of("Painkillers", "IV Fluids"));
    }

    private void generateEstimateBill(int patientId) {
        int icuBedFee = 300;
        int consultationFee = 150;
        int medicationFee = 75;
        int estimatedTotal = icuBedFee + consultationFee + medicationFee;

        System.out.println("\n--- Billing Estimate ---");
        System.out.println("Patient ID: " + patientId);
        System.out.println("ICU Bed Fee: $" + icuBedFee);
        System.out.println("Consultation Fee: $" + consultationFee);
        System.out.println("Medication Cost: $" + medicationFee);
        System.out.println("Estimated Total Bill: $" + estimatedTotal);
    }

    // For demo purposes, you can add a main method to test the functionality:
    public static void main(String[] args) {
        EmergencyHandler emergencyHandler = new EmergencyHandler();
        System.out.println("Emergency handling initiated:");
        emergencyHandler.handleEmergency(1);  // Sample severity level input
    }
}
