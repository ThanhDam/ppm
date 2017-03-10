package com.csc.team2.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.team2.model.TreatmentDt;
import com.csc.team2.repository.TreatmentDtRepository;


@Service("treatmentDtService")
@Transactional

public class TreatmentDtServiceIplm implements TreatmentDtService {
	
	@Autowired
    private TreatmentDtRepository treatmentdtRepository;

	@Override
	public TreatmentDt findById(int id) {
		return treatmentdtRepository.findOne(id);
	}

	@Override
	public TreatmentDt findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveTreatmentDt(TreatmentDt treatmentdt) {
		treatmentdtRepository.save(treatmentdt);
		
	}

	@Override
	public void updateTreatmentDt(TreatmentDt treatmentdt) {
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
	public List<TreatmentDt> findAllTreatmentDt() {
		return (List<TreatmentDt>) treatmentdtRepository.findAll();
	}

	@Override
	public boolean isTreatmentDtExist(TreatmentDt treatmentdt) {
		return findById(treatmentdt.getId()) != null;
	}
	
	
	

}
