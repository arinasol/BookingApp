package com.jelenasstudio.bookingapp.dto;

import lombok.Data;
import java.util.Set;

@Data
public class AuthResponse {
    private final String token;
    private final String email;
    private final Set<String> roles;
}
