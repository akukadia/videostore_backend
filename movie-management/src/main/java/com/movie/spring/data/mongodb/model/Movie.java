package com.movie.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movie")
public class Movie {
  @Id
  private String id;

  private String name;
  private String imageURL;
  private String overview;
  private String rating;
  private String movieType;
  private String price;
  
public Movie() {
}


public Movie(String name, String imageURL, String overview, String rating, String movieType, String price) {
	super();
	this.name = name;
	this.imageURL = imageURL;
	this.overview = overview;
	this.rating = rating;
	this.movieType = movieType;
	this.price = price;
}


public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getImageURL() {
	return imageURL;
}
public void setImageURL(String imageURL) {
	this.imageURL = imageURL;
}
public String getOverview() {
	return overview;
}
public void setOverview(String overview) {
	this.overview = overview;
}
public String getRating() {
	return rating;
}
public void setRating(String rating) {
	this.rating = rating;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
public String getMovieType() {
	return movieType;
}
public void setMovieType(String movieType) {
	this.movieType = movieType;
}
 
}
