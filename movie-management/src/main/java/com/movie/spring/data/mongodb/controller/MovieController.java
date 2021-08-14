package com.movie.spring.data.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.spring.data.mongodb.model.Movie;
import com.movie.spring.data.mongodb.model.User;
import com.movie.spring.data.mongodb.repository.MovieRepository;

@CrossOrigin(origins = "https://videostorecjv.herokuapp.com")
@RestController
@RequestMapping("/api")
public class MovieController {

  @Autowired
  MovieRepository movieRepository;

  @PostMapping("/movie")
  public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
    try {
    	Movie _movie = movieRepository.save(new Movie(movie.getName(), movie.getImageURL(), movie.getOverview(), movie.getRating(), movie.getMovieType(), movie.getPrice()));
      return new ResponseEntity<>(_movie, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  
  @GetMapping("/movie/{id}")
  public ResponseEntity<Movie> getMovieById(@PathVariable("id") String id) {
    Optional<Movie> movieData = movieRepository.findById(id);
    if (movieData.isPresent()) {
      return new ResponseEntity<>(movieData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/movie")
  public ResponseEntity<List<Movie>> getAllMovies() {
    try {
      List<Movie> movies = movieRepository.findAll();
      List<Movie> result = new ArrayList<>();
      for(Movie movie : movies) {
    	  if(!movie.getMovieType().equals("Comedy") && !movie.getMovieType().equals("Action")) {
    		  result.add(movie);
    	  }
      }
      if (result.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/comedymovie")
  public ResponseEntity<List<Movie>> getComedyMovies() {
	    try {
	    	List<Movie> movieData = movieRepository.findByMovieType("Comedy");
	      if (movieData.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(movieData, HttpStatus.OK);
	    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @GetMapping("/actionmovie")
  public ResponseEntity<List<Movie>> getActionMovies() {
    try {
    	List<Movie> movieData = movieRepository.findByMovieType("Action");
      if (movieData.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(movieData, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
