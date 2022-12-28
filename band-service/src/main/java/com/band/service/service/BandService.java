package com.band.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.band.service.entity.Band;
import com.band.service.feignclients.DiscFeignClient;
import com.band.service.feignclients.GenreFeignClient;
import com.band.service.repository.BandRepository;

import model.Disc;
import model.Genre;

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
		return bandRepository.findAll();
	}
	
	public Band getBandById(int id) {
		return bandRepository.findById(id).orElse(null);
	}
	
	public Band save(Band band) {
		band.setId(sequenceGenerator.generateSequence(Band.SEQUENCE_NAME));
		Band newBand = bandRepository.save(band);
		return newBand;
	}
	
	public Disc saveDisc(Disc disc) {
		Disc newDisc = discFeignClient.saveDisc(disc);
		return newDisc;
	}
	
	public Genre getGenreById(long id) {
		Genre genre = genreFeignClient.getGenreById(id);
		return genre;
	}	
	
	public List<Disc> getDiscByIdBand(long id){
		List<Disc> discs = discFeignClient.listDiscsByIdBand(id);
		return discs;
	}
	
}
