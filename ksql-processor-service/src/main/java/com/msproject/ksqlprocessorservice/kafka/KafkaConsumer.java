package com.msproject.ksqlprocessorservice.kafka;

import com.msproject.ksqlprocessorservice.dto.TestDTO;
import com.msproject.relationaldb.dto.DiscDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@Configuration
public class KafkaConsumer {

    @Bean
    public Consumer<KStream<String, TestDTO>> testConsumer(){
        return kstream -> kstream.foreach((key, test) -> {
            log.info("====>"+test.getMessage());
        });
    }

    @Bean
    public Consumer<KStream<String, DiscDTO>> allMusicConsumer(){
        return kstream -> kstream.foreach((key, disc) -> {
            printLog(false,1, disc);
        });
    }

    @Bean
    public Consumer<KStream<String, DiscDTO>> only80sDiscsConsumer(){
        return kstream -> kstream.foreach((key, disc) -> {
            printLog(true, 2, disc);
        });
    }

    @Bean
    public Consumer<KStream<String, DiscDTO>> onlyExpensiveDiscsConsumer(){
        return kstream -> kstream.foreach((key, disc) -> {
            printLog(false, 3, disc);
        });
    }

    @Bean
    public Consumer<KStream<String, DiscDTO>> onlyMetal80sDiscsConsumer(){
        return kstream -> kstream.foreach((key, disc) -> {
            printLog(true, 4, disc);
        });
    }

    public void printLog(boolean print, int type, DiscDTO disc){
        if (print) {
            log.info("[{}] {} ({}, {}$) - {} ({}) - {}", type, disc.getTitle(), disc.getYear(), disc.getPrice(),
                    disc.getBand().getName(), disc.getBand().getCountry(),
                    disc.getBand().getGenre().getGenre());
        }
    }

}
