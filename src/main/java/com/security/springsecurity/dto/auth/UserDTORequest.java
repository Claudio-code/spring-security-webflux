package com.security.springsecurity.dto.auth;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTORequest {
    private String username;
    private String password;
}
