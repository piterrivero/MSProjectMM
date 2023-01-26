package com.notification.service.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.service.kafka.MessageDeserializer;
import com.notification.service.model.NotificationDTO;

@Configuration
public class KafkaConfig {

	@Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

	@Autowired
	private ObjectMapper objectMapper;
	
    @Bean
    public ConsumerFactory<String, NotificationDTO> notificationDTOConsumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, MessageDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new MessageDeserializer<>(NotificationDTO.class, objectMapper));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, NotificationDTO> notificationDTOListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, NotificationDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(notificationDTOConsumerFactory());
        return factory;
    }
	
}
