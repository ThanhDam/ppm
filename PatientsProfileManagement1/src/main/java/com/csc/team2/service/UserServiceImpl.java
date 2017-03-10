package com.csc.team2.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.csc.team2.model.Role;
import com.csc.team2.model.User;
import com.csc.team2.repository.RoleRepository;
import com.csc.team2.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Override
	public User findUserByUsername(String username){
		return userRepository.findByUsername(username);
	}
	
	@Override
	public void saveAdmin(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
	    Role userRole = roleRepository.findByRoles("admin");
	    user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	    userRepository.save(user);
	}
	
	@Override
	public void saveDoctor(User user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRoles("doctor");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
	
	@Override
	public void saveNurse(User user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRoles("nurse");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
	
}
