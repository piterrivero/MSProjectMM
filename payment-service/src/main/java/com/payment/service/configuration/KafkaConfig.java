package com.payment.service.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class KafkaConfig {

	@Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

	@Autowired
	private ObjectMapper objectMapper;
	
    
	
}
