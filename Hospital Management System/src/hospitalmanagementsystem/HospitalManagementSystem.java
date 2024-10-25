package hospitalmanagementsystem;

import java.util.Scanner;

public class HospitalManagementSystem {
    private Scanner scanner = new Scanner(System.in);
    private Registration registration = new Registration();
    private BedAllocation bedAllocation = new BedAllocation();
    private InitialScreening screening = new InitialScreening();
    private AppointmentBooking appointmentBooking = new AppointmentBooking();

    public void start() {
        while (true) {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Register Patient");
            System.out.println("2. Allocate Bed");
            System.out.println("3. Initial Screening");
            System.out.println("4. Book Appointment");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            // Error handling for input
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number corresponding to the options.");
                scanner.nextLine(); // Clear the invalid input
                continue; // Restart the loop
            }

            switch (choice) {
                case 1 -> registration.registerPatient();
                case 2 -> bedAllocation.displayMenu();
                case 3 -> performScreening();
                case 4 -> bookAppointment();
                case 5 -> {
                    System.out.println("Exiting system. Thank you!");
                    return; // Exit the program
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void performScreening() {
        System.out.print("Enter Patient ID: ");
        int id = scanner.nextInt();
        Patient patient = BedAllocation.getPatientById(id);

        if (patient != null) {
            screening.performScreening(patient);
        } else {
            System.out.println("No patient found with the given ID.");
        }
    }

    private void bookAppointment() {
        System.out.print("Appointment type (Normal/Specialist): ");
        String type = scanner.nextLine();
        appointmentBooking.bookAppointment(type);
    }

    public static void main(String[] args) {
        HospitalManagementSystem system = new HospitalManagementSystem();
        system.start();
    }
}
