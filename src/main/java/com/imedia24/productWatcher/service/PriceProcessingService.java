package com.imedia24.productWatcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.producer.ProducerRecord;

@Service
public class PriceProcessingService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    // Method to process prices and send messages to Kafka
    public void processPricesAndNotify() {
        // Your logic to process prices and find profitable opportunities

        // Sample message to send to Kafka
        String message = "Profitable opportunity found!";
        
        // Sending the message to the Kafka topic
        kafkaTemplate.send("your-kafka-topic", message);
    }
}
