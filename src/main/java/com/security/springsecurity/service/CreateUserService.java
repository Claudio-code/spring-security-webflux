package com.security.springsecurity.service;

import com.security.springsecurity.document.User;
import com.security.springsecurity.dto.auth.UserDTORequest;
import com.security.springsecurity.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CreateUserService {
    private UserRepository userRepository;
    private PasswordService passwordService;

    public Mono<User> execute(UserDTORequest userDTORequestOptional) {
        User userToSave = encoderPassword(userDTORequestOptional);
        return userRepository.save(userToSave);
    }

    private User encoderPassword(UserDTORequest userDTORequest) {
        String passwordEncoder = passwordService.getPasswordEncoder(userDTORequest.getPassword());
        userDTORequest.setPassword(passwordEncoder);
        return User.of(userDTORequest);
    }
}
