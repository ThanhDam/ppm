package com.csc.team2.service;

import java.util.List;

import com.csc.team2.model.Medicine;
import com.csc.team2.model.Treatment;
import com.csc.team2.model.User;

public interface UserService {
	//public User findUserByEmail(String email);
	public User findUserByUsername(String username);
	public void saveAdmin(User user);
	public void saveDoctor(User user);
	public void saveNurse(User user);
	
	public void updateAdmin(User user);
	public void updateDoctor(User user);
	public void updateNurse(User user);
	
	public void deleteAdminById(int id);
	public void deleteDoctorById(int id);
	public void deleteNurseById(int id);
	 
	public void deleteAllDoctor();
	public void deleteAllNurse();
	
	List<User> findAllUsers();
	//List<User> findAllAdmin();
	//List<User> findAllAdmin(User user);
}
