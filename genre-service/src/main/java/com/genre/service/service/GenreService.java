package com.genre.service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genre.service.entity.Genre;
import com.genre.service.kafka.KafkaSender;
import com.genre.service.model.NotificationDTO;
import com.genre.service.repository.GenreRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GenreService {

	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private KafkaSender kafkaSender;

	@Autowired
	private SequenceGeneratorService sequenceGenerator;

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
		String description = "The genre "+genre.getGenre() + " was saved successfully";
		kafkaSender.sendMessage("processNotificationTopic", NotificationDTO.builder().description(description).notificationDate(LocalDateTime.now()).build());
		return genreRepository.save(genre);
	}

	public Genre update(int id, Genre genre) {
		log.info("Have been called the update method on the GenreService class");
		Genre toUpdate = getGenreById(id);
		toUpdate.setGenre(genre.getGenre());
		return genreRepository.save(toUpdate);
	}

	public void delete(int id) {
		log.info("Have been called the delete method on the GenreService class");
		genreRepository.deleteById(id);
	}

}
