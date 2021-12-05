package com.security.springsecurity.jwt.handler;

import com.security.springsecurity.document.User;
import com.security.springsecurity.dto.UserDTO;
import com.security.springsecurity.repository.UserRepository;
import com.security.springsecurity.service.PasswordService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public record AuthHandler(UserRepository userRepository, PasswordService passwordService) {
    public Mono<ServerResponse> singUp(ServerRequest request) {
        return request.bodyToMono(UserDTO.class)
                .map(this::encoderPassword)
                .flatMap(userRepository::save)
                .flatMap(user -> ServerResponse.ok().body(BodyInserters.fromValue(user)));
    }

    private User encoderPassword(UserDTO dto) {
        String passwordEncoder = passwordService.getPasswordEncoder(dto.getPassword());
        dto.setPassword(passwordEncoder);
        return User.of(dto);
    }
}
