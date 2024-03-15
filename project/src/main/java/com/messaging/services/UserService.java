package com.messaging.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messaging.models.User;
import com.messaging.repositories.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepo;

	@Autowired
	public UserService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	/**
	 * Method used to create a new user
	 * @param username
	 * @param password
	 * @return user object
	 */
	public User createUser(String username, String password) {
		
		return userRepo.save(new User(username, password));
	}
	
	/**
	 * Method used to check username and password for logging in
	 * @param username
	 * @param password
	 * @return returns user obj if it exists
	 */
	public Optional<User> login(String username, String password) {
	
		return userRepo.findByUsernameAndPassword(username,password);
	}
	
	

}
