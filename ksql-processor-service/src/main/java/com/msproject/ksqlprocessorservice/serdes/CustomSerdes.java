package com.msproject.ksqlprocessorservice.serdes;

import com.msproject.ksqlprocessorservice.dto.TestDTO;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public class CustomSerdes {

    private CustomSerdes() {
    }

    public static Serde<TestDTO> testDTO() {
        CustomSerializer<TestDTO> serializer = new CustomSerializer<>();
        CustomDeserializer<TestDTO> deserializer = new CustomDeserializer<>(TestDTO.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

}
