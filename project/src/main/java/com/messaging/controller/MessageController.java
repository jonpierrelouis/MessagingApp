package com.messaging.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messaging.models.Message;
import com.messaging.models.MessageDTO;
import com.messaging.models.User;
import com.messaging.services.MessageService;
import com.messaging.services.UserService;

@RestController
@RequestMapping("/messages")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class MessageController {

	private final MessageService messageService;
	private final UserService userService;

	@Autowired 
	public MessageController(MessageService messageService, UserService userService) {
		super();
		this.messageService = messageService;
		this.userService = userService;
	}
	
	@PostMapping(value = "/sendMessage")
	public Message storeMessage(HttpSession session, HttpServletRequest req) {
		
		Object sender = session.getAttribute("userId");
		int recipient = Integer.parseInt(req.getParameter("recipient"));
		String message = req.getParameter("message");
		
		return messageService.storeMessage( (Integer) sender, recipient, message);
	}
	
	@PostMapping(value = "/sendMessageBody")
	public Message storeMessage(@RequestBody Message message) {

		return messageService.storeMessage(message.getSenderId(), message.getRecipientId(), message.getMessageBody());
	}
	
	@GetMapping(value = "/getMessages/{userId}/{friendId}")
	public Optional<List<Message>> retrieveMessages(@PathVariable int userId, @PathVariable int friendId){
		
		return messageService.retrieveMessages(userId, friendId);
	}
	
	@GetMapping(value = "/getMessagesDTO/{userId}/{friendId}")
	public List<MessageDTO> retrieveMessagesDTO(@PathVariable int userId, @PathVariable int friendId){
		
		 User user1 = userService.findByUserId(userId).get();
		 User user2 = userService.findByUserId(friendId).get();
		 
		
		 List<Message> messages = messageService.retrieveMessages(userId, friendId).get();
		 List<MessageDTO> messagesDTO = new ArrayList<>();
		 
		 for(Message message : messages) {
			 String senderName = "";
			 
			 if(message.getSenderId() == user1.getUserId()) {
				 senderName = user1.getUsername();
			 }else{
				 senderName = user2.getUsername();
			 }
			 
			 MessageDTO messageDTO = new MessageDTO(senderName, message.getMessageBody());
			 messagesDTO.add(messageDTO);
		 }
		 
		 return messagesDTO;
	}
	
}
