package com.msproject.ksqlprocessorservice2.kafka;

import com.msproject.ksqlprocessorservice2.dto.DiscDTO;
import com.msproject.ksqlprocessorservice2.serdes.CustomSerdes;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class KafkaConsumer {

    private StreamsBuilder streamsBuilder;

    public KafkaConsumer(StreamsBuilder streamsBuilder) {
        this.streamsBuilder = streamsBuilder;
    }

    @PostConstruct
    public void only80sDiscsStreamConsumerTopology() {
        KStream<String, DiscDTO> kStream = streamsBuilder.stream("only-80s-discs-topic", Consumed.with(Serdes.String(), CustomSerdes.discDTO()));
        kStream.foreach((k,disc) -> printLog(true, 1, disc));
    }

    public void printLog(boolean print, int type, DiscDTO disc){
        if (print) {
            log.info("[{}] {} ({}, {}$) - {} ({}) - {}", type, disc.getTitle(), disc.getYear(), disc.getPrice(),
                    disc.getBand().getName(), disc.getBand().getCountry(),
                    disc.getBand().getGenre().getGenre());
        }
    }

}
