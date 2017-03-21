package com.csc.team2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csc.team2.model.Roles;
import com.csc.team2.model.Treatment;
import com.csc.team2.model.User;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Integer> {
	 //User findByEmail(String email);
	User findByusername(String username);
	User findByid(int id);
	@Query("SELECT u FROM User u join u.rolesList r WHERE r.id in (:rolesList)")
	List<User> findByRoleId(@Param("rolesList") int role);
}
