package com.example.medicalreservationsystem.doctors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DoctorServiceTest {
    private DoctorRepository doctorRepository;
    private DoctorService doctorService;

    @BeforeEach
    void setUp() {
        doctorRepository = Mockito.mock(DoctorRepository.class);
        doctorService = new DoctorService(doctorRepository);
    }

    @Test
    void testGetAllDoctors() {
        Doctor doc1 = new Doctor();
        doc1.setName("Dr. A");
        Doctor doc2 = new Doctor();
        doc2.setName("Dr. B");

        when(doctorRepository.findAll()).thenReturn(Arrays.asList(doc1, doc2));

        List<Doctor> result = doctorService.getAllDoctors();
        assertEquals(2, result.size());
        assertEquals("Dr. A", result.get(0).getName());
        verify(doctorRepository, times(1)).findAll();
    }

    @Test
    void testGetDoctorById() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr. X");

        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));

        Doctor result = doctorService.getDoctor(1L);
        assertNotNull(result);
        assertEquals("Dr. X", result.getName());
        verify(doctorRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateDoctor() {
        Doctor doctor = new Doctor();
        doctor.setName("Dr. Y");

        when(doctorRepository.save(doctor)).thenReturn(doctor);

        Doctor saved = doctorService.createDoctor(doctor);
        assertEquals("Dr. Y", saved.getName());
        verify(doctorRepository, times(1)).save(doctor);
    }
}
