package com.messaging.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messaging.models.User;
import com.messaging.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {
	
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	/**
	 * Method to create account, info gotten from the front end
	 * @param req
	 * @return new account username and password if the pair does not exist already
	 * and returns an empty object if the pair does exist
	 */
	@PostMapping(value = "/register")
	public User createAccount(HttpServletRequest req){
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		//check to see if username and password pair already exists
		Optional<User> optionalUser = userService.login(username, password);
		
		//if username and password exist already return empty object
		if(optionalUser.isPresent()) {
			return new User("", "");
		}
		
		//create account if it does not exist
		return userService.createUser(username, password);
		
	}
	
	/**
	 * Method to login to the account
	 * @param session
	 * @param req
	 * @return if the username and password are correct, return the user object,
	 * if not return an empty object.
	 */
	@PostMapping(value = "/login")
	public ResponseEntity<User> loginUser(HttpSession session, HttpServletRequest req){
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		Optional<User> optionalUser = userService.login(username, password);
		
		//check to see if username and password is correct
		if(!optionalUser.isPresent()) {
//			return new User("", "");
			return ResponseEntity.badRequest().build();
		}
		
		int userId = optionalUser.get().getUserId();
		session.setAttribute("userId", userId);
		
		return ResponseEntity.ok(new User(userId, username, ""));
	}
	
	/**
	 * Method for logging out the user
	 * @param session
	 * @return redirection to the login page
	 */
	@PostMapping("/logout")
	public void logoutUser(HttpSession session){
		session.removeAttribute("userId");
		
	}

}
