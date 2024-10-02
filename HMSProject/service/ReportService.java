package service;

import model.Hospital;
import model.Patient;
import model.Report;

import java.util.ArrayList;  // Import ArrayList
import java.util.List;      // Import List

public class ReportService {
    private Hospital hospital;

    public ReportService(Hospital hospital) {
        this.hospital = hospital;
    }

    public void createReport(Patient patient, String details) {
        Report report = new Report(patient, details);
        hospital.addReport(report); // Ensure this method exists in Hospital
    }

    public List<Report> getReports() {
        return hospital.getReports(); // Ensure this method exists in Hospital
    }
}
