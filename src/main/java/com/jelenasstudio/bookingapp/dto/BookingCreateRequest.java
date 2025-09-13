package com.jelenasstudio.bookingapp.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingCreateRequest {
    private String serviceId;
    private LocalDateTime start;
}