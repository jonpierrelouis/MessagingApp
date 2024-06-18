package com.messaging.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

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
	public void createUserTest() {		
		User user = new User("Alice", "Bob");
		User expectedUser = new User("Alice", "***");		
		when(userRepository.save(user)).thenReturn(user);

		User actual = userService.createUser("Alice", "Bob");
		
		assertEquals(expectedUser.getUsername(), actual.getUsername());
		assertEquals(expectedUser.getPassword(), actual.getPassword());
	}
	
	@Test
	public void loginTestValidPassword() {
		User user = new User("Alice", "Bob");
		Optional<User> expectedUser = Optional.ofNullable(new User("Alice", "***"));	
		when(userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())).thenReturn(Optional.ofNullable(user));
		
		Optional<User> actual = userService.login("Alice", "Bob");
		
		assertEquals(expectedUser, actual);
	}
	
	@Test
	public void loginTestInvalidPassword() { 
		User user = new User("Alice", "Bob");	
		Optional<User> expectedUser = Optional.empty();

		when(userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())).thenReturn(expectedUser);
		
		Optional<User> actual = userService.login("Alice", "Bob");
		
		assertEquals(expectedUser, actual);
	}
	
	@Test
	public void findByIdTest() {
		int userId = 1;
		User user = new User(1,"Alice", "Bob");
		Optional<User> expectedUser = Optional.ofNullable(user);
		
		when(userRepository.findById(userId)).thenReturn(expectedUser);
		
		Optional<User> actual = userService.findByUserId(userId);
		
		assertEquals(expectedUser, actual); 
		
	}
	
	@Test
	public void findByUsernameTest() {
		String username = "Alice";
		User user = new User(1, "Alice", "Bob");
		Optional<User> expectedUser = Optional.ofNullable(user);
		
		when(userRepository.findByUsername(username)).thenReturn(expectedUser);
		
		Optional<User> actual = userService.findByUsername(username);
		
		assertEquals(expectedUser, actual);
		
	}
}
