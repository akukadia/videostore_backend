package com.movie.spring.data.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.movie.spring.data.mongodb.model.TVShow;

public interface TVShowRepository extends MongoRepository<TVShow, String> {

}
