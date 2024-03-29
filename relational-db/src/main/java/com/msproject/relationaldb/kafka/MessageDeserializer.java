package com.msproject.relationaldb.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

public class MessageDeserializer<T> implements Deserializer<T> {
    private ObjectMapper objectMapper;
    private Class<T> targetClass;

    public MessageDeserializer(Class<T> targetClass, ObjectMapper objectMapper) {
        this.targetClass = targetClass;
        this.objectMapper = objectMapper;
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        T object = null;
        try {
            object = objectMapper.readValue(data, targetClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}
