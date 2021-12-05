package com.security.springsecurity.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public record PasswordService(BCryptPasswordEncoder passwordEncoder) {
    public String getPasswordEncoder(String password) {
        return passwordEncoder.encode(password);
    }
}
