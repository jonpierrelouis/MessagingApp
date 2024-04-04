package com.messaging.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.messaging.keys.FriendKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@IdClass(FriendKey.class)
@Table(name="friends")
public class Friend {
	
	@Id
	@Column(name="user_id_fk")
	private int userId;
	
	@Column(name="friend_id")
	private int friendId;
	
}
