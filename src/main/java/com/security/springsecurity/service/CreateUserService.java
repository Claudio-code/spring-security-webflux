package com.security.springsecurity.service;

import com.security.springsecurity.document.User;
import com.security.springsecurity.dto.UserDTO;
import com.security.springsecurity.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CreateUserService {
    private UserRepository userRepository;
    private PasswordService passwordService;

    public Mono<User> execute(UserDTO userDTOOptional) {
        User userToSave = encoderPassword(userDTOOptional);

        return userRepository.save(userToSave);
    }

    private User encoderPassword(UserDTO userDTO) {
        String passwordEncoder = passwordService.getPasswordEncoder(userDTO.getPassword());
        userDTO.setPassword(passwordEncoder);
        return User.of(userDTO);
    }
}
