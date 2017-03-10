package com.csc.team2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csc.team2.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	 //User findByEmail(String email);
	User findByUsername(String username);
}
