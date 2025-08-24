package com.jelenasstudio.bookingapp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "services")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceItem {
    @Id
    private String id;
    private String name;
    private String description;
    private Integer durationMinutes;
    private Double price;
}