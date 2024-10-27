package hospitalmanagementsystem;

import java.util.*;

public class Info {
    public static final List<String> departments = Arrays.asList("Cardiology", "Orthopedics", "Pediatrics", "Dermatology", "General");

    // Department doctors: each department has 3 normal doctors and 2 specialists
    public static final Map<String, List<String>> doctors = new HashMap<>() {{
        put("Cardiology", Arrays.asList("Dr. Carter - Normal", "Dr. Lewis - Normal", "Dr. Brooks - Normal", "Dr. Brown - Specialist", "Dr. Wilson - Specialist"));
        put("Orthopedics", Arrays.asList("Dr. Taylor - Normal", "Dr. Anderson - Normal", "Dr. Thomas - Normal", "Dr. Harris - Specialist", "Dr. Adams - Specialist"));
        put("Pediatrics", Arrays.asList("Dr. King - Normal", "Dr. Hill - Normal", "Dr. Scott - Normal", "Dr. Green - Specialist", "Dr. Evans - Specialist"));
        put("Dermatology", Arrays.asList("Dr. Young - Normal", "Dr. Allen - Normal", "Dr. Perez - Normal", "Dr. Wright - Specialist", "Dr. Baker - Specialist"));
        put("General", Arrays.asList("Dr. Smith - Normal", "Dr. Johnson - Normal", "Dr. Lee - Normal", "Dr. Walker - Specialist", "Dr. Robinson - Specialist"));
    }};

    // New variable to store patient records
    public static final Map<String, String> patientRecords = new HashMap<>(); // Assuming the key is Patient ID and value is Patient Name

    // Fixed amounts for consultation and medicines
    public static final int CONSULTATION_FEE = 50; // Fixed consultation fee
    public static final int MEDICINE_COST = 30; // Fixed medicine cost

    // Symptoms to disease mapping
    public static final Map<String, String> symptomToDisease = new HashMap<>() {{
        put("chest pain", "Heart Disease");
        put("joint pain", "Arthritis");
        put("fever", "Infection");
        put("rash", "Skin Allergy");
        put("cough", "Cold/Flu");
        put("abdominal pain", "Gastritis");
        put("headache", "Migraine");
        put("shortness of breath", "Asthma");
        put("back pain", "Herniated Disc");
        put("dizziness", "Vertigo");
        put("nausea", "Food Poisoning");
        put("fatigue", "Anemia");
        put("weight loss", "Hyperthyroidism");
        put("muscle cramps", "Electrolyte Imbalance");
        put("sore throat", "Strep Throat");
        put("urinary pain", "Urinary Tract Infection");
        put("blurred vision", "Diabetes");
        put("itchy skin", "Eczema");
        put("insomnia", "Sleep Apnea");
        put("loss of appetite", "Chronic Kidney Disease");
    }};

    // Disease to severity mapping
    public static final Map<String, String> diseaseSeverity = new HashMap<>() {{
        put("Heart Disease", "Severe");
        put("Arthritis", "Moderate");
        put("Infection", "Mild");
        put("Skin Allergy", "Moderate");
        put("Cold/Flu", "Mild");
        put("Gastritis", "Mild");
        put("Migraine", "Moderate");
        put("Asthma", "Severe");
        put("Herniated Disc", "Moderate");
        put("Vertigo", "Moderate");
        put("Food Poisoning", "Mild");
        put("Anemia", "Moderate");
        put("Hyperthyroidism", "Moderate");
        put("Electrolyte Imbalance", "Severe");
        put("Strep Throat", "Mild");
        put("Urinary Tract Infection", "Moderate");
        put("Diabetes", "Severe");
        put("Eczema", "Mild");
        put("Sleep Apnea", "Moderate");
        put("Chronic Kidney Disease", "Severe");
    }};

    // Disease to treatments mapping
    public static final Map<String, List<String>> treatments = new HashMap<>() {{
        put("Heart Disease", Arrays.asList("Aspirin", "Beta-blockers"));
        put("Arthritis", Arrays.asList("Pain relievers", "Anti-inflammatories"));
        put("Infection", Arrays.asList("Antibiotics", "Rest"));
        put("Skin Allergy", Arrays.asList("Antihistamines", "Topical creams"));
        put("Cold/Flu", Arrays.asList("Rest", "Fluids"));
        put("Gastritis", Arrays.asList("Antacids", "Avoid spicy foods"));
        put("Migraine", Arrays.asList("Pain relievers", "Dark room rest"));
        put("Asthma", Arrays.asList("Inhalers", "Avoid allergens"));
        put("Herniated Disc", Arrays.asList("Pain relievers", "Physical therapy"));
        put("Vertigo", Arrays.asList("Vestibular rehabilitation", "Medications"));
        put("Food Poisoning", Arrays.asList("Rehydration", "Rest"));
        put("Anemia", Arrays.asList("Iron supplements", "Dietary changes"));
        put("Hyperthyroidism", Arrays.asList("Anti-thyroid medications", "Radioactive iodine"));
        put("Electrolyte Imbalance", Arrays.asList("Hydration", "Dietary adjustments"));
        put("Strep Throat", Arrays.asList("Antibiotics", "Rest"));
        put("Urinary Tract Infection", Arrays.asList("Antibiotics", "Hydration"));
        put("Diabetes", Arrays.asList("Insulin", "Dietary management"));
        put("Eczema", Arrays.asList("Moisturizers", "Topical steroids"));
        put("Sleep Apnea", Arrays.asList("CPAP therapy", "Weight loss"));
        put("Chronic Kidney Disease", Arrays.asList("Diet changes", "Medications"));
    }};

    // Disease to department mapping
    public static final Map<String, String> diseaseToDepartment = new HashMap<>() {{
        put("Heart Disease", "Cardiology");
        put("Arthritis", "Orthopedics");
        put("Infection", "General");
        put("Skin Allergy", "Dermatology");
        put("Cold/Flu", "General");
        put("Gastritis", "General");
        put("Migraine", "General");
        put("Asthma", "Pulmonary");
        put("Herniated Disc", "Orthopedics");
        put("Vertigo", "Neurology");
        put("Food Poisoning", "General");
        put("Anemia", "General");
        put("Hyperthyroidism", "Endocrinology");
        put("Electrolyte Imbalance", "General");
        put("Strep Throat", "General");
        put("Urinary Tract Infection", "Urology");
        put("Diabetes", "Endocrinology");
        put("Eczema", "Dermatology");
        put("Sleep Apnea", "Pulmonary");
        put("Chronic Kidney Disease", "Nephrology");
    }};

    // Disease to precautions mapping
    public static final Map<String, List<String>> diseasePrecautions = new HashMap<>() {{
        put("Heart Disease", Arrays.asList("Regular exercise", "Healthy diet", "Avoid smoking"));
        put("Arthritis", Arrays.asList("Maintain a healthy weight", "Stay active", "Use heat and cold therapy"));
        put("Infection", Arrays.asList("Wash hands frequently", "Avoid close contact with sick individuals"));
        put("Skin Allergy", Arrays.asList("Avoid known allergens", "Use hypoallergenic products"));
        put("Cold/Flu", Arrays.asList("Get vaccinated", "Practice good hygiene"));
        put("Gastritis", Arrays.asList("Avoid spicy foods", "Eat smaller meals"));
        put("Migraine", Arrays.asList("Identify triggers", "Stay hydrated"));
        put("Asthma", Arrays.asList("Avoid allergens", "Use inhalers as prescribed"));
        put("Herniated Disc", Arrays.asList("Maintain good posture", "Avoid heavy lifting"));
        put("Vertigo", Arrays.asList("Avoid sudden movements", "Stay hydrated"));
        put("Food Poisoning", Arrays.asList("Wash hands before handling food", "Cook food thoroughly"));
        put("Anemia", Arrays.asList("Eat iron-rich foods", "Consider supplements"));
        put("Hyperthyroidism", Arrays.asList("Regular check-ups", "Avoid excessive iodine"));
        put("Electrolyte Imbalance", Arrays.asList("Stay hydrated", "Maintain a balanced diet"));
        put("Strep Throat", Arrays.asList("Practice good hygiene", "Avoid close contact with others"));
        put("Urinary Tract Infection", Arrays.asList("Stay hydrated", "Urinate after intercourse"));
        put("Diabetes", Arrays.asList("Monitor blood sugar levels", "Follow a healthy diet"));
        put("Eczema", Arrays.asList("Moisturize regularly", "Avoid irritants"));
        put("Sleep Apnea", Arrays.asList("Maintain a healthy weight", "Avoid alcohol"));
        put("Chronic Kidney Disease", Arrays.asList("Manage blood pressure", "Follow a low-sodium diet"));
    }};

    public static final Map<String, String> faqSection = new HashMap<>() {{
        put("What are the hospital visiting hours?", "Visiting hours are from 10 AM to 6 PM daily.");
        put("How can I book an appointment?", "Appointments can be booked by calling our reception or through our online portal.");
        put("What are the room charges?", "Room charges vary depending on the type of room. Please contact reception for specific rates.");
        put("Are there discounts available for senior citizens?", "Yes, senior citizens are eligible for a 10% discount on consultation fees.");
        put("What services are available in the emergency department?", "The emergency department is open 24/7 with specialized doctors on call.");
        put("How can I get my medical records?", "Medical records can be requested from the medical records department with a valid ID.");
        put("What payment methods are accepted?", "We accept cash, credit/debit cards, and most insurance providers.");
        put("Is there a pharmacy on-site?", "Yes, our pharmacy is open 24/7 and is located on the ground floor.");
        put("Do you offer online consultations?", "Yes, online consultations are available. Please contact reception for more details.");
        put("Are there facilities for overnight stays for family members?", "Yes, a limited number of rooms are available for family members at an additional cost.");
    }};

    public static void displayFAQ() {
        Scanner scanner = new Scanner(System.in);

        List<String> questions = new ArrayList<>(faqSection.keySet());
        System.out.println("\n-----------------------------------\n");
        System.out.println("--- Frequently Asked Questions ---");
        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + ". " + questions.get(i));
        }

        // Prompt user for a question choice
        System.out.print("\nEnter the number of the question you want to ask: ");
        int choice = scanner.nextInt();

        // Validate the choice and display the corresponding answer
        if (choice > 0 && choice <= questions.size()) {
            String selectedQuestion = questions.get(choice - 1);
            System.out.println("Answer: " + faqSection.get(selectedQuestion));
        } else {
            System.out.println("Invalid choice. Please select a valid question number.");
        }
    }
}
