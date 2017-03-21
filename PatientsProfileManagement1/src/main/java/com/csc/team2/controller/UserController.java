package com.csc.team2.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.csc.team2.model.Medicine;
import com.csc.team2.model.User;
import com.csc.team2.service.UserServiceImpl;

@RestController
public class UserController {
	@Autowired
	UserServiceImpl userService;
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	//--------------------Select All Users-----------------------------------------
	
	@RequestMapping(value="/user", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listUser(){
		List<User> users = userService.findAllUsers();
		if(users.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	//------------------Update Admin-----------------------------------------------
	
		@RequestMapping(value="/user/{username}", method = RequestMethod.PUT)
		public ResponseEntity<?> updateUser(@PathVariable("username") String username,@RequestBody User user){
			logger.info("update user with username {}", username);
			
			User currentUser = userService.findUserByUsername(username);
			if(currentUser==null){
				logger.info("unable to update user with username {}, not found", username);
			}
			currentUser.setName(user.getName());
			currentUser.setPassword(user.getPassword());
			currentUser.setSpecialist(user.getSpecialist());
			currentUser.setSex(user.getSex());
			currentUser.setAddress(user.getAddress());
			userService.saveAdmin(currentUser);
			return new ResponseEntity<User>(currentUser,HttpStatus.OK);
		}
}
