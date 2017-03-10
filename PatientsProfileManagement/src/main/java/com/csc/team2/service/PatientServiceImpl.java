package com.csc.team2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.team2.model.Patient;
import com.csc.team2.repository.IPatientRepository;


@Service("patientService")
public class PatientServiceImpl implements IPatientService {

	@Autowired
	IPatientRepository patientRepos;
	
	@Override
	public Patient findById(int id) {
		return patientRepos.findOne(id);
	}

	@Override
	public Patient findByName(String name) {
		return patientRepos.findByname(name);
	}

	@Override
	public void savePatient(Patient patient) {
		patientRepos.save(patient);
		
	}

	@Override
	public void updatePatient(Patient patient) {
		savePatient(patient);
		
	}

	@Override
	public void deletePatientById(int id) {
		patientRepos.delete(id);
		
	}

	@Override
	public void deleteAllPatients() {
		patientRepos.deleteAll();
		
	}

	@Override
	public List<Patient> findAllPatients() {
		return (List<Patient>) patientRepos.findAll();
	}

	@Override
	public boolean isPatientExist(Patient patient) {
		// TODO Auto-generated method stub
		return false;
	}

}
