package com.jelenasstudio.bookingapp.repository;

import com.jelenasstudio.bookingapp.model.SalonSchedule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScheduleRepository extends MongoRepository<SalonSchedule, String> {
}