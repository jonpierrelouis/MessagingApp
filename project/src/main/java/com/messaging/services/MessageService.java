package com.messaging.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.messaging.models.Message;
import com.messaging.repositories.MessageRepository;

@Service
public class MessageService {
	
	private MessageRepository messageRepository;

	public MessageService(MessageRepository messageRepository) {
		super();
		this.messageRepository = messageRepository;
	}
	
	
	//send(store) message
	public Message storeMessage(int sender, int recipient, String message) {
		
		Message newMessage = new Message(sender, recipient, message);
		
		return messageRepository.save(newMessage);
	}

	//retrieve messages
	public List<Message> retrieveMessages(int person1, int person2){
		
		return null;
	}
	
	
}
