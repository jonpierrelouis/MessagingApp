package com.messaging.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFriendDTO {
	
	int userId;
	
	String newFriend;

}
