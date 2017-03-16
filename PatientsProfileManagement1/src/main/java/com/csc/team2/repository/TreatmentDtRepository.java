package com.csc.team2.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.csc.team2.model.TreatmentDetail;

@Repository
public interface TreatmentDtRepository extends CrudRepository<TreatmentDetail, Integer>{

	//User findByName(String name);
	
}
