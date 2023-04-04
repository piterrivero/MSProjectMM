package com.msproject.ksqlprocessorservice.kafka;

import com.msproject.ksqlprocessorservice.dto.MusicDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaListeners {

    @KafkaListener(topics = "musicTopic", groupId = "group1", containerFactory = "musicDTOListenerContainerFactory")
    public void listenerMusic(MusicDTO musicDTO) {
        musicDTO.getDisc().stream().forEach(discDTO -> log.info("Disc: " + discDTO.getTitle()));
    }

}
