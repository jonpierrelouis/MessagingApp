package com.messaging.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.messaging.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
	
	@Query(value = "SELECT * FROM messages WHERE sender_id IN (?1,?2) AND recipient_id IN (?1,?2) ORDER BY message_id;", nativeQuery = true)
	public Optional<List<Message>> findBySenderAndRecipient(int person1, int person2);
	
	@Query(value = "SELECT message_id, message_body, u1.user_name AS sender, u2.user_name AS recipient "
			+ "FROM messages "
			+ "INNER JOIN users u1 ON u1.user_id = messages.sender_id "
			+ "INNER JOIN users u2 ON u2.user_id = messages.recipient_id "
			+ "WHERE sender_id IN (?1, ?2) AND recipient_id IN (?1, ?2) ORDER BY message_id;", nativeQuery=true)
	public Optional<List<Message>> findFormatedMessages(int sender, int receiver);

}
