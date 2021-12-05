package com.security.springsecurity.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public record PasswordService(BCryptPasswordEncoder passwordEncoder) {
    public String getPasswordEncoder(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public Boolean validatePassword(String rawPassword, String passwordEncoded) {
        return passwordEncoder.matches(rawPassword, passwordEncoded);
    }
}
