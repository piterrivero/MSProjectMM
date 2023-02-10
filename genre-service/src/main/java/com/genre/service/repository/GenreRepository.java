package com.genre.service.repository;

import com.genre.service.entity.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends MongoRepository<Genre, Integer> {

}
