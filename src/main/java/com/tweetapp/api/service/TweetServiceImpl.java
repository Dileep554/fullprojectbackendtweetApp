package com.tweetapp.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.api.exception.IncorrectOrDeletedTweet;
import com.tweetapp.api.kafka.ProducerService;
import com.tweetapp.api.model.Tweet;
import com.tweetapp.api.model.User;
import com.tweetapp.api.repository.TweetRepository;
import com.tweetapp.api.repository.UserRepository;

@Service
public class TweetServiceImpl implements TweetService 
{

	@Autowired
	TweetRepository tweetRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProducerService producerService;
	
	Logger logger = LoggerFactory.getLogger(TweetServiceImpl.class);
	
	@Override
	public Tweet postTweet(Tweet tweet) 
	{
		

		
		producerService.sendMessage("Tweet posted by user");
		logger.info("Tweet posted successfully....");
		return tweetRepository.save(tweet);
	}

	@Override
	public Tweet editTweet(Tweet tweet) 
	{

		producerService.sendMessage("Tweet is updated..");
		logger.info("Tweet is updated successfully...");
		return tweetRepository.save(tweet);
	}

	@Override
	public Tweet likeTweet(Tweet tweet)
	{

		tweet.setLikes(tweet.getLikes()+1);
		return tweetRepository.save(tweet);
	}

	@Override
	public Tweet replyTweet(Tweet parentTweet, Tweet replyTweet) 
	{
		tweetRepository.save(replyTweet);
		List<Tweet> parentTweetReplies = parentTweet.getReplies();
		parentTweetReplies.add(replyTweet);
		parentTweet.setReplies(parentTweetReplies);
		tweetRepository.save(parentTweet);
		return replyTweet;
	}

	@Override
	public void deleteTweet(Tweet tweet) 
	{
		tweetRepository.delete(tweet);
		logger.info("Tweet deleted successfully...");
	}

	@Override
	public List<Tweet> getAllTweets() 
	{
	    producerService.sendMessage("Received request to send all tweet data.");
		logger.info("Retriving all the tweet data");
		return tweetRepository.findAll();
	}

	@Override
	public List<Tweet> getAllTweetsByUsername(String username) 
	{
		logger.info("Retriving tweets of user: "+username);
		return tweetRepository.findByUserUsername(username);
	}

	@Override
	public Tweet postTweetByUsername(Tweet tweet, String username) 
	{
		User user = userRepository.findByUsername(username);
		tweet.setUser(user);
		//producerService.sendMessage("Tweet posted by the user : "+username);
		logger.info("Tweet posted by user: "+username);
		return tweetRepository.save(tweet);
		
	}

	@Override
	public void deleteTweetById(String tweetId) 
	{
		tweetRepository.deleteById(tweetId);
		logger.info("Deleted thw tweet for the tweet id"+tweetId);
	}

	@Override
	public Tweet replyTweetById(Tweet replyTweet, String parentTweetId) throws IncorrectOrDeletedTweet 
	{
		Optional<Tweet> parentTweet = tweetRepository.findById(parentTweetId);
		if (parentTweet.isPresent()) {
			List<Tweet> replies = parentTweet.get().getReplies();
			replies.add(replyTweet);
			tweetRepository.save(parentTweet.get());
		}
		else {
			throw new IncorrectOrDeletedTweet("Incorrect or deleted parent tweet id.");
		}
		return replyTweet;
		

	}

	@Override
	public void likeTweetById(String tweetId) 
	{
		Optional<Tweet> tweet = tweetRepository.findById(tweetId);
		logger.info("Liked Tweet with Id: {} is {}", tweetId, tweet.get());
		if (tweet.isPresent()) {
			tweet.get().setLikes(tweet.get().getLikes()+1);
			tweetRepository.save(tweet.get());
		}
		
	}

}
