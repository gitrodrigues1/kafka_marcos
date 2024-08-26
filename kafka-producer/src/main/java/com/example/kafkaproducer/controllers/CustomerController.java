package com.example.kafkaproducer.controllers;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafkaproducer.domain.Customer;
import com.example.kafkaproducer.dtos.CustomerDto;
import com.example.kafkaproducer.repositories.CustomerRepository;
import com.example.kafkaproducer.services.KafkaEventService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    
    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    private final KafkaEventService eventConfig;

    private final CustomerRepository customerRepository;

    public CustomerController(KafkaEventService eventConfig, CustomerRepository customerRepository) {
        this.eventConfig = eventConfig;
        this.customerRepository = customerRepository;
    }

    @PostMapping
    public String send(){
        CustomerDto dto = new CustomerDto(UUID.randomUUID(), LocalDateTime.now());
        eventConfig.sendEnvent("topic-1", dto);
        return "OK";
    }

    @PostMapping("/1000")
    public String sendThousandMessages() {
        LOG.warn("Starting the processing of events.");
        int count = 1000000;
        while(count > 0) {
            CustomerDto dto = new CustomerDto(UUID.randomUUID(), LocalDateTime.now());
            eventConfig.sendEnvent("topic-2", dto);
            count --;
        }
        LOG.warn("Process finished.");
        return "Finished.";
    }

    @PostMapping("/1000-db")
    public String sendThousandMessagesDb() {
        LOG.warn("Starting the processing of recording to database.");
        int count = 1000000;
        while(count > 0) {
            Customer customer = new Customer( count* 1L, LocalDateTime.now()); 
            customerRepository.save(customer);
        }

        return "Finished";
    }
}
