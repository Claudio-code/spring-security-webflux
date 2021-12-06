package com.security.springsecurity.security;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class SecurityContextManager implements ServerSecurityContextRepository {
    private static final String START_BEARER_TOKEN = "Bearer ";
    private AuthManager authManager;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return null;
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String token = null;

        if (authHeader != null && authHeader.startsWith(SecurityContextManager.START_BEARER_TOKEN)) {
            token = authHeader.replace(SecurityContextManager.START_BEARER_TOKEN, "");
        }

        if (token != null) {
            Authentication auth = new UsernamePasswordAuthenticationToken(token, token);
            return authManager.authenticate(auth).map(SecurityContextImpl::new);
        }

        return Mono.empty();
    }
}
