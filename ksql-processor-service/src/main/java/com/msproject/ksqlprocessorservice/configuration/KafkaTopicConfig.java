package com.msproject.ksqlprocessorservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String MUSIC_TOPIC = "musicTopic";

    @Bean
    public NewTopic musicTopic() {
        return TopicBuilder.name(MUSIC_TOPIC)
                .build();
    }

}
