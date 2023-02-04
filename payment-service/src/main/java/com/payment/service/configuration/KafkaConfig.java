package com.payment.service.configuration;

import com.payment.service.kafka.MessageDeserializer;
import com.payment.service.model.OrderDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

	@Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

	@Autowired
	private ObjectMapper objectMapper;

	@Bean
	public ConsumerFactory<String, OrderDTO> orderDTOConsumerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, MessageDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new MessageDeserializer<>(OrderDTO.class, objectMapper));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, OrderDTO> orderDTOListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, OrderDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(orderDTOConsumerFactory());
		return factory;
	}
	
}
