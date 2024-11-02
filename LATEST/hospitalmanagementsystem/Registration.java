package hospitalmanagementsystem;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import java.io.File;
import java.time.LocalDate;
import java.util.Optional;

public class Registration {

    public void registerPatient() {
        // Custom dialog
        Dialog<Patient> dialog = new Dialog<>();
        dialog.setTitle("Patient Registration");
        dialog.setHeaderText("Enter Patient Details");

        // Set the button types
        ButtonType registerButtonType = new ButtonType("Register", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(registerButtonType, ButtonType.CANCEL);

        // Name field
        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        // Gender options
        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("Male");
        maleRadio.setToggleGroup(genderGroup);
        RadioButton femaleRadio = new RadioButton("Female");
        femaleRadio.setToggleGroup(genderGroup);
        RadioButton otherRadio = new RadioButton("Other");
        otherRadio.setToggleGroup(genderGroup);
        maleRadio.setSelected(true); // Default selection

        // Contact number field
        TextField contactField = new TextField();
        contactField.setPromptText("Contact Number");

        // Blood group dropdown
        ComboBox<String> bloodGroupBox = new ComboBox<>();
        bloodGroupBox.getItems().addAll("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
        bloodGroupBox.setPromptText("Blood Group");

        // Age dropdown
        ComboBox<Integer> ageBox = new ComboBox<>();
        for (int i = 1; i <= 100; i++) {
            ageBox.getItems().add(i);
        }
        ageBox.setPromptText("Age");

        // Date picker for date of birth
        DatePicker dobPicker = new DatePicker();
        dobPicker.setPromptText("Date of Birth");

        // Medical history field
        TextField medicalHistoryField = new TextField();
        medicalHistoryField.setPromptText("Medical History");

        // Attach file button
        Button attachFileButton = new Button("Attach File");
        Label fileNameLabel = new Label("No file selected"); // Label to show file name

        // File chooser action
        attachFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select a File");
            File file = fileChooser.showOpenDialog(dialog.getDialogPane().getScene().getWindow());
            if (file != null) {
                fileNameLabel.setText(file.getName()); // Show the selected file name
            }
        });

        // Layout for the fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Gender:"), 0, 1);
        grid.add(maleRadio, 1, 1);
        grid.add(femaleRadio, 1, 2);
        grid.add(otherRadio, 1, 3);
        grid.add(new Label("Contact Number:"), 0, 4);
        grid.add(contactField, 1, 4);
        grid.add(new Label("Blood Group:"), 0, 5);
        grid.add(bloodGroupBox, 1, 5);
        grid.add(new Label("Age:"), 0, 6);
        grid.add(ageBox, 1, 6);
        grid.add(new Label("Date of Birth:"), 0, 7);
        grid.add(dobPicker, 1, 7);
        grid.add(new Label("Medical History:"), 0, 8);
        grid.add(medicalHistoryField, 1, 8);
        grid.add(attachFileButton, 0, 9); // Attach File button
        grid.add(fileNameLabel, 1, 9); // Label to display file name

        // Add content to the dialog
        dialog.getDialogPane().setContent(new VBox(grid));

        // Convert the result to a Patient object when the Register button is clicked
        dialog.setResultConverter(new Callback<ButtonType, Patient>() {
            @Override
            public Patient call(ButtonType buttonType) {
                if (buttonType == registerButtonType) {
                    String name = nameField.getText();
                    String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
                    String contactNumber = contactField.getText();
                    String bloodGroup = bloodGroupBox.getValue();
                    int age = ageBox.getValue();
                    LocalDate dob = dobPicker.getValue();
                    String medicalHistory = medicalHistoryField.getText();
                    Patient p = new Patient(name, gender, contactNumber, bloodGroup, dob.toString(), medicalHistory, age);
                    BedAllocation.addPatient(p);

                    // Return the new Patient object
                    return p;
                }
                return null;
            }
        });

        // Show the dialog and get the result
        Optional<Patient> result = dialog.showAndWait();
        result.ifPresent(patient -> showConfirmation("Registration Successful!", "Your Patient ID is: " + patient.getPatientID()));
    }

    private void showConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
