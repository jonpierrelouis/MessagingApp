package com.messaging.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="messages")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_id")
	int messageId;

	@Column(name = "sender_id")
	int senderId;
	
	@Column(name = "recipient_id")
	int recipientId;
	
	@Column(name = "message_body")
	String messageBody;

	public Message(int senderId, int recipientId, String messageBody) {
		super();
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.messageBody = messageBody;
	}
	
}
