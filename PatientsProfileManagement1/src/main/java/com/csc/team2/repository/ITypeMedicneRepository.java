package com.csc.team2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csc.team2.model.TypeMedicine;

@Repository
public interface ITypeMedicneRepository extends JpaRepository<TypeMedicine, Integer> {
		TypeMedicine findBytypeName(String name);
}
