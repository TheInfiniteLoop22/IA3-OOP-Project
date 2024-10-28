package hospitalmanagementsystem;

import javafx.scene.control.Alert;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient {
    public static int idCounter = 1000; // Starting Patient ID
    public int patientID;
    public String name;
    public String gender;
    public String contactNumber;
    public String bloodGroup;
    public String dob;
    public String medicalHistory;
    public String dateOfRegistration;
    public int roomNumber; // Added roomNumber field
    public int age; // Added age variable

    public Patient(String name, String gender, String contactNumber, String bloodGroup, String dob, String medicalHistory, int age) {
        this.patientID = generatePatientID();
        this.name = name;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.bloodGroup = bloodGroup;
        this.dob = dob;
        this.medicalHistory = medicalHistory;
        this.dateOfRegistration = getCurrentDate();
        this.roomNumber = 0; // Initialize roomNumber to 0 (indicating no room allocated)
        this.age = age; // Set the age
    }

    // Method to generate unique Patient ID
    public static int generatePatientID() {
        return idCounter++;
    }

    // Method to get current date as string
    public String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(new Date());
    }

    // Method to display confirmation to the user using JavaFX Alert
    public void displayConfirmation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Thank you for registering! Your Patient ID is: " + patientID + "\nPlease save this ID for future reference.");
        alert.showAndWait();
    }

    // Display Patient information for confirmation (for demonstration purposes)
    public void displayPatientInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Patient Information");
        alert.setHeaderText(null);
        alert.setContentText("Patient ID: " + patientID + "\n" +
                             "Name: " + name + "\n" +
                             "Gender: " + gender + "\n" +
                             "Contact Number: " + contactNumber + "\n" +
                             "Blood Group: " + bloodGroup + "\n" +
                             "Date of Birth: " + dob + "\n" +
                             "Medical History: " + medicalHistory + "\n" +
                             "Date of Registration: " + dateOfRegistration + "\n" +
                             "Room Number: " + roomNumber + "\n" +
                             "Age: " + age);
        alert.showAndWait();
    }

    // Getter for patientID
    public int getPatientID() {
        return patientID;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for gender
    public String getGender() {
        return gender; // Return the gender
    }

    // Getter for medicalHistory
    public String getMedicalHistory() {
        return medicalHistory; // Return the medical history
    }

    // Getter for roomNumber
    public int getRoomNumber() {
        return roomNumber;
    }

    // Setter for roomNumber
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber; // Set the room number
    }

    // Getter for age
    public int getAge() {
        return age; // Return the age
    }
}
