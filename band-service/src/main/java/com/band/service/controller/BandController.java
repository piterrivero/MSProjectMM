package com.band.service.controller;

import com.band.service.entity.Band;
import com.band.service.model.DiscDTO;
import com.band.service.model.GenreDTO;
import com.band.service.service.BandService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/band")
public class BandController {

    private final BandService bandService;

    public BandController(BandService bandService) {
        this.bandService = bandService;
    }

    @GetMapping
    public ResponseEntity<List<Band>> listBands() {
        log.info("Have been called the listBands method on the BandController class");
        List<Band> bands = bandService.getAll();
        if (bands.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bands);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Band> getBand(@PathVariable("id") int id) {
        log.info("Have been called the getBand method on the BandController class");
        Band band = bandService.getBandById(id);
        if (band == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(band);
    }

    @PostMapping
    public ResponseEntity<Band> saveBand(@RequestBody Band band) {
        log.info("Have been called the saveBand method on the BandController class");
        Band newBand = bandService.save(band);
        return ResponseEntity.ok(newBand);
    }

    // Communicating with other micro services

    @CircuitBreaker(name = "discCB", fallbackMethod = "fallBackSaveDisc")
    @PostMapping("/disc/{idBand}")
    public ResponseEntity<DiscDTO> saveDisc(@PathVariable("idBand") int idBand, @RequestBody DiscDTO disc) {
        log.info("Have been called the saveDisc method");
        disc.setIdBand(idBand);
        DiscDTO newDisc = bandService.saveDisc(disc);
        return ResponseEntity.ok(newDisc);
    }

    public ResponseEntity<DiscDTO> fallBackSaveDisc(@PathVariable("idBand") int idBand, @RequestBody DiscDTO disc, RuntimeException excepcion) {
        log.info("Have been called the fallBackSaveDisc method on the BandController class");
        log.info("The band can not be saved in this moment, please try later");
        return new ResponseEntity("The band can not be saved in this moment, please try later", HttpStatus.OK);
    }

    @Retry(name = "discCB", fallbackMethod = "fallBackGetDiscsByIdBand")
    @GetMapping("/disc/{idBand}")
    public ResponseEntity<List<DiscDTO>> getDiscsByIdBand(@PathVariable("idBand") long idBand) {
        log.info("Have been called the getDiscsByIdBand method on the BandController class");
        List<DiscDTO> discs = bandService.getDiscByIdBand(idBand);
        return ResponseEntity.ok(discs);
    }

    public ResponseEntity<List<DiscDTO>> fallBackGetDiscsByIdBand(@PathVariable("idBand") long idBand, RuntimeException excepcion) {
        log.info("Have been called the fallBackGetDiscsByIdBand method on the BandController class");
        log.info("Right now the disc can not be found, please try later");
        return new ResponseEntity("Right now the disc can not be found, please try later", HttpStatus.OK);
    }

    @CircuitBreaker(name = "genreCB", fallbackMethod = "fallBackGetGenreById")
    @GetMapping("/genre/{idGenre}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable("idGenre") long idGenre) {
        log.info("Have been called the getGenreById method");
        GenreDTO genre = bandService.getGenreById(idGenre);
        return ResponseEntity.ok(genre);
    }

    public ResponseEntity<GenreDTO> fallBackGetGenreById(@PathVariable("idGenre") long idGenre, RuntimeException excepcion) {
        log.info("Have been called the fallBackGetGenreById method on the BandController class");
        log.info("Right now the genre can not be found, please try later");
        return new ResponseEntity("Right now the genre can not be found, please try later", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Band> updateBand(@PathVariable("id") int id, @RequestBody Band band) {
        log.info("Have been called the updateBand method on the BandController class");
        if (band == null) {
            return ResponseEntity.notFound().build();
        }
        Band newBand = bandService.update(id, band);
        return ResponseEntity.ok(newBand);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBand(@PathVariable("id") int id) {
        log.info("Have been called the deleteBand method on the BandController class");
        Band band = bandService.getBandById(id);
        if (band == null) {
            return ResponseEntity.notFound().build();
        }
        bandService.delete(id);
        return new ResponseEntity<>(org.springframework.http.HttpStatus.OK);
    }

}
