package com.csc.team2.service;

import java.util.List;

import com.csc.team2.model.Roles;

public interface IRolesService {
	public Roles findById(int id);
	public Roles findByRoles(String roles);
	List<Roles> findAllRoles();
}
