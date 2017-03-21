package com.csc.team2.service;

import java.util.List;

import com.csc.team2.model.Medicine;
import com.csc.team2.model.Roles;
import com.csc.team2.model.Treatment;
import com.csc.team2.model.User;

public interface IUserService {
	public User findUserById(int id);
	public User findUserByUsername(String username);
	
	public void saveUser(User user);
	public void saveAdmin(User user);
	public void saveDoctor(User user);
	public void saveNurse(User user);
	
	public void updateAdmin(User user);
	public void updateDoctor(User user);
	public void updateNurse(User user);
	
	public void deleteUserById(int id);

	 
	public void deleteAllDoctor();
	public void deleteAllNurse();
	
	public List<User> findUserByRoleId(int role);
	List<User> findAllUsers();
	//List<User> findAllAdmin();
	//List<User> findAllAdmin(User user);
}
