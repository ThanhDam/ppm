package com.csc.team2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.csc.team2.model.Patient;

@RepositoryRestResource(collectionResourceRel = "patient", path = "patient")
public interface IPatientRepository extends CrudRepository<Patient, Integer> {
		Patient findByname(String name);
}
