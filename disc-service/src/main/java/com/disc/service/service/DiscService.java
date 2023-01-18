package com.disc.service.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.disc.service.entity.Disc;
import com.disc.service.repository.DiscRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DiscService {

	@Autowired
	private DiscRepository discRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	public List<Disc> getAll(){
		log.info("Have been called the getAll method on the DiscService class");
		return discRepository.findAll();
	}
	
	public List<Disc> getLiveDiscs(){
		log.info("Have been called the getLiveDiscs method on the DiscService class");
		return discRepository.findAll()
						.stream()
						.filter(x -> x.getTitle().toUpperCase().contains("LIVE"))
						.collect(Collectors.toList());
	}
	
	public Disc getDiscById(int id) {
		log.info("Have been called the getDiscById method on the DiscService class");
		Optional<Disc> disc = discRepository.findById(id);
		return disc.isPresent() ? disc.get() : null;
	}
	
	public Disc save(Disc disc) {
		log.info("Have been called the save method on the DiscService class");
		disc.setId(sequenceGenerator.generateSequence(Disc.SEQUENCE_NAME));
		return discRepository.save(disc);
	}
	
	public List<Disc> getDiscsByIdBand(long idBand){
		log.info("Have been called the getDiscsByIdBand method on the DiscService class");
		Query query = new Query();
		query.addCriteria(Criteria.where("idBand").is(idBand));
		return mongoTemplate.find(query, Disc.class);
	}
	
	public Disc update(int id, Disc disc) {
		log.info("Have been called the update method on the DiscService class");
		Disc toUpdate =  getDiscById(id);
		toUpdate.setIdBand(disc.getIdBand());
		toUpdate.setPrice(disc.getPrice());
		toUpdate.setStock(disc.getStock());
		toUpdate.setTitle(disc.getTitle());
		toUpdate.setYear(disc.getYear());
		return discRepository.save(toUpdate);
	}
	
	public void delete(int id) {
		log.info("Have been called the delete method on the DiscService class");
		discRepository.deleteById(id);
	}
	
}
