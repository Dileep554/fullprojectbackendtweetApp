package com.tweetapp.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.api.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	List<User> findByUsernameContaining(String username);
	User findByUsername(String username);
	

}
