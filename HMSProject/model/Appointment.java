package model;

public class Appointment {
    private Patient patient;
    private Doctor doctor;
    private String dateTime;

    public Appointment(Patient patient, Doctor doctor, String dateTime) {
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
    }

    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public String getDateTime() { return dateTime; }
}
