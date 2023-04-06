package com.msproject.ksqlprocessorservice.kafka;

import com.msproject.relationaldb.dto.DiscDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Slf4j
@Configuration
public class KafkaProcessors {

    @Bean
    public Function<KStream<String, DiscDTO>, KStream<String, DiscDTO>> only80sDiscsProcessor(){
        return kstream -> kstream.filter((key, disc) -> {
          return Integer.parseInt(disc.getYear())  >= 1980 && Integer.parseInt(disc.getYear())  < 1990;
        });
    }

    @Bean
    public Function<KStream<String, DiscDTO>, KStream<String, DiscDTO>> onlyExpensiveDiscsProcessor(){
        return kstream -> kstream.filter((key, disc) -> {
            return disc.getPrice() > 30;
        });
    }

}
