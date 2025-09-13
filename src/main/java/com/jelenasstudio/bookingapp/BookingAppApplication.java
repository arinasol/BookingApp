package com.jelenasstudio.bookingapp;

import com.jelenasstudio.bookingapp.model.Role;
import com.jelenasstudio.bookingapp.model.User;
import com.jelenasstudio.bookingapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@RequiredArgsConstructor
@SpringBootApplication
public class BookingAppApplication {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    public static void main(String[] args) {
        SpringApplication.run(BookingAppApplication.class, args);
    }

    @Bean
    CommandLineRunner initAdmin() {
        return args -> {
            if (!userRepository.existsByEmail(adminEmail)) {
                User admin = User.builder()
                        .name("Admin")
                        .email(adminEmail)
                        .password(passwordEncoder.encode(adminPassword))
                        .roles(Set.of(Role.ROLE_ADMIN))
                        .build();
                userRepository.save(admin);
            }
        };
    }

}
