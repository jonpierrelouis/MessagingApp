package com.messaging.services;

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

import com.messaging.models.Message;
import com.messaging.models.MessageDTO;
import com.messaging.models.User;
import com.messaging.repositories.MessageRepository;

@SpringBootTest
public class MessageServiceTest {
	
	@Mock
	private MessageRepository messageRepository;
	
	@InjectMocks
	private MessageService messageService;
	
	@Test
	public void storeMessages() {
		Message message = new Message(1, 2, "How have you been?");
		Message expected = new Message(1, 2, "How have you been?");
		
		when(messageRepository.save(message)).thenReturn(expected);
		
		Message actual = messageService.storeMessage(message.getSenderId(), message.getRecipientId(), message.getMessageBody());
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void retrieveMessage() {
		int user1 = 1;
		int user2 = 2;
		List<Message> messages = new ArrayList<>(Arrays.asList(
				new Message(1, 2, "want to get lunch today"),
				new Message(2, 1, "to busy. maybe tommorrow"),
				new Message(1, 2, "cant i have an meeting"),
				new Message(1, 2, "what about next week?") ));
		List<Message> expected = new ArrayList<>(Arrays.asList(
				new Message(1, 2, "want to get lunch today"),
				new Message(2, 1, "to busy. maybe tommorrow"),
				new Message(1, 2, "cant i have an meeting"),
				new Message(1, 2, "what about next week?")));
		
		when(messageRepository.findBySenderAndRecipient(user1, user2)).thenReturn(Optional.ofNullable(messages));
		
		Optional<List<Message>> actual = messageService.retrieveMessages(user1, user2);
		
		assertEquals(expected, actual.get());
	}
	
	@Test
	public void rawMessageToDTOTest() {
		User user1 = new User(1, "Alice", "password123");
		User user2 = new User(2, "Charles", "password456");
		List<Message> rawMessages = new ArrayList<>(Arrays.asList(
				new Message(1, 2, "want to get lunch today"),
				new Message(2, 1, "to busy. maybe tommorrow"),
				new Message(1, 2, "cant i have an meeting"),
				new Message(1, 2, "what about next week?") ));
		List<MessageDTO> expected = new ArrayList<>(Arrays.asList(
				new MessageDTO("Alice", "want to get lunch today"),
				new MessageDTO("Charles", "to busy. maybe tommorrow"),
				new MessageDTO("Alice", "cant i have an meeting"),
				new MessageDTO("Alice", "what about next week?") ));
		
		List<MessageDTO> actual = messageService.rawMessageToDTO(rawMessages, user1, user2);
		
		assertEquals(expected, actual);
		
	}

}
