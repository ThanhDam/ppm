package com.csc.team2.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.team2.model.Treatment;
import com.csc.team2.repository.TreatmentRepository;;

@Service("treatmentService")
@Transactional
public class TreatmentServiceIplm implements TreatmentService{

	@Autowired
    private TreatmentRepository treatmentRepository;

	@Override
	public Treatment findById(int id) {
		return treatmentRepository.findOne(id);
	}

	@Override
	public Treatment findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveTreatment(Treatment treatment) {
		treatmentRepository.save(treatment);
		
	}

	@Override
	public void updateTreatment(Treatment treatment) {
		saveTreatment(treatment);
		
	}

	@Override
	public void deleteTreatmentById(int id) {
		treatmentRepository.delete(id);
	}

	@Override
	public void deleteAllTreatment() {
		treatmentRepository.deleteAll();
		
	}

	@Override
	public List<Treatment> findAllTreatment() {
		return (List<Treatment>) treatmentRepository.findAll();
	}

	@Override
	public boolean isTreatmentExist(Treatment treatment) {
		return findById(treatment.getId()) != null;
	}
}