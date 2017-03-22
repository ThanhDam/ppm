package com.csc.team2.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.csc.team2.model.Roles;
import com.csc.team2.model.User;
import com.csc.team2.repository.IRoleRepository;
import com.csc.team2.repository.IUserRepository;

@Service("userService")
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserRepository userRepository;
	@Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Override
	public User findUserById(int id){
		return userRepository.findByid(id);
	}
    
    @Override
	public User findUserByUsername(String username){
		return userRepository.findByusername(username);
	}
	
    @Override
   	public List<User> findUserByRoleId(int roleid){		
   		return userRepository.findByRoleId(roleid);
   	}
    
/*    @Override
	public void saveUser(User user) {
	    userRepository.save(user);
	}*/
    
	@Override
	public void saveAdmin(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
	    Roles userRole = roleRepository.findByroles("admin");
	    user.setRolesList(new HashSet<Roles>(Arrays.asList(userRole)));
	    userRepository.save(user);
	}
	
	@Override
	public void saveDoctor(User user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Roles userRole = roleRepository.findByroles("doctor");
		user.setRolesList(new HashSet<Roles>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
	
	@Override
	public void saveNurse(User user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Roles userRole = roleRepository.findByroles("nurse");
		user.setRolesList(new HashSet<Roles>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
	@Override
	public void updateAdmin(User user){
		saveAdmin(user);
	}
	@Override
	public void updateDoctor(User user){
		saveDoctor(user);
	}
	@Override
	public void updateNurse(User user){
		saveNurse(user);
	}
	@Override
	public void deleteUserById(int id){
		userRepository.delete(id);
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

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
		
	}
	
	
	/*@Override
	public List<User> findAllAdmin(User user){
		Role userRole = roleRepository.findByRoles("doctor");
		
	}*/
}
