package com.jelenasstudio.bookingapp.repository;
import com.jelenasstudio.bookingapp.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.time.LocalDateTime;

public interface BookingRepository extends MongoRepository<Booking, String> {
    List<Booking> findByUserId(String userId);
    List<Booking> findByStartBetween(LocalDateTime from, LocalDateTime to);
}
