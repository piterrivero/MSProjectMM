package com.genre.service.service;

import com.genre.service.configuration.KafkaTopicConfig;
import com.genre.service.entity.Genre;
import com.genre.service.kafka.KafkaSender;
import com.genre.service.model.NotificationDTO;
import com.genre.service.repository.GenreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GenreService {

    private final GenreRepository genreRepository;

    private final KafkaSender kafkaSender;

    private final SequenceGeneratorService sequenceGenerator;

    public GenreService(GenreRepository genreRepository, KafkaSender kafkaSender, SequenceGeneratorService sequenceGenerator) {
        this.genreRepository = genreRepository;
        this.kafkaSender = kafkaSender;
        this.sequenceGenerator = sequenceGenerator;
    }

    public List<Genre> getAll() {
        log.info("Have been called the getAll method on the GenreService class");
        return genreRepository.findAll();
    }

    public Genre getGenreById(int id) {
        log.info("Have been called the getGenreById method on the GenreService class");
        Optional<Genre> genre = genreRepository.findById(id);
        return genre.isPresent() ? genre.get() : null;
    }

    public Genre save(Genre genre) {
        log.info("Have been called the save method on the GenreService class");
        genre.setId(sequenceGenerator.generateSequence(Genre.SEQUENCE_NAME));

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setDescription("The genre " + genre.getGenre() + " was saved successfully");
        notificationDTO.setNotificationDate(LocalDateTime.now());
        kafkaSender.sendMessage(KafkaTopicConfig.PROCESS_NOTIFICATION_TOPIC, notificationDTO);

        return genreRepository.save(genre);
    }

    public Genre update(int id, Genre genre) {
        log.info("Have been called the update method on the GenreService class");
        Genre toUpdate = getGenreById(id);
        toUpdate.setGenre(genre.getGenre());

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setDescription("The genre " + toUpdate.getGenre() + " was updated successfully");
        notificationDTO.setNotificationDate(LocalDateTime.now());
        kafkaSender.sendMessage(KafkaTopicConfig.PROCESS_NOTIFICATION_TOPIC, notificationDTO);

        return genreRepository.save(toUpdate);
    }

    public void delete(int id) {
        log.info("Have been called the delete method on the GenreService class");
        Genre toDelete = getGenreById(id);

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setDescription("The genre " + toDelete.getGenre() + " was deleted successfully");
        notificationDTO.setNotificationDate(LocalDateTime.now());
        kafkaSender.sendMessage(KafkaTopicConfig.PROCESS_NOTIFICATION_TOPIC, notificationDTO);

        genreRepository.deleteById(id);
    }

}
