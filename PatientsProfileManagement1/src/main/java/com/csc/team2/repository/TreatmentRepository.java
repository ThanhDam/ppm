package com.csc.team2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.csc.team2.model.Treatment;;

@Repository
public interface TreatmentRepository extends CrudRepository<Treatment, Integer>{

	//User findByName(String name);
}
