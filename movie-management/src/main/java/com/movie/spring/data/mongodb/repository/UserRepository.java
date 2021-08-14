package com.movie.spring.data.mongodb.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.movie.spring.data.mongodb.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findByEmail(String email);
}
