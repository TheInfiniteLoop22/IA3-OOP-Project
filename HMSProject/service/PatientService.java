package service;

import model.Hospital;
import model.Patient;
import java.util.List;  
import java.util.ArrayList;

public class PatientService {
    private Hospital hospital;

    public PatientService(Hospital hospital) {
        this.hospital = hospital;
    }

    public void registerPatient(String name, int age, String contactNumber, String email, String registrationId) {
        Patient newPatient = new Patient(name, age, contactNumber, email, registrationId);
        hospital.addPatient(newPatient);
    }

    public List<Patient> getPatients() {
        return hospital.getPatients(); 
    }
}
