package com.csc.team2.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.team2.model.TreatmentDetail;
import com.csc.team2.repository.TreatmentDtRepository;


@Service("treatmentDtService")
@Transactional

public class TreatmentDtServiceIplm implements TreatmentDtService {
	
	@Autowired
    private TreatmentDtRepository treatmentdtRepository;

	@Override
	public TreatmentDetail findById(int id) {
		return treatmentdtRepository.findOne(id);
	}

	@Override
	public TreatmentDetail findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveTreatmentDt(TreatmentDetail treatmentdt) {
		treatmentdtRepository.save(treatmentdt);
		
	}

	@Override
	public void updateTreatmentDt(TreatmentDetail treatmentdt) {
		saveTreatmentDt(treatmentdt);
		
	}

	@Override
	public void deleteTreatmentDtById(int id) {
		treatmentdtRepository.delete(id);
		
	}

	@Override
	public void deleteAllTreatmentDt() {
		treatmentdtRepository.deleteAll();
		
	}

	@Override
	public List<TreatmentDetail> findAllTreatmentDt() {
		return (List<TreatmentDetail>) treatmentdtRepository.findAll();
	}

	@Override
	public boolean isTreatmentDtExist(TreatmentDetail treatmentdt) {
		return findById(treatmentdt.getId()) != null;
	}

	

	
	
	
	

}
