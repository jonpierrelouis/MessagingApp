package com.messaging.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.messaging.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
	
	@Query(value = "SELECT * FROM messages WHERE sender_id = ?1 OR sender_id = ?2)", nativeQuery = true)
	public Optional<List<Message>> findBySenderAndRecipient(int person1, int person2);

}
