package com.security.springsecurity.configuration;

import com.security.springsecurity.jwt.handler.AuthHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class Configurations {
    @Bean
    public RouterFunction<ServerResponse> auth(AuthHandler handler) {
        return RouterFunctions.route(
                RequestPredicates.POST("/sign-up").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                handler::singUp
        );
    }
}
