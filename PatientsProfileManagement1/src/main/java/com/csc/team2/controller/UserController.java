package com.csc.team2.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.csc.team2.model.Medicine;
import com.csc.team2.model.Roles;
import com.csc.team2.model.User;
import com.csc.team2.service.UserServiceImpl;

@RestController
public class UserController {
	@Autowired
	UserServiceImpl userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
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

	//--------------------Select All User by Role-----------------------------------------
	
		@RequestMapping(value="/userbyrole/{id}", method = RequestMethod.GET)
		public ResponseEntity<List<User>> listUserByRole(@PathVariable("id") int id){
			List<User> users = userService.findUserByRoleId(id);
			if(users.isEmpty()){
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
	//--------------------Select One User-----------------------------------------
	
	@RequestMapping(value="/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") int id){
		logger.info("Fetching user with id {}", id);
		User user = userService.findUserById(id);
		if(user==null){
			 logger.error("Patient with id {} not found.", id);
			 return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	//------------------Update User-----------------------------------------------
	
		@RequestMapping(value="/user/{id}", method = RequestMethod.PUT)
		public ResponseEntity<?> updateUser(@PathVariable("id") int id,@RequestBody User user){
			logger.info("update user with id {}", id);
			
			User currentUser = userService.findUserById(id);
			if(currentUser==null){
				logger.info("unable to update user with id {}, not found", id);
			}
			
			currentUser.setName(user.getName());
			currentUser.setUsername(user.getUsername());
			currentUser.setSpecialist(user.getSpecialist());
			currentUser.setSex(user.getSex());
			currentUser.setAddress(user.getAddress());
			//currentUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			currentUser.setRolesList(user.getRolesList());
			currentUser.setActive(user.getActive());
			userService.saveUser(currentUser);
			return new ResponseEntity<User>(currentUser,HttpStatus.OK);
		}
//------------------Change Password User-----------------------------------------------
		
		@RequestMapping(value="/changePass", method = RequestMethod.PUT)
		public ResponseEntity<?> ChangePass(@RequestBody User user){
			Object principal = SecurityContextHolder.getContext().getAuthentication().getName();
			User currentUser = userService.findUserByUsername(principal.toString());
			if(currentUser==null){
				logger.info("unable to update user with id {}, not found", principal.toString());
			}				
				currentUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
				userService.saveUser(currentUser);
				return new ResponseEntity<User>(currentUser,HttpStatus.OK);
			}
		//------------------Delete a User-----------------------------------------
		@RequestMapping(value="/user/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteUser(@PathVariable("id") int id){
			logger.info("Fetch & delete user with id {}", id);
			User user = userService.findUserById(id);
			if(user==null){
				logger.info("unable to delete medicine with id {}, not found",id);
			}
			userService.deleteUserById(id);
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
		
		//------------------Delete All User---------------------------------------
		@RequestMapping(value="/user", method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteAllUser(){
			logger.info("Deleting All User");
			 
			userService.deleteAllDoctor();
	        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);

		}
		
	//------------------USER PROFILE------------------------------
		/*@RequestMapping(value="/userProfile", method = RequestMethod.GET)
		public ModelAndView userProfile(){
			ModelAndView modelAndView = new ModelAndView();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByUsername(auth.getName());
			modelAndView.addObject("userid", user.getId());
			modelAndView.addObject("userName", user.getName());
			modelAndView.addObject("userRole","ROLE " + auth.getAuthorities().toString());
			modelAndView.addObject("userAddress", user.getAddress());
			modelAndView.addObject("userSpecialist", user.getSpecialist());
			modelAndView.addObject("userSex", user.getSex());
			modelAndView.setViewName("/userProfile");
			return modelAndView;
		}*/
		
		@RequestMapping(value = "/userProfile", method = RequestMethod.GET, produces =MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public ResponseEntity<?> currentUserName(Object principal) {
			principal = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = userService.findUserByUsername(principal.toString());
			return new ResponseEntity<User>(user, HttpStatus.OK);

	    }
}