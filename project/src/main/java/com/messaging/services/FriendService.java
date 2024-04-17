package com.messaging.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messaging.models.Friend;
import com.messaging.models.User;
import com.messaging.repositories.FriendRepository;
import com.messaging.repositories.UserRepository;

@Service
public class FriendService {
	
	private final FriendRepository friendRepo;
	private final UserRepository userRepo;
	
	@Autowired
	public FriendService(FriendRepository friendRepo, UserRepository userRepo) {
		super();
		this.friendRepo = friendRepo;
		this.userRepo = userRepo;
	}
	
	/**
	 * Get a list of friends of the user
	 * @param userId
	 * @return
	 */
	public List<User> findByUsingUserId(int userId){
		
		Optional<List<Friend>> friends = friendRepo.findByUserId(userId);
		
		List<User> users = new ArrayList<>();
		
		for(Friend friend : friends.get()) {
			User user = userRepo.findById(friend.getFriendId()).get();
			
			User newUser = new User(user.getUserId(), user.getUsername());
			
			users.add(newUser);
		}
		
		return users;
	}
	
	/**
	 * Allows the user to add a friend
	 * @param userId
	 * @param friendId
	 * @return the user's friend object
	 */
	public Friend addFriend(int userId, int friendId) {
		friendRepo.save(new Friend(friendId, userId));
		return friendRepo.save(new Friend(userId, friendId));
	}

}
