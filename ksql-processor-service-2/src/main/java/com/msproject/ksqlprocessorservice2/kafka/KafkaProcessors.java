package com.msproject.ksqlprocessorservice2.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void streamTopology() {
        KStream<String, String> kStream = streamsBuilder.stream("test-topic", Consumed.with(Serdes.String(), Serdes.String()));
        kStream.map((key, value) -> log.info("Value: "+value)).to("spring.boot.kafka.stream.output", Produced.with(Serdes.String(), Serdes.String()));
    }
     */

}
