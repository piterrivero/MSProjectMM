package com.order.service.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String PROCESS_NOTIFICATION_TOPIC = "processNotificationTopic";
    public static final String PROCESS_ORDER_TOPIC = "processOrderTopic";

    @Bean
    public NewTopic processNotificationTopic() {
        return TopicBuilder.name(PROCESS_NOTIFICATION_TOPIC)
                .build();
    }

    @Bean
    public NewTopic processOrderTopic() {
        return TopicBuilder.name(PROCESS_ORDER_TOPIC)
                .build();
    }

}
