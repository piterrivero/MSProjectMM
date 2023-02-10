package com.genre.service.controller;

import com.genre.service.entity.Genre;
import com.genre.service.mapper.GenreMapper;
import com.genre.service.model.GenreDTO;
import com.genre.service.service.GenreService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/genre")
public class GenreController {

    private final GenreService genreService;

    private final GenreMapper genreMapper;

    public GenreController(GenreService genreService, GenreMapper genreMapper) {
        this.genreService = genreService;
        this.genreMapper = genreMapper;
    }

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
    public ResponseEntity<GenreDTO> saveGenre(@RequestBody GenreDTO genre) {
        log.info("Have been called the saveGenre method on the GenreController class");
        Genre newGenre = genreService.save(genreMapper.dtoToModel(genre));
        return ResponseEntity.ok(genreMapper.modelToDto(newGenre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable("id") int id, @RequestBody GenreDTO genre) {
        log.info("Have been called the updateGenre method on the GenreController class");
        if (genre == null) {
            return ResponseEntity.notFound().build();
        }
        Genre newGenre = genreService.update(id, genreMapper.dtoToModel(genre));
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
