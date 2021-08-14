package com.movie.spring.data.mongodb.controller;

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

import com.movie.spring.data.mongodb.model.TVShow;
import com.movie.spring.data.mongodb.repository.TVShowRepository;

@CrossOrigin(origins = "https://videostorecjv.herokuapp.com")
@RestController
@RequestMapping("/api")
public class TVShowController {

  @Autowired
  TVShowRepository tvShowRepository;

  @PostMapping("/tvshow")
  public ResponseEntity<TVShow> createTVShow(@RequestBody TVShow tvShow) {
    try {
    	TVShow _tvShow = tvShowRepository.save(new TVShow(tvShow.getName(), tvShow.getImageURL(), tvShow.getOverview(), tvShow.getRating(), tvShow.getMovieType(), tvShow.getPrice()));
      return new ResponseEntity<>(_tvShow, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  
  @GetMapping("/tvshow/{id}")
  public ResponseEntity<TVShow> getTVshowById(@PathVariable("id") String id) {
    Optional<TVShow> tvShowData = tvShowRepository.findById(id);

    if (tvShowData.isPresent()) {
      return new ResponseEntity<>(tvShowData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/tvshow")
  public ResponseEntity<List<TVShow>> getAllTVShows() {
    try {
      List<TVShow> tvShows = tvShowRepository.findAll();
      if (tvShows.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(tvShows, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
