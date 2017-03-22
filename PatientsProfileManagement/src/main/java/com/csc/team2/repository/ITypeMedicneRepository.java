package com.csc.team2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.csc.team2.model.TypeOfMedicine;

@Repository
public interface ITypeMedicneRepository extends CrudRepository<TypeOfMedicine, Integer> {
		TypeOfMedicine findBytypename(String name);
}
