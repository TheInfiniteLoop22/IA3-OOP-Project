package model;

public class Report {
    private Patient patient;
    private String details;

    public Report(Patient patient, String details) {
        this.patient = patient;
        this.details = details;
    }

    public Patient getPatient() { return patient; }
    public String getDetails() { return details; }
}
