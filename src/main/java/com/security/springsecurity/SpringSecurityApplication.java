package com.security.springsecurity;

import com.security.springsecurity.security.AuthManager;
import com.security.springsecurity.security.SecurityContextManager;
import com.security.springsecurity.util.JwtUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityContextManager securityContextManager() {
        var authManager = new AuthManager(new JwtUtils());
        return new SecurityContextManager(authManager);
    }
}
