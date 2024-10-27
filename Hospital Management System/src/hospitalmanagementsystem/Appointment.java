package hospitalmanagementsystem;

import java.util.*;

public class Appointment {
    private static final Map<String, List<String>> doctorAvailability = new HashMap<>();

    public Appointment() {
        // Initialize available time slots for each doctor
        for (String dept : Info.departments) {
            for (String doctor : Info.doctors.get(dept)) {
                doctorAvailability.put(doctor, new ArrayList<>(Arrays.asList("9:00 AM", "10:00 AM", "11:00 AM", "2:00 PM", "3:00 PM")));
            }
        }
    }

    public void bookAppointment(String department, String doctorType) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your Patient ID: ");
        int patientId = scanner.nextInt();

        if (!BedAllocation.patientRecords.containsKey(patientId)) {
            System.out.println("Invalid Patient ID. Please register first.");
            return;
        }

        Patient patient = BedAllocation.patientRecords.get(patientId); // Retrieve patient details
        int age = patient.getAge(); // Get the patient's age

        if (!Info.doctors.containsKey(department)) {
            System.out.println("Department not found.");
            return;
        }

        System.out.println("Available doctors in " + department + " (" + doctorType + "):");
        List<String> doctorList = Info.doctors.get(department).stream()
            .filter(doc -> {
                if (doctorType.equals("Specialist")) {
                    return doc.toLowerCase().contains("specialist");
                } else {
                    return !doc.toLowerCase().contains("specialist");
                }
            })
            .toList();

        if (doctorList.isEmpty()) {
            System.out.println("No doctors available with the specified type in this department.");
            return;
        }

        for (int i = 0; i < doctorList.size(); i++) {
            System.out.println((i + 1) + ". " + doctorList.get(i));
        }

        System.out.print("Choose a doctor by number: ");
        int docChoice = scanner.nextInt();
        scanner.nextLine();

        String doctor = doctorList.get(docChoice - 1);
        List<String> availableSlots = doctorAvailability.get(doctor);

        if (availableSlots.isEmpty()) {
            System.out.println("No slots available for this doctor today.");
            return;
        }

        System.out.println("Available time slots:");
        for (int i = 0; i < availableSlots.size(); i++) {
            System.out.println((i + 1) + ". " + availableSlots.get(i));
        }

        System.out.print("Choose a time slot by number: ");
        int slotChoice = scanner.nextInt();
        scanner.nextLine();

        String chosenSlot = availableSlots.remove(slotChoice - 1);
        System.out.println("Appointment confirmed with " + doctor + " at " + chosenSlot + ".");

        // Perform symptom screening after booking the appointment
        ScreeningResult result = performScreening(patientId);

        // Generate a report after screening
        generateReport(patientId, doctor, result);

        // Generate a bill based on the doctor's type and apply discounts if applicable
        generateBill(patientId, doctorType, age);
    }

    // Inner class to store screening results
    private static class ScreeningResult {
        String disease;
        String severity;
        List<String> medicines;

        public ScreeningResult(String disease, String severity, List<String> medicines) {
            this.disease = disease;
            this.severity = severity;
            this.medicines = medicines;
        }
    }

    private ScreeningResult performScreening(int patientId) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your symptoms (comma-separated): ");
        String[] symptoms = scanner.nextLine().split(",");

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
            System.out.println("Possible disease: " + matchedDisease);
            System.out.println("Severity Level: " + severity);

            List<String> precautions = Info.diseasePrecautions.get(matchedDisease);
            if (precautions != null) {
                System.out.println("Precautions: " + String.join(", ", precautions));
            }

            if (severity.equals("Severe")) {
                System.out.println("Please consult a specialist.");
            } else {
                System.out.println("General doctor consultation is sufficient.");
            }

            System.out.println("Suggested Treatment(s): " + medicines);
        } else {
            System.out.println("No known diseases match these symptoms.");
        }

        return new ScreeningResult(matchedDisease, severity, medicines);
    }

    private void generateReport(int patientId, String doctor, ScreeningResult result) {
        String patientName = BedAllocation.patientRecords.get(patientId).getName();

        System.out.println("\n--- Appointment Report ---");
        System.out.println("Patient ID: " + patientId);
        System.out.println("Patient Name: " + patientName);
        System.out.println("Doctor: " + doctor);
        System.out.println("Disease: " + result.disease);
        System.out.println("Severity: " + result.severity);
        System.out.println("Medicines prescribed: " + result.medicines);
        System.out.println("---------------------------");
    }

    // Method for generating the bill based on the doctor type and applying discounts
// Method for generating the bill based on the doctor type and applying discounts
private void generateBill(int patientId, String doctorType, int age) {
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
    // Check for other schemes based on additional conditions (add your conditions here)
    
    if (patient.getMedicalHistory().toLowerCase().contains("cardiac")) { // Example condition
        totalBill *= 0.85; // 15% discount for patients with cardiac illnesses
        discountMessage.append("15% Discount for Cardiac Illness\n");
    }

    if (patient.getGender().equalsIgnoreCase("female")) { // Example condition
        totalBill *= 0.95; // 5% discount for female patients
        discountMessage.append("5% Discount for Female Patients\n");
    }

    // Display billing information
    System.out.println("\n--- Billing Information ---");
    System.out.println("Patient ID: " + patientId);
    System.out.println("Doctor Type: " + doctorType);
    System.out.println("Consultation Fee: $" + consultationFee);
    System.out.println("Medicine Cost: $" + medicineCost);
    System.out.println(discountMessage.toString());
    System.out.println("Total Bill after discounts: $" + totalBill);
    System.out.println("----------------------------");
}

}
