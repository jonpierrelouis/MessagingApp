package com.messaging.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.messaging.models.Friend;
import com.messaging.services.FriendService;

@RestController
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
	public Optional<List<String>> getFriendsList(HttpSession session, HttpServletRequest req){
		
		Object userId = session.getAttribute("userId");
		
		
		return friendService.findByUsingUserId((Integer) userId);	
	}
	
	/**
	 * Allows the user to add a friend
	 * @param session
	 * @param req
	 * @return the friend object
	 */
	public Friend addFriend(HttpSession session, HttpServletRequest req) {
		
		Object userId = session.getAttribute("userId");
		int friend = Integer.parseInt(req.getParameter("friend"));
		
		return friendService.addFriend((Integer) userId, friend);
	}

}
