package com.jelenasstudio.bookingapp.controller;

import com.jelenasstudio.bookingapp.model.Role;
import com.jelenasstudio.bookingapp.model.User;
import com.jelenasstudio.bookingapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    // Get all users
    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return userRepository.findById(id)
                .map(user -> {
                    if (user.getRoles().contains(Role.ROLE_ADMIN)) {
                        return ResponseEntity.badRequest().body("Can't delete admin");
                    }
                    userRepository.delete(user);
                    return ResponseEntity.ok("User deleted");
                })
                .orElse(ResponseEntity.status(404).body("User not found"));
    }
}
