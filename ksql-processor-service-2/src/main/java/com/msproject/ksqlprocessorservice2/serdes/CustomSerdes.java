package com.msproject.ksqlprocessorservice2.serdes;

import com.msproject.ksqlprocessorservice2.dto.DiscDTO;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public class CustomSerdes {

    private CustomSerdes() {
    }

    public static Serde<DiscDTO> discDTO() {
        CustomSerializer<DiscDTO> serializer = new CustomSerializer<>();
        CustomDeserializer<DiscDTO> deserializer = new CustomDeserializer<>(DiscDTO.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

}
