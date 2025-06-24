package com.example.medicalreservationsystem.appointments;

import com.example.medicalreservationsystem.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PreAuthorize("hasRole('PATIENT')")
    @PostMapping
    public Appointment book(@RequestBody Appointment appointment) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        if (!currentUser.getId().equals(appointment.getPatient().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Patients can only book appointments for themselves.");
        }
        appointment.setPatient(currentUser);
        return appointmentService.saveAppointment(appointment);
    }


    @PreAuthorize("hasAnyRole('DOCTOR', 'PATIENT', 'ADMIN')")
    @GetMapping
    public List<Appointment> viewAppointments() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        boolean isPatient = false;
        for (GrantedAuthority authority : currentUser.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_PATIENT")) {
                isPatient = true;
            }
        }


        if (isPatient) {
            return appointmentService.getAppointmentsForPatient(currentUser.getId());
        } else {
            return appointmentService.getAllAppointments();
        }
    }
}