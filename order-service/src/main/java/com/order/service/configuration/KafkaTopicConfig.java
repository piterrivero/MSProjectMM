package com.order.service.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {
    
	@Autowired
	private KafkaProperties kafkaProperties;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        return new KafkaAdmin(kafkaProperties.buildAdminProperties());
    }

    @Bean
    public NewTopic processOrderTopic() {
    	return TopicBuilder.name("processOrderTopic")
                .partitions(3)
                .replicas(1)
                .build();
    }
    
    @Bean
    public NewTopic processPaymentTopic() {
    	return TopicBuilder.name("processPaymentTopic")
                .partitions(3)
                .replicas(1)
                .build();
    }
    
}
