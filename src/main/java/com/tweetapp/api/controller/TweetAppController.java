package com.tweetapp.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.tweetapp.api.exception.InvalidUsernameOrPasswordException;
import com.tweetapp.api.exception.UsernameAlreadyExists;
import com.tweetapp.api.kafka.ProducerService;
//import com.tweetapp.api.kafka.ProducerService;
import com.tweetapp.api.model.ErrorMessages;
import com.tweetapp.api.model.Tweet;
import com.tweetapp.api.model.User;
import com.tweetapp.api.model.UserResponse;
import com.tweetapp.api.service.TweetService;
import com.tweetapp.api.service.UserService;
/**
 * 
 * @author Resolute
 * 
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1.0/tweets")
public class TweetAppController 
{
	
	@Autowired
	UserService userService;
	
	@Autowired
	TweetService tweetService;
	
	@Autowired
	ProducerService producerService; 
	
	
	
	
	Logger logger = LoggerFactory.getLogger(TweetAppController.class);
	
	
	
    

	/*
	 * This redirects to the  method to register the users in the tweet app
	 * */
	@PostMapping("/register")
	public ResponseEntity<Object> registerUser(@RequestBody User user) throws UsernameAlreadyExists 
	{
		try {
			logger.info("User created successfully!!!...");
			return new ResponseEntity<>(userService.createUser(user),HttpStatus.CREATED);
		}
		 catch (UsernameAlreadyExists e) {
				throw new UsernameAlreadyExists(ErrorMessages.USER_ALREADY_EXISTS.getMessage());
			}
		
	}
	
	/*
	 * This redirects to the  method to login the valid users into the tweet app
	 * */
	@ResponseBody
	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(Model model, @RequestBody User user, HttpServletRequest request) throws InvalidUsernameOrPasswordException 
	{	
		
		logger.debug("----Inside TweetAppController-> loginUser()------");
		try {
			 UserResponse authUser = userService.loginUser(user.getUsername(), user.getPassword());
			System.out.print(authUser);
			if (authUser != null) {
				request.getSession().setAttribute("user", user.getUsername());
				return new ResponseEntity<UserResponse>(authUser, HttpStatus.OK);
			} else {
				throw new InvalidUsernameOrPasswordException(ErrorMessages.INVALID_CREDENTIALS.getMessage());
			}
		} catch (InvalidUsernameOrPasswordException e) {
			throw new InvalidUsernameOrPasswordException(ErrorMessages.INVALID_CREDENTIALS.getMessage());
		}
	}
	
	
	/*
	 * This redirects to forgot password page...
	 * */
	@ResponseBody
	@GetMapping("/{username}/forgot")
	public Map<String,String> forgotPassword(@PathVariable("username") String username)
	{
		logger.info("Forgot Password request received with username: "+ username);
		return new HashMap<String, String>(userService.forgotPassword(username));
		
	}
	
	/*
	 * This redirects to method  to reset the password for the logged in users
	 * */
	@ResponseBody
	@PostMapping("/reset")
	public Map<String,String> resetUserPassword(@RequestBody User user) 
	{
		logger.info("Request to reset the password");
		return new HashMap<String, String>(userService.resetPassword(user.getUsername(),user.getPassword()));
		
	}
	
	/*
	 * This redirects to the method that retreives all the tweets
	 * */
	@GetMapping("/all")
	public ResponseEntity<List<Tweet>> getAllTweets() 
	{
		logger.info("Retreive all the tweets by the all users");
		return new ResponseEntity<>(tweetService.getAllTweets(),HttpStatus.OK);
	}
	
	/*
	 * This redirects to the method to retrive all the users
	 * */
	@GetMapping("/users/all")
	public ResponseEntity<List<User>> getAllUsers() 
	{
		logger.info("Retrived all the users");
		return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
	}
	
	/*
	 * This redirects to the method to search user by the username
	 * */
	@GetMapping("/user/search/{username}")
	public ResponseEntity<List<User>> searchUser(@PathVariable("username") String username) throws InvalidUsernameOrPasswordException 
	{
		logger.info("Retriving the user by the username");
		return new ResponseEntity<>(userService.getUserByUsername(username),HttpStatus.OK);
	}
	
	/*
	 * This redirects to the method to get all the tweets for a respective user
	 * */
	@GetMapping("/{username}")
	public ResponseEntity<List<Tweet>> getAllTweetsByUser(@PathVariable("username") String username) 
	{
		logger.info("Retriving all the tweets by the user");
		return new ResponseEntity<>(tweetService.getAllTweetsByUsername(username),HttpStatus.OK);
	}
	
	/*
	 * This redirects to the method to post tweet by the user
	 * */
	@PostMapping("/{username}/add")
	public ResponseEntity<Tweet> postTweetByUser(@PathVariable("username") String username, @RequestBody Tweet tweet)
	{
		logger.info("Tweet successfully posted by the user");
		return new ResponseEntity<>(tweetService.postTweetByUsername(tweet, username),HttpStatus.OK);
	}
	
	/*
	 * This redirects to the method to update the tweet
	 * */
	@PutMapping("/{username}/update/{id}")
	public ResponseEntity<Tweet> updateTweetByUser(@PathVariable("username") String username, @PathVariable("id") String tweetId, @RequestBody Tweet tweet) 
	{
		logger.info("Tweet successfully updated by the user");
		return new ResponseEntity<>(tweetService.editTweet(tweet),HttpStatus.OK);
	}
	
	/*
	 * This redirects to the method of deleting the tweet by the user
	 * */
	@DeleteMapping("/{username}/delete/{id}")
	public ResponseEntity<HttpStatus> deleteTweetByUser(@PathVariable("username") String username, @PathVariable("id") String tweetId) 
	{
	
		tweetService.deleteTweetById(tweetId);
		logger.info("Tweet deleted by the user");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/*
	 * This redirects to the method to like post 
	 * */
	@PutMapping("/{username}/like/{id}")
	public ResponseEntity<HttpStatus> likeTweetByUser(@PathVariable("username") String username, @PathVariable("id") String tweetId) 
	{
		tweetService.likeTweetById(tweetId);
		logger.info("Like a tweet ");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/*
	 * This redirects to the method to reply to a tweet
	 * */
	@PostMapping("/{username}/reply/{id}")
	public ResponseEntity<Tweet> replyTweetByUser(@PathVariable("username") String username, @PathVariable("id") String tweetId, @RequestBody Tweet replyTweet) 
	{
		
		try 
		{
			logger.info("Replying to the tweet by user");
			return new ResponseEntity<>(tweetService.replyTweetById(replyTweet, tweetId),HttpStatus.OK);
		} catch (Exception e) {

			e.printStackTrace();
			return new ResponseEntity<>(new Tweet(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
