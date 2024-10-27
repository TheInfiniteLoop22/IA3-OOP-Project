package hospitalmanagementsystem;

public class HardCodedData {

    public static void displayInfo() {
        System.out.println("\n----- Hospital Information -----");

        // Display Departments and Doctors
        System.out.println("Departments and Doctors:");
        Info.departments.forEach(department -> {
            System.out.println("\n" + department + " Department:");
            Info.doctors.get(department).forEach(doctor -> System.out.println(" - " + doctor));
        });

        // Display Consultation and Medicine Costs
        System.out.println("\nConsultation Fee: $" + Info.CONSULTATION_FEE);
        System.out.println("Medicine Cost (fixed): $" + Info.MEDICINE_COST);

        // Display symptom-to-disease mapping (sample for patient reference)
        System.out.println("\nSymptom to Disease Mapping:");
        Info.symptomToDisease.forEach((symptom, disease) -> {
            System.out.println("Symptom: " + symptom + " -> Disease: " + disease);
        });

        // Display disease severity levels
        System.out.println("\nDisease Severity Levels:");
        Info.diseaseSeverity.forEach((disease, severity) -> {
            System.out.println("Disease: " + disease + " -> Severity: " + severity);
        });

        // Display available treatments
        System.out.println("\nTreatments:");
        Info.treatments.forEach((disease, treatmentList) -> {
            System.out.print("Disease: " + disease + " -> Treatment: ");
            System.out.println(String.join(", ", treatmentList));
        });

        // Display discount schemes
        System.out.println("\nDiscount Schemes:");
        System.out.println("1. 10% discount for senior citizens.(above 65 yrs old)");
        System.out.println("2. 20% discount for infants.(below 5 yrs old)");
        System.out.println("3. 15% discount for patients with cardiac illnesses(cardiac in medical history)");
        System.out.println("4. 5% discount for female patients.");

        System.out.println("\n--------------------------------");
    }
}
