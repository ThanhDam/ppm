package com.csc.team2.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.csc.team2.model.TreatmentDt;

@Repository
public interface TreatmentDtRepository extends CrudRepository<TreatmentDt, Integer>{

	//User findByName(String name);
}
