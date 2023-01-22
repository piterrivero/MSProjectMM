package com.band.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.band.service.entity.Band;
import com.band.service.feignclients.DiscFeignClient;
import com.band.service.feignclients.GenreFeignClient;
import com.band.service.model.DiscDTO;
import com.band.service.model.GenreDTO;
import com.band.service.repository.BandRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BandService {

	@Autowired
	private BandRepository bandRepository;
	
	@Autowired
	private DiscFeignClient discFeignClient;
	
	@Autowired
	private GenreFeignClient genreFeignClient;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	public List<Band> getAll(){
		log.info("Have been called the getAll method on the BandService class");
		return bandRepository.findAll();
	}
	
	public Band getBandById(int id) {
		log.info("Have been called the getBandById method on the BandService class");
		Optional<Band> band = bandRepository.findById(id);
		return band.isPresent() ? band.get() : null;
	}
	
	public Band save(Band band) {
		log.info("Have been called the save method on the BandService class");
		band.setId(sequenceGenerator.generateSequence(Band.SEQUENCE_NAME));
		return bandRepository.save(band);
	}
	
	public DiscDTO saveDisc(DiscDTO disc) {
		log.info("Have been called the saveDisc method on the BandService class");
		return discFeignClient.saveDisc(disc);
	}
	
	public GenreDTO getGenreById(long id) {
		log.info("Have been called the getGenreById method on the BandService class");
		return genreFeignClient.getGenreById(id);
	}	
	
	public List<DiscDTO> getDiscByIdBand(long id){
		log.info("Have been called the getDiscByIdBand method on the BandService class");
		return discFeignClient.listDiscsByIdBand(id);
	}
	
	public Band update(int id, Band band) {
		log.info("Have been called the update method on the BandService class");
		Band toUpdate =  getBandById(id);
		toUpdate.setIdGenre(band.getIdGenre());
		toUpdate.setCountry(band.getCountry());
		toUpdate.setName(band.getName());
		return bandRepository.save(toUpdate);
	}
	
	public void delete(int id) {
		log.info("Have been called the delete method on the BandService class");
		bandRepository.deleteById(id);
	}
}
