package com.example.kafkaproducer.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerDto(
    UUID uuid,
    LocalDateTime dateTime
) {

}
