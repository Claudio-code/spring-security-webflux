package com.security.springsecurity.document;

import com.security.springsecurity.dto.auth.UserDTORequest;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document
@Data
public class User {
    @Id
    private String id;
    private String username;
    private String password;

    public static User of(UserDTORequest dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }
}
