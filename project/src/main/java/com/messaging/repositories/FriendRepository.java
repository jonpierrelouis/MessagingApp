package com.messaging.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.messaging.keys.FriendKey;
import com.messaging.models.Friend;
import com.messaging.models.User;

@Repository
public interface FriendRepository extends JpaRepository<Friend, FriendKey> {

	Optional<List<Friend>> findByUserId(int userId);
	
//	@Query(value = "SELECT users FROM friends "
//	+ " INNER JOIN users ON friends.friend_id = users.user_id "
//	+ " WHERE friends.user_id = ?1;", nativeQuery=true)
//	Optional<List<User>> findNamesByUserId(int userId); 

}
