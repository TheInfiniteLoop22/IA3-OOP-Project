package service;

import model.Appointment;
import model.Doctor;
import model.Patient;
import model.Hospital;

import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private Hospital hospital;
    private List<Appointment> appointments;

    public AppointmentService(Hospital hospital) {
        this.hospital = hospital;
        this.appointments = new ArrayList<>();
    }

    public void scheduleAppointment(Patient patient, Doctor doctor, String dateTime) {
        Appointment appointment = new Appointment(patient, doctor, dateTime);
        appointments.add(appointment);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
}
