package com.msproject.ksqlprocessorservice2.kafka;

import com.msproject.ksqlprocessorservice2.dto.DiscDTO;
import com.msproject.ksqlprocessorservice2.serdes.CustomSerdes;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class KafkaProcessors {

    private StreamsBuilder streamsBuilder;

    public KafkaProcessors(StreamsBuilder streamsBuilder) {
        this.streamsBuilder = streamsBuilder;
    }

    /*
    @PostConstruct
    public void allDiscsStreamProducerTopology() {
        KStream<String, DiscDTO> kStream = streamsBuilder.stream("all-discs-topic", Consumed.with(Serdes.String(), CustomSerdes.discDTO()));

        kStream.filter((key, disc) -> (Integer.parseInt(disc.getYear()) >= 1980 && Integer.parseInt(disc.getYear()) < 1990))
                .to("only-80s-discs-topic", Produced.with(Serdes.String(), CustomSerdes.discDTO()));
    }
     */

    @PostConstruct
    public void allCountDiscs() {

        // STEP BY STEP

        /*
        KStream<String, DiscDTO> kStream = streamsBuilder.stream("all-discs-topic", Consumed.with(Serdes.String(), CustomSerdes.discDTO()));

        KStream<String, String> kstream2 = kStream.mapValues(v -> v.getBand().getName());

        KGroupedStream<String, String> kGroupedStream = kstream2.groupBy((k, v) -> v, Grouped.with(Serdes.String(), Serdes.String()));

        KTable<String, Long> ktable = kGroupedStream.count(Materialized.with(Serdes.String(), Serdes.Long()));

        KStream<String, Long> result = ktable.toStream();

        result.foreach((x,y) -> {
            log.info("{} - {}", x, y);
        });
        */

        KStream<String, DiscDTO> kStream = streamsBuilder.stream("all-discs-topic", Consumed.with(Serdes.String(), CustomSerdes.discDTO()));

        kStream.mapValues(v -> v.getBand().getName())
                .groupBy((k, v) -> v, Grouped.with(Serdes.String(), Serdes.String()))
                .count(Materialized.with(Serdes.String(), Serdes.Long()))
                .toStream()
                .to("number-discs-of-bands-topic", Produced.with(Serdes.String(), Serdes.Long()));

     }
}
