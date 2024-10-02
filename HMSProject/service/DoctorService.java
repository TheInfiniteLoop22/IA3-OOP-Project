package service;

import model.Doctor;
import model.Hospital;

import java.util.List;

public class DoctorService {
    private Hospital hospital;

    public DoctorService(Hospital hospital) {
        this.hospital = hospital;
    }

    public void addDoctor(String name, String specialization, String contactNumber) {
        Doctor doctor = new Doctor(name, specialization, contactNumber);
        hospital.addDoctor(doctor);
    }

    public List<Doctor> getDoctors() {
        return hospital.getDoctors();
    }
}
