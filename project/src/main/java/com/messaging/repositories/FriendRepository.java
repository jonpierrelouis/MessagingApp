package com.messaging.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.messaging.keys.FriendKey;
import com.messaging.models.Friend;

@Repository
public interface FriendRepository extends JpaRepository<Friend, FriendKey> {

	@Query(value = "SELECT users.user_name FROM friends "
			+ "INNER JOIN users ON friends.friend_id = users.user_id "
			+ "WHERE user_id_fk = ?1;", nativeQuery=true)
	Optional<List<String>> findByUserId(int userId);

}
