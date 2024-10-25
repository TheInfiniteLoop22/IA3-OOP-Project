package hospitalmanagementsystem;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient {
    private static int idCounter = 1000; // Starting Patient ID
    private int patientID;
    private String name;
    private String gender;
    private String contactNumber;
    private String bloodGroup;
    private String dob;
    private String medicalHistory;
    private String dateOfRegistration;
    private int roomNumber; // Added roomNumber field

    // Constructor to initialize patient details
    public Patient(String name, String gender, String contactNumber, String bloodGroup, String dob, String medicalHistory) {
        this.patientID = generatePatientID();
        this.name = name;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.bloodGroup = bloodGroup;
        this.dob = dob;
        this.medicalHistory = medicalHistory;
        this.dateOfRegistration = getCurrentDate();
        this.roomNumber = 0; // Initialize roomNumber to 0 (indicating no room allocated)
    }

    // Method to generate unique Patient ID
    private static int generatePatientID() {
        return idCounter++;
    }

    // Method to get current date as string
    private String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(new Date());
    }

    // Method to display confirmation to the user
    public void displayConfirmation() {
        System.out.println("Thank you for registering! Your Patient ID is: " + patientID);
        System.out.println("Please save this ID for future reference.");
    }

    // Display Patient information for confirmation (for demonstration purposes)
    public void displayPatientInfo() {
        System.out.println("Patient ID: " + patientID);
        System.out.println("Name: " + name);
        System.out.println("Gender: " + gender);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println("Blood Group: " + bloodGroup);
        System.out.println("Date of Birth: " + dob);
        System.out.println("Medical History: " + medicalHistory);
        System.out.println("Date of Registration: " + dateOfRegistration);
        System.out.println("Room Number: " + roomNumber); // Display room number
    }

    // Getter for patientID
    public int getPatientID() {
        return patientID;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for roomNumber
    public int getRoomNumber() {
        return roomNumber;
    }

    // Setter for roomNumber
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber; // Set the room number
    }

    public void displayConfirmation(String patientId) {
        System.out.println("Registration successful!");
        System.out.println("Patient ID: " + patientId);
    }
}
