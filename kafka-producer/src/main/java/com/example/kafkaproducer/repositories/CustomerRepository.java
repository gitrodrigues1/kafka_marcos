package com.example.kafkaproducer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kafkaproducer.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
