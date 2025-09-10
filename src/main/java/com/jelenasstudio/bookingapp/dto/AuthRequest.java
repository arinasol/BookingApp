package com.jelenasstudio.bookingapp.dto;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
public class AuthRequest {
    @Email @NotBlank private String email;
    @NotBlank private String password;
}
