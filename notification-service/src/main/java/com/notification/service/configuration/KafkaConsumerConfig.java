package com.notification.service.configuration;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;


@EnableKafka
@Configuration
public class KafkaConsumerConfig {
	
	/**
	    :::: To override the default properties that are defined on the application.yml ::::
	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    	props.put(ConsumerConfig.GROUP_ID_CONFIG, groupIdDefault);
    	props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    	props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    	props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.kafka.example.models");
	 */
	
	@Autowired
	private KafkaProperties kafkaProperties;
    
    @Bean
    public ConsumerFactory<String, Object> objectConsumerFactory() {
    	// Using the parameters by default defined on the application.yml but overriding the one that is special for this case
    	Map<String, Object> props = kafkaProperties.buildConsumerProperties();
    	props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    	return new DefaultKafkaConsumerFactory<>(props);
    }
    

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> objectKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(objectConsumerFactory());
        return factory;
    }
    
}
