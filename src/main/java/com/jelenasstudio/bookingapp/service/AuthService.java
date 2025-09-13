package com.jelenasstudio.bookingapp.service;

import com.jelenasstudio.bookingapp.dto.*;
import com.jelenasstudio.bookingapp.model.User;
import com.jelenasstudio.bookingapp.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthResponse register(RegisterRequest req) {
        User user = userService.register(req);

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRoles().stream().map(Enum::name).collect(Collectors.toSet())
        );

        return new AuthResponse(token, user.getEmail(),
                user.getRoles().stream().map(Enum::name).collect(Collectors.toSet()));
    }

    public AuthResponse login(AuthRequest req) {

        try {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );
        } catch (BadCredentialsException ex) {
            throw new RuntimeException("Wrong email or password");
        }

        User user = userService.findByEmail(req.getEmail());

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRoles().stream().map(Enum::name).collect(Collectors.toSet())
        );

        return new AuthResponse(token, user.getEmail(),
                user.getRoles().stream().map(Enum::name).collect(Collectors.toSet()));
    }
}
