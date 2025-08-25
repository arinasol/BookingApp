package com.jelenasstudio.bookingapp.repository;

import com.jelenasstudio.bookingapp.model.ServiceItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServiceItemRepository extends MongoRepository<ServiceItem, String> {

}

