package com.csc.team2.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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
	@Override
	public void updateAdmin(User user){
		
	}
	@Override
	public void updateDoctor(User user){
		
	}
	@Override
	public void updateNurse(User user){
		
	}
	@Override
	public void deleteAdminById(int id){
		
	}
	@Override
	public void deleteDoctorById(int id){
		
	}
	@Override
	public void deleteNurseById(int id){
		
	}
	@Override
	public void deleteAllDoctor(){
		
	}
	@Override
	public void deleteAllNurse(){
		
	}
	
	@Override
	public List<User> findAllUsers(){
		return userRepository.findAll();
		
	}
	
	
	/*@Override
	public List<User> findAllAdmin(User user){
		Role userRole = roleRepository.findByRoles("doctor");
		
	}*/
}
