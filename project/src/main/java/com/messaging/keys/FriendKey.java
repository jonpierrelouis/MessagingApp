package com.messaging.keys;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendKey implements Serializable{
	
	private int userId;
	
	private int friendId;

}
