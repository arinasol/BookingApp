package com.jelenasstudio.bookingapp.service;

import com.jelenasstudio.bookingapp.model.DayOfWeek;
import com.jelenasstudio.bookingapp.model.SalonSchedule;
import com.jelenasstudio.bookingapp.model.WorkingHours;
import com.jelenasstudio.bookingapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SalonScheduleService {

    private final ScheduleRepository repository;

    // Get or create a schedule
    public SalonSchedule getSchedule() {
        return repository.findById("1")
                .orElseGet(() -> repository.save(
                        SalonSchedule.builder()
                                .id("1")
                                .defaultHours(new WorkingHours(9, 18)) // default hours
                                .build()
                ));
    }

    public SalonSchedule setDefaultHours(WorkingHours workingHours) {
        SalonSchedule schedule = getSchedule();
        schedule.setDefaultHours(workingHours);
        return repository.save(schedule);
    }

    public SalonSchedule setDayHours(DayOfWeek day, WorkingHours workingHours) {
        SalonSchedule schedule = getSchedule();
        schedule.getWeeklyHours().put(day, workingHours);
        return repository.save(schedule);
    }


    public SalonSchedule closeDay(LocalDate date) {
        SalonSchedule schedule = getSchedule();
        schedule.getClosedDates().add(date);
        return repository.save(schedule);
    }

    public SalonSchedule openDay(LocalDate date) {
        SalonSchedule schedule = getSchedule();
        schedule.getClosedDates().remove(date);
        return repository.save(schedule);
    }
}
