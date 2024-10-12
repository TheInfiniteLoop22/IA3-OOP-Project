package app;

import model.Appointment;
import model.Doctor;
import model.Hospital;
import model.Patient;
import model.Report;
import model.Treatment;
import service.AppointmentService;
import service.DoctorService;
import service.PatientService;
import service.ReportService;
import service.OnlineDiagnosis;

//added comment

import java.util.Scanner;

public class HMSApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hospital hospital = new Hospital();
        PatientService patientService = new PatientService(hospital);
        DoctorService doctorService = new DoctorService(hospital);
        AppointmentService appointmentService = new AppointmentService(hospital);
        ReportService reportService = new ReportService(hospital);
        OnlineDiagnosis onlineDiagnosis = new OnlineDiagnosis(); // Create an instance of OnlineDiagnosis

        while (true) {
            System.out.println("Welcome to the Hospital Management System");
            System.out.println("1. Register Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. Create Report");
            System.out.println("5. View Patients");
            System.out.println("6. View Appointments");
            System.out.println("7. View Reports");
            System.out.println("8. Online Diagnosis"); // New option for online diagnosis
            System.out.println("9. Exit");
            System.out.print("Please choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Register Patient
                    System.out.print("Enter patient name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter age: ");
                    while (!scanner.hasNextInt()) {
                        System.out.print("Invalid input. Please enter a valid age: ");
                        scanner.next(); // Consume the invalid input
                    }
                    int age = scanner.nextInt();  // Read age as int
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter contact number: ");
                    String contactNumber = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter registration ID: ");
                    String registrationId = scanner.nextLine();
                    patientService.registerPatient(name, age, contactNumber, email, registrationId); // Pass as int
                    break;

                case 2: // Add Doctor
                    System.out.print("Enter doctor name: ");
                    String doctorName = scanner.nextLine();
                    System.out.print("Enter specialization: ");
                    String specialization = scanner.nextLine();
                    System.out.print("Enter contact number: "); // Added contact number
                    String docContactNumber = scanner.nextLine(); // Added contact number for doctor
                    doctorService.addDoctor(doctorName, specialization, docContactNumber); // Adjusted parameters
                    break;

                case 3: // Schedule Appointment
                    System.out.print("Enter patient name: ");
                    String pName = scanner.nextLine();
                    Patient patient = findPatientByName(patientService, pName);
                    if (patient != null) {
                        System.out.print("Enter doctor name: ");
                        String dName = scanner.nextLine();
                        Doctor doctor = findDoctorByName(doctorService, dName);
                        if (doctor != null) {
                            System.out.print("Enter appointment date and time: ");
                            String dateTime = scanner.nextLine();
                            appointmentService.scheduleAppointment(patient, doctor, dateTime);
                        } else {
                            System.out.println("Doctor not found.");
                        }
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;

                case 4: // Create Report
                    System.out.print("Enter patient name: ");
                    String reportPatientName = scanner.nextLine();
                    Patient reportPatient = findPatientByName(patientService, reportPatientName);
                    if (reportPatient != null) {
                        System.out.print("Enter report details: ");
                        String reportDetails = scanner.nextLine();
                        reportService.createReport(reportPatient, reportDetails);
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;

                case 5: // View Patients
                    System.out.println("Patients:");
                    for (Patient p : patientService.getPatients()) {
                        System.out.println("Name: " + p.getName() + ", Age: " + p.getAge());
                    }
                    break;

                case 6: // View Appointments
                    System.out.println("Appointments:");
                    for (Appointment appt : appointmentService.getAppointments()) {
                        System.out.println("Patient: " + appt.getPatient().getName() + ", Doctor: " + appt.getDoctor().getName() + ", Date: " + appt.getDateTime());
                    }
                    break;

                case 7: // View Reports
                    System.out.println("Reports:");
                    for (Report r : reportService.getReports()) {
                        System.out.println("Patient: " + r.getPatient().getName() + ", Details: " + r.getDetails());
                    }
                    break;

                case 8: // Online Diagnosis
                    System.out.print("Enter patient name: ");
                    String diagnosisPatientName = scanner.nextLine();
                    Patient diagnosisPatient = findPatientByName(patientService, diagnosisPatientName);
                    if (diagnosisPatient != null) {
                        onlineDiagnosis.diagnose(diagnosisPatient); // Call the diagnose method
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;

                case 9: // Exit
                    System.out.println("Exiting the application.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static Patient findPatientByName(PatientService patientService, String name) {
        for (Patient p : patientService.getPatients()) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    private static Doctor findDoctorByName(DoctorService doctorService, String name) {
        for (Doctor d : doctorService.getDoctors()) {
            if (d.getName().equalsIgnoreCase(name)) {
                return d;
            }
        }
        return null;
    }
}
