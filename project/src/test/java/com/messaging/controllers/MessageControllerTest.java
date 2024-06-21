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

import com.messaging.controller.MessageController;
import com.messaging.models.Message;
import com.messaging.models.MessageDTO;
import com.messaging.models.User;
import com.messaging.services.MessageService;
import com.messaging.services.UserService;

@SpringBootTest
public class MessageControllerTest {

	@Mock
	private MessageService messageService;
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private MessageController messageController;
	
	@Test
	public void storeMessageBodyTest() {
		Message message = new Message(1,2, "Can you pick me up tomorrow?");
		MessageDTO messageDTO = new MessageDTO("Chris", "Can you pick me up tomorrow?");
		User user1 = new User(1, "Chris", "password123");
		User user2 = new User(2, "Alexis", "password456");
		MessageDTO expected = new MessageDTO("Chris", "Can you pick me up tomorrow?");
		
		when(userService.findByUserId(message.getSenderId())).thenReturn(Optional.ofNullable(user1));
		when(userService.findByUserId(message.getRecipientId())).thenReturn(Optional.ofNullable(user2));
		when(messageService.storeMessage(message.getSenderId(), message.getRecipientId(), message.getMessageBody())).thenReturn(message);
		when(messageService.rawMessageToDTO(new ArrayList<>(Arrays.asList(message)), user1, user2)).thenReturn(Arrays.asList(messageDTO));
		
		List<MessageDTO> actual = messageController.storeMessage(message);
		
		assertEquals(expected.getSenderName(), actual.get(0).getSenderName());
		assertEquals(expected.getMessageBody(), actual.get(0).getMessageBody());
	}
	
	@Test
	public void retrieveMessages() {
		int userId = 1;
		int friendId = 2;
		List<Message> messages = new ArrayList<>(Arrays.asList(
				new Message(1, 2, "want to get lunch today"),
				new Message(2, 1, "to busy. maybe tommorrow"),
				new Message(1, 2, "cant i have an meeting"),
				new Message(1, 2, "what about next week?") ));
		Optional<List<Message>> expected = Optional.ofNullable(new ArrayList<>(Arrays.asList(
				new Message(1, 2, "want to get lunch today"),
				new Message(2, 1, "to busy. maybe tommorrow"),
				new Message(1, 2, "cant i have an meeting"),
				new Message(1, 2, "what about next week?") )));
		
		when(messageService.retrieveMessages(userId, friendId)).thenReturn(Optional.ofNullable(messages));
		
		Optional<List<Message>> actual = messageController.retrieveMessages(userId, friendId); 
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void retrieveMessagesDTO() {
		User user1 = new User(1, "Chris", "password123");
		User user2 = new User(2, "Alexis", "password456");
//		List<Message> messages = new ArrayList<>(Arrays.asList(
//				new Message(1, 2, "want to get lunch today"),
//				new Message(2, 1, "to busy. maybe tommorrow"),
//				new Message(1, 2, "cant i have an meeting"),
//				new Message(1, 2, "what about next week?") ));
		Optional<List<Message>> returnedMessages = Optional.ofNullable(new ArrayList<>(Arrays.asList(
				new Message(1, 2, "want to get lunch today"),
				new Message(2, 1, "to busy. maybe tommorrow"),
				new Message(1, 2, "cant i have an meeting"),
				new Message(1, 2, "what about next week?") )));
		List<MessageDTO> returnedMessagesDTO = new ArrayList<>(Arrays.asList(
				new MessageDTO("Chris", "want to get lunch today"),
				new MessageDTO("Alexis", "to busy. maybe tommorrow"),
				new MessageDTO("Chris", "cant i have an meeting"),
				new MessageDTO("Chris", "what about next week?") ));
		List<MessageDTO> expected = new ArrayList<>(Arrays.asList(
				new MessageDTO("Chris", "want to get lunch today"),
				new MessageDTO("Alexis", "to busy. maybe tommorrow"),
				new MessageDTO("Chris", "cant i have an meeting"),
				new MessageDTO("Chris", "what about next week?") ));
		
		when(messageService.retrieveMessages(user1.getUserId(), user2.getUserId())).thenReturn(returnedMessages);
		when(userService.findByUserId(user1.getUserId())).thenReturn(Optional.ofNullable(user1));
		when(userService.findByUserId(user2.getUserId())).thenReturn(Optional.ofNullable(user2));
		when(messageService.rawMessageToDTO(returnedMessages.get(), user1, user2)).thenReturn(returnedMessagesDTO);
		
		List<MessageDTO> actual = messageController.retrieveMessagesDTO(user1.getUserId(), user2.getUserId()); 
		
		assertEquals(expected, actual);
	}
	
	
}
