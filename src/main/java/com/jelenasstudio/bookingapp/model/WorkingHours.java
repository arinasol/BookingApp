package com.jelenasstudio.bookingapp.model;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class WorkingHours {
    private int openHour;
    private int closeHour;
}