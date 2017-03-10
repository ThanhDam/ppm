package com.csc.team2.service;

import com.csc.team2.model.User;

public interface UserService {
	//public User findUserByEmail(String email);
	public User findUserByUsername(String username);
	public void saveAdmin(User user);
	public void saveDoctor(User user);
	public void saveNurse(User user);
}
