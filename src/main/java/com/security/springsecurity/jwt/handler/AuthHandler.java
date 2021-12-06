package com.security.springsecurity.jwt.handler;

import com.security.springsecurity.document.User;
import com.security.springsecurity.dto.auth.UserDTORequest;
import com.security.springsecurity.service.CreateUserService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public record AuthHandler(CreateUserService createUserService) {
    public Mono<ServerResponse> singUp(ServerRequest request) {
        return request.bodyToMono(UserDTORequest.class)
                .map(createUserService::execute)
                .flatMap(this::singUpFormatResponse);
    }

    public Mono<ServerResponse> singUpFormatResponse(Mono<User> userMono) {
        return userMono.flatMap(user -> ServerResponse.ok()
                .body(BodyInserters.fromValue(user)));
    }
}
