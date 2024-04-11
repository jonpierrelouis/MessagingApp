package com.messaging.keys;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendKey implements Serializable{
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "friend_id")
	private int friendId;

}
