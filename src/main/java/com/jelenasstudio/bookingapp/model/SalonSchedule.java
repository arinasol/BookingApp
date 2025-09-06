package com.jelenasstudio.bookingapp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Document("schedule")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SalonSchedule {
    @Id
    private String id;

    private WorkingHours defaultHours;

    // Working hours by day
    private Map<DayOfWeek, WorkingHours> weeklyHours = new HashMap<>();

    // Closed days
    private Set<LocalDate> closedDates = new HashSet<>();
}