package com.ved_api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ved_api.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByEmail(String email);
}
