package com.example.medicalreservationsystem.appointments;

import com.example.medicalreservationsystem.doctors.Doctor;
import com.example.medicalreservationsystem.doctors.DoctorRepository;
import com.example.medicalreservationsystem.users.User;
import com.example.medicalreservationsystem.users.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentServiceTest {
    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;
    private UserRepository userRepository;
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        appointmentRepository = Mockito.mock(AppointmentRepository.class);
        doctorRepository = Mockito.mock(DoctorRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        appointmentService = new AppointmentService(appointmentRepository, doctorRepository, userRepository);
    }

    @Test
    void testGetAllAppointments() {
        Appointment a1 = new Appointment();
        Appointment a2 = new Appointment();

        when(appointmentRepository.findAll()).thenReturn(Arrays.asList(a1, a2));

        List<Appointment> result = appointmentService.getAllAppointments();
        assertEquals(2, result.size());
        verify(appointmentRepository, times(1)).findAll();
    }

    @Test
    void testSaveAppointment() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        User user = new User();
        user.setId(2L);

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(user);

        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user));
        when(appointmentRepository.save(appointment)).thenReturn(appointment);

        Appointment result = appointmentService.saveAppointment(appointment);
        assertNotNull(result);
        verify(doctorRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findById(2L);
        verify(appointmentRepository, times(1)).save(appointment);
    }
}
