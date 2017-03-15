package com.csc.team2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.csc.team2.model.Patient;

@RepositoryRestResource(collectionResourceRel = "patient", path = "patient")
public interface IPatientRepository extends JpaRepository<Patient, Integer> {
		Patient findByname(String name);
}
