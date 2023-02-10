package com.disc.service.service;

import com.disc.service.configuration.KafkaTopicConfig;
import com.disc.service.entity.Disc;
import com.disc.service.kafka.KafkaSender;
import com.disc.service.model.NotificationDTO;
import com.disc.service.repository.DiscRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DiscService {

    private final DiscRepository discRepository;

    private final MongoTemplate mongoTemplate;

    private final SequenceGeneratorService sequenceGenerator;

    private final KafkaSender kafkaSender;

    public DiscService(DiscRepository discRepository, MongoTemplate mongoTemplate, SequenceGeneratorService sequenceGenerator, KafkaSender kafkaSender) {
        this.discRepository = discRepository;
        this.mongoTemplate = mongoTemplate;
        this.sequenceGenerator = sequenceGenerator;
        this.kafkaSender = kafkaSender;
    }

    public List<Disc> getAll() {
        log.info("Have been called the getAll method on the DiscService class");
        return discRepository.findAll();
    }

    public List<Disc> getLiveDiscs() {
        log.info("Have been called the getLiveDiscs method on the DiscService class");
        return discRepository.findAll()
                .stream()
                .filter(x -> x.getTitle().toUpperCase().contains("LIVE"))
                .collect(Collectors.toList());
    }

    public Disc getDiscById(int id) {
        log.info("Have been called the getDiscById method on the DiscService class");
        Optional<Disc> disc = discRepository.findById(id);
        return disc.isPresent() ? disc.get() : null;
    }

    public Disc save(Disc disc) {
        log.info("Have been called the save method on the DiscService class");
        disc.setId(sequenceGenerator.generateSequence(Disc.SEQUENCE_NAME));

        String description = "The disc " + disc.getTitle() + " was saved successfully";
        kafkaSender.sendMessage(KafkaTopicConfig.PROCESS_NOTIFICATION_TOPIC, NotificationDTO.builder().description(description).notificationDate(LocalDateTime.now()).build());

        return discRepository.save(disc);
    }

    public List<Disc> getDiscsByIdBand(long idBand) {
        log.info("Have been called the getDiscsByIdBand method on the DiscService class");
        Query query = new Query();
        query.addCriteria(Criteria.where("idBand").is(idBand));
        return mongoTemplate.find(query, Disc.class);
    }

    public Disc update(int id, Disc disc) {
        log.info("Have been called the update method on the DiscService class");
        Disc toUpdate = getDiscById(id);
        toUpdate.setIdBand(disc.getIdBand());
        toUpdate.setPrice(disc.getPrice());
        toUpdate.setStock(disc.getStock());
        toUpdate.setTitle(disc.getTitle());
        toUpdate.setYear(disc.getYear());

        String description = "The disc " + toUpdate.getTitle() + " was updated successfully";
        kafkaSender.sendMessage(KafkaTopicConfig.PROCESS_NOTIFICATION_TOPIC, NotificationDTO.builder().description(description).notificationDate(LocalDateTime.now()).build());

        return discRepository.save(toUpdate);
    }

    public void delete(int id) {
        log.info("Have been called the delete method on the DiscService class");
        Disc toDelete = getDiscById(id);

        String description = "The disc " + toDelete.getTitle() + " was deleted successfully";
        kafkaSender.sendMessage(KafkaTopicConfig.PROCESS_NOTIFICATION_TOPIC, NotificationDTO.builder().description(description).notificationDate(LocalDateTime.now()).build());

        discRepository.deleteById(id);
    }

}
