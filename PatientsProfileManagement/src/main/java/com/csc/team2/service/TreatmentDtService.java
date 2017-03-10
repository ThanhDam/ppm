package com.csc.team2.service;

import java.util.List;

import com.csc.team2.model.TreatmentDt;

public interface TreatmentDtService  {
	
	TreatmentDt findById(int id);
	 
    TreatmentDt findByName(String name);
 
    void saveTreatmentDt(TreatmentDt treatmentdt);
 
    void updateTreatmentDt(TreatmentDt treatmentdt);
 
    void deleteTreatmentDtById(int id);
 
    void deleteAllTreatmentDt();
 
    List<TreatmentDt> findAllTreatmentDt();
 
    boolean isTreatmentDtExist(TreatmentDt treatmentdt);

}
