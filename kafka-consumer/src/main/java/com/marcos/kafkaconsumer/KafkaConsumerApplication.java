package com.marcos.kafkaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class KafkaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

	@KafkaListener(topics = "topic-1")
    public static String listensT1(final String in) {
        System.out.println(in);
        return in;
    }

    @KafkaListener(topics = "topic-2")
    public static String listensT2(final String in) {
        System.out.println(in);
        return in;
    }
}
