package com.jelenasstudio.bookingapp.service;

import com.jelenasstudio.bookingapp.dto.BookingCreateRequest;
import com.jelenasstudio.bookingapp.dto.BookingRequest;
import com.jelenasstudio.bookingapp.model.Booking;
import com.jelenasstudio.bookingapp.model.ServiceItem;
import com.jelenasstudio.bookingapp.model.User;
import com.jelenasstudio.bookingapp.repository.BookingRepository;
import com.jelenasstudio.bookingapp.repository.ServiceItemRepository;
import com.jelenasstudio.bookingapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ServiceItemRepository serviceRepository;
    private final UserRepository userRepository;

    public Booking createBooking(BookingCreateRequest req, String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        ServiceItem service = serviceRepository.findById(req.getServiceId()).orElseThrow();

        LocalDateTime start = req.getStart();
        LocalDateTime end = start.plusMinutes(service.getDurationMinutes());

        boolean conflict = bookingRepository.findAll().stream()
                .anyMatch(b -> {
                    LocalDateTime bEnd = serviceRepository.findById(b.getServiceId())
                            .map(s -> b.getStart().plusMinutes(s.getDurationMinutes()))
                            .orElse(b.getStart());
                    return b.getStart().isBefore(end) && start.isBefore(bEnd);
                });
        if (conflict) {
            throw new RuntimeException("The time is already taken");
        }

        Booking booking = Booking.builder()
                .userId(user.getId())
                .serviceId(service.getId())
                .start(start)
                .build();

        return bookingRepository.save(booking);
    }

    public List<BookingRequest> getBookingsForUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();

        return bookingRepository.findByUserId(user.getId()).stream()
                .map(b -> new BookingRequest(
                        b.getId(),
                        user.getName(),
                        serviceRepository.findById(b.getServiceId())
                                .map(ServiceItem::getName)
                                .orElse("Unknown service"),
                        b.getStart()
                ))
                .toList();
    }

    public List<BookingRequest> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(b -> {
                    String userName = userRepository.findById(b.getUserId())
                            .map(User::getName)
                            .orElse("Unknown user");

                    String serviceName = serviceRepository.findById(b.getServiceId())
                            .map(ServiceItem::getName)
                            .orElse("Unknown service");

                    return new BookingRequest(
                            b.getId(),
                            userName,
                            serviceName,
                            b.getStart()
                    );
                })
                .toList();
    }

    public void deleteBooking(String id) {
        bookingRepository.deleteById(id);
    }
}