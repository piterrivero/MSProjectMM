package com.disc.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.disc.service.entity.Disc;
import com.disc.service.repository.DiscRepository;

@Service
public class DiscService {

	@Autowired
	private DiscRepository discRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	public List<Disc> getAll(){
		return discRepository.findAll();
	}
	
	public Disc getBandById(int id) {
		return discRepository.findById(id).orElse(null);
	}
	
	public Disc save(Disc disc) {
		disc.setId(sequenceGenerator.generateSequence(Disc.SEQUENCE_NAME));
		Disc newDisc = discRepository.save(disc);
		return newDisc;
	}
	
	public List<Disc> getDiscsByIdBand(long idBand){
		Query query = new Query();
		query.addCriteria(Criteria.where("idBand").is(idBand));
		List<Disc> discs = mongoTemplate.find(query, Disc.class);
		return discs;
	}
	
}
