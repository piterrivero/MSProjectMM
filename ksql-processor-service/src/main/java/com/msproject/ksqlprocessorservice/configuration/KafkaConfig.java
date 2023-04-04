package com.msproject.ksqlprocessorservice.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msproject.ksqlprocessorservice.dto.MusicDTO;
import com.msproject.ksqlprocessorservice.kafka.MessageDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    private final ObjectMapper objectMapper;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public KafkaConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public ConsumerFactory<String, MusicDTO> musicDTOConsumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, MessageDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new MessageDeserializer<>(MusicDTO.class, objectMapper));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MusicDTO> musicDTOListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MusicDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(musicDTOConsumerFactory());
        return factory;
    }
}
