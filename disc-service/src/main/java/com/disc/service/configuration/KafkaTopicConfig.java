package com.disc.service.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    
	public static final String PROCESS_NOTIFICATION_TOPIC = "processNotificationTopic";
	
    @Bean
    public NewTopic processNotificationTopic(){
        return TopicBuilder.name(PROCESS_NOTIFICATION_TOPIC)
                .build();
    }
    
}
