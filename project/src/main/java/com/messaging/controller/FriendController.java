package com.messaging.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messaging.models.Friend;
import com.messaging.models.User;
import com.messaging.services.FriendService;

@RestController
@RequestMapping("/friends")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class FriendController {
	
	private final FriendService friendService;

	@Autowired
	public FriendController(FriendService friendService) {
		super();
		this.friendService = friendService;
	}
	
	/**
	 * 
	 * @param session
	 * @param req
	 * @return returns a list of the user's friends
	 */
	@GetMapping(value = "/getFriends")
	public List<User> getFriendsList(HttpSession session, HttpServletRequest req){
		
		Object userId = session.getAttribute("userId");
		System.out.println("userId " +userId);
		
		return friendService.findByUsingUserId((Integer) userId);	
	}
	
	/**
	 * 
	 * @param userId
	 * @return returns a list of the user's friends
	 */
	@GetMapping(value = "/getFriends/{userId}")
	public List<User> getFriendsList(@PathVariable int userId){
		
		return friendService.findByUsingUserId(userId);
	}
	
	/**
	 * Allows the user to add a friend
	 * @param session
	 * @param req
	 * @return the friend object
	 */
	@PostMapping(value = "/addFriend")
	public Friend addFriend(HttpSession session, HttpServletRequest req) {
		
		Object userId = session.getAttribute("userId");
		int friend = Integer.parseInt(req.getParameter("friend"));
		
		return friendService.addFriend((Integer) userId, friend);
	}

}
