package com.jelenasstudio.bookingapp.service;

import com.jelenasstudio.bookingapp.model.ServiceItem;
import com.jelenasstudio.bookingapp.repository.ServiceItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceItemService {

    private final ServiceItemRepository repository;

    public List<ServiceItem> getAllServices() {
        return repository.findAll();
    }

    public Optional<ServiceItem> getServiceById(String id) {
        return repository.findById(id);
    }

    public ServiceItem createService(ServiceItem serviceItem) {
        return repository.save(serviceItem);
    }

    public ServiceItem updateService(String id, ServiceItem updated) {
        updated.setId(id);
        return repository.save(updated);
    }

    public void deleteService(String id) {
        repository.deleteById(id);
    }
}
