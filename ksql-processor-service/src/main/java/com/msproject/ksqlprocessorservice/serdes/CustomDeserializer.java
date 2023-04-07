package com.msproject.ksqlprocessorservice.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class CustomDeserializer<T> implements Deserializer<T> {

    private ObjectMapper objectMapper = new ObjectMapper();

    private Class<T> targetClass;

    public CustomDeserializer(Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        T object = null;
        try {
            if (data == null) {
                System.out.println("Null received at deserializing");
                return null;
            }
            System.out.println("Deserializing...");
            object = objectMapper.readValue(data, targetClass);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[]");
        }
        return object;
    }

    @Override
    public void close() {
    }
}
