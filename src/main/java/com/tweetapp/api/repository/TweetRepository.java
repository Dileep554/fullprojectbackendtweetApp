package com.tweetapp.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.api.model.Tweet;

public interface TweetRepository extends MongoRepository<Tweet, String> {
	
	List<Tweet> findByUserUsername(String username);
	

}
