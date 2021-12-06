package com.security.springsecurity.util;

import com.security.springsecurity.document.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.function.Function;

@Component
public class JwtUtils implements Serializable {
    private static final String TOKEN_SECRET = "my-secret";

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS256, JwtUtils.TOKEN_SECRET)
                .compact();
    }

    public String getUserName(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(JwtUtils.TOKEN_SECRET)
                .parseClaimsJwt(token)
                .getBody();
    }
}
