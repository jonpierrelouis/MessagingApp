package com.messaging.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messaging.models.Message;
import com.messaging.models.MessageDTO;
import com.messaging.models.User;
import com.messaging.repositories.MessageRepository;
import com.messaging.repositories.UserRepository;

@Service
public class MessageService {
	
	private MessageRepository messageRepository;
	private UserRepository userRepository;

	@Autowired
	public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
		super();
		this.messageRepository = messageRepository;
		this.userRepository = userRepository;
	}
	
	
	/**
	 * Store message into the database
	 * @param sender
	 * @param recipient
	 * @param message
	 * @return message
	 */
	public Message storeMessage(int sender, int recipient, String message) {
		
		Message newMessage = new Message(sender, recipient, message);
		
		return messageRepository.save(newMessage);
	}

	/**
	 * Retrieve messages betweeen 2 people from the database
	 * @param person1
	 * @param person2
	 * @return a list of messages
	 */
	public Optional<List<Message>> retrieveMessages(int person1, int person2){
		return messageRepository.findBySenderAndRecipient(person1, person2);
	}
	
}
