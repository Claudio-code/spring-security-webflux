package com.security.springsecurity.configuration;

import com.security.springsecurity.security.AuthManager;
import com.security.springsecurity.security.SecurityContextManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@AllArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class SecurityConfigurationFilter {
    private final AuthManager authManager;
    private final SecurityContextManager securityContextManager;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        return httpSecurity
                .cors().disable()
                .csrf().disable()
                .authenticationManager(authManager)
                .securityContextRepository(securityContextManager)
                .authorizeExchange()
                .pathMatchers("/sign-up/**").permitAll()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange().authenticated()
                .and()
                .build();
    }
}
