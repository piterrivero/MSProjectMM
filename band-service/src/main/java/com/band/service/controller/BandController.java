package com.band.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.band.service.entity.Band;
import com.band.service.service.BandService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import model.Disc;
import model.Genre;

@Slf4j
@RestController
@RequestMapping("/band")
public class BandController {

	@Autowired
	private BandService bandService;
	
	@GetMapping
	public ResponseEntity<List<Band>> listBands(){
		log.info("Have been called the listBands method");
		List<Band> bands = bandService.getAll();
		if (bands.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(bands);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Band> getBand(@PathVariable("id") int id){
		log.info("Have been called the getBand method");
		Band band = bandService.getBandById(id);
		if (band == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(band);
	}
	
	@PostMapping
	public ResponseEntity<Band> saveBand(@RequestBody Band band){
		log.info("Have been called the saveBand method");
		Band newBand = bandService.save(band);
		return ResponseEntity.ok(newBand);
	}
	
	// Communicating with other micro services
	
	@CircuitBreaker(name = "discCB",fallbackMethod = "fallBackSaveDisc")
	@PostMapping("/disc/{idBand}")
	public ResponseEntity<Disc> saveDisc(@PathVariable("idBand") int idBand, @RequestBody Disc disc){
		log.info("Have been called the saveDisc method");
		disc.setIdBand(idBand);
		Disc newDisc = bandService.saveDisc(disc);
		return ResponseEntity.ok(newDisc);
	} 

	public ResponseEntity<Disc> fallBackSaveDisc(@PathVariable("idBand") int idBand, @RequestBody Disc disc, RuntimeException excepcion){
		log.info("Have been called the fallBackSaveDisc method");
		return new ResponseEntity("The band can not be saved in this moment, please try later", HttpStatus.OK);
	}
	
	@CircuitBreaker(name = "discCB",fallbackMethod = "fallBackGetDiscsByIdBand")
	@GetMapping("/disc/{idBand}")
	public ResponseEntity<List<Disc>> getDiscsByIdBand(@PathVariable("idBand") long idBand){
		log.info("Have been called the getDiscsByIdBand method");
		List<Disc> discs = bandService.getDiscByIdBand(idBand);
		return ResponseEntity.ok(discs);
	}
	
	public ResponseEntity<List<Disc>> fallBackGetDiscsByIdBand(@PathVariable("idBand") long idBand, RuntimeException excepcion){
		log.info("Have been called the fallBackGetDiscsByIdBand method");
		return new ResponseEntity("Right now the disc can not be found, please try later", HttpStatus.OK);
	}
	
	@CircuitBreaker(name = "genreCB",fallbackMethod = "fallBackGetGenreById")
	@GetMapping("/genre/{idGenre}")
	public ResponseEntity<Genre> getGenreById(@PathVariable("idGenre") long idGenre){
		log.info("Have been called the getGenreById method");
		Genre genre = bandService.getGenreById(idGenre);
		return ResponseEntity.ok(genre);
	}
	
	public ResponseEntity<Genre> fallBackGetGenreById(@PathVariable("idGenre") long idGenre, RuntimeException excepcion){
		log.info("Have been called the fallBackGetGenreById method");
		return new ResponseEntity("Right now the genre can not be found, please try later", HttpStatus.OK);
	}
	
}
