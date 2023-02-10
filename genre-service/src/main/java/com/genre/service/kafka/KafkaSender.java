package com.genre.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaSender {

    private KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaSender(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topicName, Object data) {
        log.info("Sent a message to the topic: " + topicName);

        Message<Object> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();

        kafkaTemplate.send(message);
    }

}
