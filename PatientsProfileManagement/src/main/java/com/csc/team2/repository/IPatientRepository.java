package com.csc.team2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.csc.team2.model.Patient;

@Repository
public interface IPatientRepository extends CrudRepository<Patient, Integer> {
		Patient findByname(String name);
}
