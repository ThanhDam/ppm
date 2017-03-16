package com.csc.team2.service;

import java.util.List;

import com.csc.team2.model.TreatmentDetail;

public interface TreatmentDtService  {
	
	TreatmentDetail findById(int id);
	 
    TreatmentDetail findByName(String name);
 
    void saveTreatmentDt(TreatmentDetail treatmentdt);
 
    void updateTreatmentDt(TreatmentDetail treatmentdt);
 
    void deleteTreatmentDtById(int id);
 
    void deleteAllTreatmentDt();
 
    List<TreatmentDetail> findAllTreatmentDt();
 
    boolean isTreatmentDtExist(TreatmentDetail treatmentdt);

    
}
