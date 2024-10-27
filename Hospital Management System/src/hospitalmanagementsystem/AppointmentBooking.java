package hospitalmanagementsystem;

import java.util.Scanner;

public class AppointmentBooking {
    private Appointment appointment = new Appointment();

    // Method to book an appointment with either a general or specialist doctor
    public void bookAppointment(String type) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter department (Cardiology, Orthopedics, Pediatrics, Dermatology, General): ");
        String department = scanner.nextLine();

        // Determine if the appointment is for a general doctor or specialist
        if (type.equalsIgnoreCase("Specialist")) {
            appointment.bookAppointment(department, "Specialist");
        } else {
            appointment.bookAppointment(department, "General");
        }
    }
}
