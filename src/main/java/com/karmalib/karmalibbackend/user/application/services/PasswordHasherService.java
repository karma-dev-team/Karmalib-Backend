package com.karmalib.karmalibbackend.user.application.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordHasherService {

    private final BCryptPasswordEncoder passwordEncoder;

    // Constructor to initialize the BCryptPasswordEncoder
    public PasswordHasherService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Method to hash a password
    public String hashPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    // Method to check if a plain password matches a hashed password
    public boolean matches(String plainPassword, String hashedPassword) {
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }
}