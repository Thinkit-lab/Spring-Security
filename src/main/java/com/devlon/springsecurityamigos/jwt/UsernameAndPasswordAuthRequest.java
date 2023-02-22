package com.devlon.springsecurityamigos.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsernameAndPasswordAuthRequest {
    private String username;
    private String password;
}
