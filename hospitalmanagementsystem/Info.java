package hospitalmanagementsystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class Info extends Application {
    public static final List<String> departments = Arrays.asList("Cardiology", "Orthopedics", "Pediatrics", "Dermatology", "General");

    public static final Map<String, List<String>> doctors = new HashMap<>() {{
        put("Cardiology", Arrays.asList("Dr. Carter - Normal", "Dr. Lewis - Normal", "Dr. Brooks - Normal", "Dr. Brown - Specialist", "Dr. Wilson - Specialist"));
        put("Orthopedics", Arrays.asList("Dr. Taylor - Normal", "Dr. Anderson - Normal", "Dr. Thomas - Normal", "Dr. Harris - Specialist", "Dr. Adams - Specialist"));
        put("Pediatrics", Arrays.asList("Dr. King - Normal", "Dr. Hill - Normal", "Dr. Scott - Normal", "Dr. Green - Specialist", "Dr. Evans - Specialist"));
        put("Dermatology", Arrays.asList("Dr. Young - Normal", "Dr. Allen - Normal", "Dr. Perez - Normal", "Dr. Wright - Specialist", "Dr. Baker - Specialist"));
        put("General", Arrays.asList("Dr. Smith - Normal", "Dr. Johnson - Normal", "Dr. Lee - Normal", "Dr. Walker - Specialist", "Dr. Robinson - Specialist"));
    }};

    public static final Map<String, String> patientRecords = new HashMap<>();

    public static final int CONSULTATION_FEE = 50;
    public static final int MEDICINE_COST = 30;

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
        put("What are the hospital visiting hours?", "The hospital visiting hours are from 10 AM to 8 PM.");
        put("How can I book an appointment?", "You can book an appointment through our website or call our front desk.");
        put("What insurance do you accept?", "We accept a variety of insurance plans. Please check with our billing department for details.");
        put("How do I obtain my medical records?", "You can request your medical records from our medical records department.");
        put("What are the payment methods accepted?", "We accept cash, credit cards, and insurance.");
        put("How can I contact a specific department?", "You can contact a specific department through our main line or visit our website.");
        put("What should I bring for my first appointment?", "Please bring a valid ID, insurance card, and any previous medical records.");
        put("Is there a pharmacy on-site?", "Yes, we have a pharmacy located in the hospital.");
        put("What should I do in case of an emergency?", "In case of an emergency, please dial 911 or visit the nearest emergency room.");
        put("Are there parking facilities available?", "Yes, we have ample parking facilities available for patients and visitors.");
    }};

    public void displayFAQ() {
        // Create the JavaFX components for displaying FAQs
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        layout.setPadding(new javafx.geometry.Insets(20));

        Label titleLabel = new Label("Frequently Asked Questions");
        titleLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        layout.getChildren().add(titleLabel);

        for (String question : faqSection.keySet()) {
            Button questionButton = new Button(question);
            questionButton.setOnAction(event -> {
                String answer = faqSection.get(question);
                Alert answerAlert = new Alert(Alert.AlertType.INFORMATION);
                answerAlert.setTitle("FAQ Answer");
                answerAlert.setHeaderText(null);
                answerAlert.setContentText(answer);
                answerAlert.showAndWait();
            });
            layout.getChildren().add(questionButton);
        }

        Scene scene = new Scene(layout, 400, 300);
        stage.setTitle("FAQ Section");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) {
        // Optional: You can add logic to start your application here if needed.
    }
}
