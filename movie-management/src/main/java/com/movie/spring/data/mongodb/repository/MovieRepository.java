package com.movie.spring.data.mongodb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.movie.spring.data.mongodb.model.Movie;
import com.movie.spring.data.mongodb.model.User;

public interface MovieRepository extends MongoRepository<Movie, String> {

	List<Movie> findByMovieType(String movieType);
}
