package com.security.springsecurity.security;

import com.security.springsecurity.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class AuthManager implements ReactiveAuthenticationManager {
    private JwtUtils jwtUtils;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        String userName = jwtUtils.getUserName(token);
        return makeAuth(userName);
    }

    private Mono<Authentication> makeAuth(String userName) {
        var auth = new UsernamePasswordAuthenticationToken(userName, userName);
        return  Mono.justOrEmpty(auth);
    }
}
