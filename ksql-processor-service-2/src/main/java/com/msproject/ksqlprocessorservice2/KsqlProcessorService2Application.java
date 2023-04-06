package com.msproject.ksqlprocessorservice2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafka
@EnableKafkaStreams
public class KsqlProcessorService2Application {

    public static void main(String[] args) {
        SpringApplication.run(KsqlProcessorService2Application.class, args);
    }

}
