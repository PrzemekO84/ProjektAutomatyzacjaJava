package com.example.medicalreservationsystem.security;

import com.example.medicalreservationsystem.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
}
