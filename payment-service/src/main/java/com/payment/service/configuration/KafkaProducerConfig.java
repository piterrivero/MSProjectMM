package com.payment.service.configuration;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

// If we want to use the kafkatTemplate by default this class is not needed at all... but if we want to use customs kafkatemplate we have to
// create the class and define it, but in this moment the default one is not more available

@Configuration
public class KafkaProducerConfig {

	/**
    	:::: To override the default properties that are defined on the application.yml ::::
    	configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	 */
	
	@Autowired
	private KafkaProperties kafkaProperties;
	
	@Bean
	public ProducerFactory<String, Object> objectProducerFactory() {
		Map<String, Object> props = kafkaProperties.buildProducerProperties();
    	props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
	    return new DefaultKafkaProducerFactory<>(props);
	}

	@Bean
	public KafkaTemplate<String, Object> objectKafkaTemplate() {
	    return new KafkaTemplate<>(objectProducerFactory());
	}
}
