package com.csc.team2.service;

import java.util.List;

import com.csc.team2.model.Patient;

public interface IPatientService {
	Patient findById(int id);
	 
	Patient findByName(String name);
 
    void savePatient(Patient patient);
 
    void updatePatient(Patient patient);
 
    void deletePatientById(int id);
 
    void deleteAllPatients();
 
    List<Patient> findAllPatients();
 
    boolean isPatientExist(Patient patient);
}
