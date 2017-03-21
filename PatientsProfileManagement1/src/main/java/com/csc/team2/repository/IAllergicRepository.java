package com.csc.team2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.csc.team2.model.Allergic;

@Repository
public interface IAllergicRepository extends CrudRepository<Allergic, Integer> {
	
}
