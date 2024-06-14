package com.messaging.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.messaging.models.User;
import com.messaging.repositories.UserRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void createUserTest1() {		
		//Arrange
		User user = new User("Alice", "Bob");
		User respUser = new User("Alice", "***");
		
		when(userRepository.save(user)).thenReturn(respUser);

		//Act
		User actual = userService.createUser("Alice", "Bob");
		
		
		//Assert
		//test name and password
		assertEquals(respUser.getUsername(), actual.getUsername());
		assertEquals(respUser.getPassword(), actual.getPassword());
	}
}
