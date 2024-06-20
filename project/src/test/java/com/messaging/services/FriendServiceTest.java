package com.messaging.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.messaging.models.Friend;
import com.messaging.models.User;
import com.messaging.repositories.FriendRepository;
import com.messaging.repositories.UserRepository;

@SpringBootTest
public class FriendServiceTest {

	@Mock
	private FriendRepository friendRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private FriendService friendService;
	
	@Test
	public void FindByUserIdTest() {
		int userId = 1;
		List<Friend> friendsInt = new ArrayList<>(Arrays.asList(
				new Friend(userId, 2),
				new Friend(userId, 5),
				new Friend(userId, 8),
				new Friend(userId, 13)));
		List<User> expected = new ArrayList<>(Arrays.asList(
				new User(2, "Nick"),
				new User(5, "Bill"),
				new User(8, "Atticus"),
				new User(13, "Joey")
				));
		
		when(friendRepository.findByUserId(userId)).thenReturn(Optional.ofNullable(friendsInt));
		when(userRepository.findById(2)).thenReturn(Optional.ofNullable(new User(2, "Nick", "password")));
		when(userRepository.findById(5)).thenReturn(Optional.ofNullable(new User(5, "Bill", "password")));
		when(userRepository.findById(8)).thenReturn(Optional.ofNullable(new User(8, "Atticus", "password")));
		when(userRepository.findById(13)).thenReturn(Optional.ofNullable(new User(13, "Joey", "password")));
		
		List<User> actual = friendService.findByUsingUserId(userId);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void addFriendTest() {
		int userId = 1;
		int friendId = 4;
		Friend friend = new Friend(1,4);
		Friend expected = new Friend(1,4);
		
		when(friendRepository.save(new Friend(userId, friendId))).thenReturn(friend);
		
		Friend actual = friendService.addFriend(userId, friendId);
		
		assertEquals(expected, actual);
		verify(friendRepository, times(1)).save(new Friend(friendId, userId));
		verify(friendRepository, times(1)).save(new Friend(userId, friendId));
		
	}
}
