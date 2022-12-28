package com.band.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.band.service.entity.Band;

@Repository
public interface BandRepository extends MongoRepository<Band, Integer> {

}
