package com.genre.service.controller;

import java.util.List;

import com.genre.service.mapper.GenreMapper;
import com.genre.service.model.GenreDTO;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RestController
@RequestMapping("/genre")
public class GenreController {

	private GenreService genreService;

	private GenreMapper genreMapper;

	@GetMapping
	public ResponseEntity<List<GenreDTO>> listGenres() {
		log.info("Have been called the listGenres method on the GenreController class");
		List<Genre> genres = genreService.getAll();
		if (genres.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(genreMapper.modelsToDto(genres));
	}

	@GetMapping("/{id}")
	public ResponseEntity<GenreDTO> getGenre(@PathVariable("id") int id) {
		log.info("Have been called the getGenre method on the GenreController class");
		Genre genre = genreService.getGenreById(id);
		if (genre == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(genreMapper.modelToDto(genre));
	}

	@PostMapping
	public ResponseEntity<GenreDTO> saveGenre(@RequestBody GenreDTO genreDto) {
		log.info("Have been called the saveGenre method on the GenreController class");
		Genre newGenre = genreService.save(genreMapper.dtoToModel(genreDto));
		return ResponseEntity.ok(genreMapper.modelToDto(newGenre));
	}

	@PutMapping("/{id}")
	public ResponseEntity<GenreDTO> updateGenre(@PathVariable("id") int id, @RequestBody GenreDTO genreDto) {
		log.info("Have been called the updateGenre method on the GenreController class");
		if (genreDto == null) {
			return ResponseEntity.notFound().build();
		}
		Genre newGenre = genreService.update(id, genreMapper.dtoToModel(genreDto));
		return ResponseEntity.ok(genreMapper.modelToDto(newGenre));
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
