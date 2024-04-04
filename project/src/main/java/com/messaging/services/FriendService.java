package com.messaging.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messaging.models.Friend;
import com.messaging.repositories.FriendRepository;

@Service
public class FriendService {
	
	private final FriendRepository friendRepo;
	
	@Autowired
	public FriendService(FriendRepository friendRepo) {
		super();
		this.friendRepo = friendRepo;
	}
	
	/**
	 * Get a list of friends of the user
	 * @param userId
	 * @return
	 */
	public Optional<List<String>> findByUsingUserId(int userId){
		
		return friendRepo.findByUserId(userId);
	}
	
	/**
	 * Allows the user to add a friend
	 * @param userId
	 * @param friendId
	 * @return the user's friend object
	 */
	public Friend addFriend(int userId, int friendId) {
		
		return friendRepo.save(new Friend(userId, friendId));
	}

}
