package com.msproject.ksqlprocessorservice2.kafka;

import com.msproject.ksqlprocessorservice2.dto.DiscDTO;
import com.msproject.ksqlprocessorservice2.serdes.CustomSerdes;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class KafkaProcessors {

    private StreamsBuilder streamsBuilder;

    public KafkaProcessors(StreamsBuilder streamsBuilder) {
        this.streamsBuilder = streamsBuilder;
    }

    @PostConstruct
    public void allDiscsStreamProducerTopology() {
        KStream<String, DiscDTO> kStream = streamsBuilder.stream("all-discs-topic", Consumed.with(Serdes.String(), CustomSerdes.discDTO()));

        kStream.filter((key, disc) -> (Integer.parseInt(disc.getYear()) >= 1980 && Integer.parseInt(disc.getYear()) < 1990))
                .to("only-80s-discs-topic", Produced.with(Serdes.String(), CustomSerdes.discDTO()));
    }

}
