package com.tweetapp.api.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.tweetapp.api.exception.InvalidUsernameOrPasswordException;
import com.tweetapp.api.exception.UsernameAlreadyExists;
import com.tweetapp.api.model.User;
import com.tweetapp.api.model.UserResponse;

public interface UserService {
	
	User createUser(User user) throws UsernameAlreadyExists;
	User updateUser(User user);
	int deleteUser(User user);
	List<User> getAllUsers();
	List<User> getUserByUsername(String username) throws InvalidUsernameOrPasswordException;
	Optional<User> getUserById(String id);
	UserResponse loginUser(String username, String password) throws InvalidUsernameOrPasswordException;
	Map<String, String> forgotPassword(String username);
	Map<String,String>resetPassword(String username,String password);
}
