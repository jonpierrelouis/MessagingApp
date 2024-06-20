package com.messaging.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import com.messaging.controller.UserController;
import com.messaging.models.User;
import com.messaging.services.UserService;

@SpringBootTest
public class UserControllerTest {
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private UserController userController;
	
	@Test
	public void createAccountTest() {
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.setParameter("username", "alice");
		req.setParameter("password", "bob");
		User expected = new User(1,"alice", "bob");
		
		when(userService.login("alice", "bob")).thenReturn(Optional.empty());
		when(userService.createUser("alice", "bob")).thenReturn(expected);
		
		User actual = userController.createAccount(req);
		
		assertEquals(expected, actual);		
	}
	
	@Test
	public void createInvalidAccountTest() {
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.setParameter("username", "alice");
		req.setParameter("password", "bob");
		User test = new User(1, "alice", "bob");
		User expected = new User(0,"", "");
		
		when(userService.login(test.getUsername(), test.getPassword())).thenReturn(Optional.ofNullable(test));
		when(userService.createUser("alice", "bob")).thenReturn( new User("invalid", "invalid"));
		
		User actual = userController.createAccount(req);
		
		assertEquals(expected, actual);		
	}
	
	@Test
	public void loginUser() {
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.setParameter("username", "alice");
		req.setParameter("password", "bob");
		MockHttpSession session = new MockHttpSession();
		User user = new User(1, "alice", "bob");
		User expected = new User(1, "alice", "");
		
		when(userService.login(user.getUsername(), user.getPassword())).thenReturn(Optional.ofNullable(expected));
		
		ResponseEntity<User> actual = userController.loginUser(session, req);
		
		assertEquals(expected.getUserId(), actual.getBody().getUserId());
		assertEquals(expected.getUsername(), actual.getBody().getUsername());
		assertEquals(expected.getPassword(), actual.getBody().getPassword());
		assertEquals(session.getAttribute("userId"), actual.getBody().getUserId());
	}

	@Test
	public void loginInvalidUser() {
		
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.setParameter("username", "alice");
		req.setParameter("password", "bob");
		MockHttpSession session = new MockHttpSession();
		User user = new User(1, "alice", "bob");
		
		when(userService.login(user.getUsername(), user.getPassword())).thenReturn(Optional.empty());
		
		ResponseEntity<User> actual = userController.loginUser(session, req);
		
		assertEquals(ResponseEntity.badRequest().build(), actual);
		
	}
	
	@Test
	public void logoutUser(){
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("userId", 1);
		
		userController.logoutUser(session);
		
		assertEquals(null, session.getAttribute("userId"));
		
	}

}
