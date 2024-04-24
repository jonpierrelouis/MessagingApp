package com.messaging.controller;

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
import com.messaging.services.MessageService;

@RestController
@RequestMapping("/messages")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class MessageController {

	private final MessageService messageService;

	@Autowired 
	public MessageController(MessageService messageService) {
		super();
		this.messageService = messageService;
	}
	
	@PostMapping(value = "/sendMessage")
	public Message storeMessage(HttpSession session, HttpServletRequest req) {
		
		Object sender = session.getAttribute("userId");
		int recipient = Integer.parseInt(req.getParameter("recipient"));
		String message = req.getParameter("message");
		
		return messageService.storeMessage( (Integer) sender, recipient, message);
	}
	
	public Message storeMessage(@RequestBody Message message) {

		return messageService.storeMessage(message.getSenderId(), message.getRecipientId(), message.getMessageBody());
	}
	
	@GetMapping(value = "/getMessages")
	public Optional<List<Message>> retrieveMessages(HttpSession session, HttpServletRequest req){
		
		Object sender = session.getAttribute("userId");
		int recipient = Integer.parseInt(req.getParameter("recipient"));
		
		return messageService.retrieveMessages((Integer) sender, recipient);
	}
	
	@GetMapping(value = "/getMessages/{userId}/{friendId}")
	public Optional<List<Message>> retrieveMessages(@PathVariable int userId, @PathVariable int friendId){
		
		return messageService.retrieveMessages(userId, friendId);
	}
	
}
