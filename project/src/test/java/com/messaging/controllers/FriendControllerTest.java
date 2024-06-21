package com.messaging.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.messaging.controller.FriendController;
import com.messaging.models.AddFriendDTO;
import com.messaging.models.Friend;
import com.messaging.models.User;
import com.messaging.services.FriendService;
import com.messaging.services.UserService;

@SpringBootTest
public class FriendControllerTest {
	
	@Mock
	private FriendService friendService;
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private FriendController friendController;
	
	@Test
	public void getFriendsListTest() {
		int userId = 1;
		List<User> friends = new ArrayList<>(Arrays.asList(
				new User(2, "Kyle", "password123"),
				new User(5, "Jen", "password456"),
				new User(8, "Jill", "password789"),
				new User(9, "Quinn", "password1111")));
		
		List<User> expected = new ArrayList<>(Arrays.asList(
				new User(2, "Kyle", "password123"),
				new User(5, "Jen", "password456"),
				new User(8, "Jill", "password789"),
				new User(9, "Quinn", "password1111")));
		
		when(friendService.findByUsingUserId(userId)).thenReturn(friends);
		
		List<User> actual = friendController.getFriendsList(userId);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void addFriend() {
		AddFriendDTO addFriendDTO = new AddFriendDTO(25, "Jacob");
		User newFriend = new User(44, "Jacob", "password123");
		Friend friend = new Friend(addFriendDTO.getUserId(), newFriend.getUserId());
		Friend expected = new Friend(addFriendDTO.getUserId(), newFriend.getUserId());
		
		when(userService.findByUsername(addFriendDTO.getNewFriend())).thenReturn(Optional.ofNullable(newFriend));
		when(friendService.addFriend(addFriendDTO.getUserId(), newFriend.getUserId())).thenReturn(friend);
		
		Friend actual = friendController.addFriend(addFriendDTO);
		
		assertEquals(expected, actual);
		
	}
}
