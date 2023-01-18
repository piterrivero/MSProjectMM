package com.genre.service.controller;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genre.service.entity.Genre;
import com.genre.service.service.GenreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/genre")
public class GenreController {

	@Autowired
	private GenreService genreService;

	@GetMapping
	public ResponseEntity<List<Genre>> listGenres() {
		log.info("Have been called the listGenres method on the GenreController class");
		List<Genre> genres = genreService.getAll();
		if (genres.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(genres);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Genre> getGenre(@PathVariable("id") int id) {
		log.info("Have been called the getGenre method on the GenreController class");
		Genre genre = genreService.getGenreById(id);
		if (genre == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(genre);
	}

	@PostMapping
	public ResponseEntity<Genre> saveGenre(@RequestBody Genre genre) {
		log.info("Have been called the saveGenre method on the GenreController class");
		Genre newGenre = genreService.save(genre);
		return ResponseEntity.ok(newGenre);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Genre> updateGenre(@PathVariable("id") int id, @RequestBody Genre genre) {
		log.info("Have been called the updateGenre method on the GenreController class");
		if (genre == null) {
			return ResponseEntity.notFound().build();
		}
		Genre newGenre = genreService.update(id, genre);
		return ResponseEntity.ok(newGenre);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteGenre(@PathVariable("id") int id) {
		log.info("Have been called the deleteGenre method on the GenreController class");
		Genre genre = genreService.getGenreById(id);
		if (genre == null) {
			return ResponseEntity.notFound().build();
		}
		genreService.delete(id);
		return new ResponseEntity<>(org.springframework.http.HttpStatus.OK);
	}

}
