package com.jelenasstudio.bookingapp.controller;

import com.jelenasstudio.bookingapp.model.DayOfWeek;
import com.jelenasstudio.bookingapp.model.SalonSchedule;
import com.jelenasstudio.bookingapp.model.WorkingHours;
import com.jelenasstudio.bookingapp.service.SalonScheduleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;

@RestController
@RequestMapping("/schedule")
@PreAuthorize("hasRole('ADMIN')")
public class ScheduleController {

    private final SalonScheduleService salonScheduleService;

    public ScheduleController(SalonScheduleService salonScheduleService) {
        this.salonScheduleService = salonScheduleService;
    }

    // Get current schedule
    @GetMapping
    public SalonSchedule getSchedule() {
        return salonScheduleService.getSchedule();
    }

    // Put default hours
    @PutMapping("/default")
    public SalonSchedule setDefaultHours(@RequestBody WorkingHours workingHours) {
        return salonScheduleService.setDefaultHours(workingHours);
    }

    // Put working hours for a specific day of the week
    @PutMapping("/day/{day}")
    public SalonSchedule setDayHours(@PathVariable DayOfWeek day,
                                     @RequestBody WorkingHours workingHours) {
        return salonScheduleService.setDayHours(day, workingHours);
    }

    // Close a specific day
    @PostMapping("/close/{date}")
    public SalonSchedule closeDay(@PathVariable String date) {
        return salonScheduleService.closeDay(LocalDate.parse(date));
    }

    // Open closed day
    @DeleteMapping("/close/{date}")
    public SalonSchedule openDay(@PathVariable String date) {
        return salonScheduleService.openDay(LocalDate.parse(date));
    }
}
