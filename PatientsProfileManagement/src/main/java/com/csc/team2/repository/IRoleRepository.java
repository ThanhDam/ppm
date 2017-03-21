package com.csc.team2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.csc.team2.model.Roles;

@Repository("roleRepository")
public interface IRoleRepository extends CrudRepository<Roles, Integer>{
	Roles findByroles(String roles);

}
