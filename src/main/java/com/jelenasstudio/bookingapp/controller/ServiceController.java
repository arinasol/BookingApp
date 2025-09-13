package com.jelenasstudio.bookingapp.controller;

import com.jelenasstudio.bookingapp.model.ServiceItem;
import com.jelenasstudio.bookingapp.service.ServiceItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceItemService service;

    // Get all services
    @GetMapping
    public List<ServiceItem> getAll() {
        return service.getAllServices();
    }

    // Get service by id
    @GetMapping("/{id}")
    public ResponseEntity<ServiceItem> getById(@PathVariable String id) {
        return service.getServiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create service for admin
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ServiceItem create(@RequestBody ServiceItem item) {
        return service.createService(item);
    }

    // Update service for admin
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ServiceItem update(@PathVariable String id, @RequestBody ServiceItem item) {
        return service.updateService(id, item);
    }

    // Delete service for admin
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}