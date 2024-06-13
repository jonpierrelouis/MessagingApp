package com.messaging.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.messaging.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public Optional<User> findByUsernameAndPassword(String username, String password);

	public Optional<User> findByUsername(String username);

}
