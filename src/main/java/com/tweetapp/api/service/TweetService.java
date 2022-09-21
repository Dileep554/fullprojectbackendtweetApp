package com.tweetapp.api.service;

import java.util.List;

import com.tweetapp.api.exception.IncorrectOrDeletedTweet;
import com.tweetapp.api.model.Tweet;

public interface TweetService {
	
	Tweet postTweet(Tweet tweet);
	Tweet postTweetByUsername(Tweet tweet, String username);
	Tweet editTweet(Tweet tweet);
	Tweet likeTweet(Tweet tweet);
	Tweet replyTweet(Tweet parentTweet, Tweet replyTweet);
	void deleteTweet(Tweet tweet);
	List<Tweet> getAllTweets();
	List<Tweet> getAllTweetsByUsername(String username);
	Tweet replyTweetById(Tweet replyTweet, String parentTweetId) throws IncorrectOrDeletedTweet;
	void deleteTweetById(String tweetId);
	void likeTweetById(String tweetId);

}
