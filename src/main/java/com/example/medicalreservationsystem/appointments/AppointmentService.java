package com.example.medicalreservationsystem.appointments;

import com.example.medicalreservationsystem.doctors.Doctor;
import com.example.medicalreservationsystem.doctors.DoctorRepository;
import com.example.medicalreservationsystem.users.User;
import com.example.medicalreservationsystem.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,
                              DoctorRepository doctorRepository,
                              UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsForPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public Appointment getAppointment(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }


    public Appointment saveAppointment(Appointment appointment) {
        Long doctorId = appointment.getDoctor().getId();
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with ID " + doctorId + " not found"));
        appointment.setDoctor(doctor);

        Long patientId = appointment.getPatient().getId();
        User patient = userRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient with ID " + patientId + " not found"));
        appointment.setPatient(patient);

        return appointmentRepository.save(appointment);
    }
}