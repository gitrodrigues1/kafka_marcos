package com.example.kafkaproducer.services;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaEventService {

    private KafkaTemplate<Object, Object> template;

    public KafkaEventService(KafkaTemplate<Object, Object> template) {
        this.template = template;
    }

    public <T> void sendEnvent(String topic, T dados) {
    
        template.send(topic, dados.toString());
    
    }
}
