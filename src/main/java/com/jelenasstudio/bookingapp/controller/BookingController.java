package com.jelenasstudio.bookingapp.controller;

import com.jelenasstudio.bookingapp.dto.BookingCreateRequest;
import com.jelenasstudio.bookingapp.dto.BookingRequest;
import com.jelenasstudio.bookingapp.model.Booking;
import com.jelenasstudio.bookingapp.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    // User books the service
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public Booking create(@RequestBody BookingCreateRequest req, Authentication auth) {
        return bookingService.createBooking(req, auth.getName());
    }

    // Get bookings for user
    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public List<BookingRequest> myBookings(Authentication auth) {
        return bookingService.getBookingsForUser(auth.getName());
    }

    // Get all user bookings for admin
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<BookingRequest> allBookings() {
        return bookingService.getAllBookings();
    }

    // Delete booking for admin
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
