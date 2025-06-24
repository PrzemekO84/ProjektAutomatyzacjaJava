package com.example.medicalreservationsystem.appointments;

import com.example.medicalreservationsystem.doctors.Doctor;
import com.example.medicalreservationsystem.users.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private User patient;
}
