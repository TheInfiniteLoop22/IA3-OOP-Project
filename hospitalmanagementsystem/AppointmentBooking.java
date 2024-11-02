package hospitalmanagementsystem;

import javafx.scene.control.TextInputDialog;
import java.util.Optional;

public class AppointmentBooking {
    public static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public Appointment appointment = new Appointment();

    // Method to book an appointment with either a general or specialist doctor
    public void bookAppointment(String type) {
        // Prompt for department selection
        TextInputDialog departmentDialog = new TextInputDialog();
        departmentDialog.setTitle("Appointment Booking");
        departmentDialog.setHeaderText(null);
        departmentDialog.setContentText("Enter department (Cardiology, Orthopedics, Pediatrics, Dermatology, General):");

        Optional<String> departmentResult = departmentDialog.showAndWait();

        if (departmentResult.isEmpty()) return; // Exit if no input is provided

        // Capitalize the first letter of the input department
        String department = capitalizeFirstLetter(departmentResult.get());

        // Determine if the appointment is for a general doctor or specialist
        if (type.equalsIgnoreCase("Specialist")) {
            appointment.bookAppointment(department, "Specialist");
        } else {
            appointment.bookAppointment(department, "General");
        }
    }
}
