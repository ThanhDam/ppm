package com.csc.team2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.team2.model.Medicine;
import com.csc.team2.model.Roles;
import com.csc.team2.model.TypeOfMedicine;
import com.csc.team2.repository.IRoleRepository;

@Service("rolesService")
public class RolesServiceImpl implements IRolesService {
	@Autowired
	IRoleRepository rolesRepos;

	@Override
	public Roles findById(int id) {
		return rolesRepos.findOne(id);
	}

	@Override
	public Roles findByRoles(String role) {
		return rolesRepos.findByroles(role);
	}
	@Override
	public List<Roles> findAllRoles() {
		return (List<Roles>) rolesRepos.findAll();
	}
}
