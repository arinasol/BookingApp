package com.jelenasstudio.bookingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
    private String id;
    private String userName;
    private String serviceName;
    private LocalDateTime start;
}