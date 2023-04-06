package com.msproject.relationaldb.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String ALL_DISCS_TOPIC = "all-discs-topic";

    @Bean
    public NewTopic allDiscsTopic() {
        return TopicBuilder.name(ALL_DISCS_TOPIC)
                .build();
    }

}
