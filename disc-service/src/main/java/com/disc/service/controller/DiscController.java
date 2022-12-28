package com.disc.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.disc.service.entity.Disc;
import com.disc.service.service.DiscService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/disc")
public class DiscController {

	@Autowired
	private DiscService discService;
	
	@GetMapping
	public ResponseEntity<List<Disc>> listBands(){
		log.info("Have been called the listBands method");
		List<Disc> discs = discService.getAll();
		if (discs.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(discs);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Disc> getBand(@PathVariable("id") int id){
		log.info("Have been called the getBand method");
		Disc disc = discService.getBandById(id);
		if (disc == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(disc);
	}
	
	@PostMapping
	public ResponseEntity<Disc> saveDisc(@RequestBody Disc disc){
		log.info("Have been called the saveDisc method");
		Disc newDisc = discService.save(disc);
		return ResponseEntity.ok(newDisc);
	}
	
	@GetMapping("/band/{idBand}")
	public ResponseEntity<List<Disc>> getBandsById(@PathVariable("idBand") long idBand){
		log.info("Have been called the getBandsById method");
		List<Disc> disc = discService.getDiscsByIdBand(idBand);
		if (disc == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(disc);
	}
	
}
