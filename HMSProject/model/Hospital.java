package model;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Report> reports;
//remove private
    
    public Hospital() {
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        reports = new ArrayList<>();
    }

    public void addPatient(Patient patient) { patients.add(patient); }
    public void addDoctor(Doctor doctor) { doctors.add(doctor); }
    public void addReport(Report report) { reports.add(report); }
    
    public List<Patient> getPatients() { return patients; }
    public List<Doctor> getDoctors() { return doctors; }
    public List<Report> getReports() { return reports; }
}
