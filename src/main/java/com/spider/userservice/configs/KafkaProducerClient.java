package com.spider.userservice.configs;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerClient {
//    this is a producer for kafka
//    whenever we need to add an event to kafka we will use this producer
    private KafkaTemplate<String, String> kafkaTemplate;
    public KafkaProducerClient(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendEvent(String topic, String message) {

        kafkaTemplate.send(topic, message);
    }
}
