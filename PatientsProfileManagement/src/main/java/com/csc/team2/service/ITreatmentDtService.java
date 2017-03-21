package com.csc.team2.service;

import java.util.List;

import com.csc.team2.model.Medicine;
import com.csc.team2.model.TreatmentDetail;

public interface ITreatmentDtService  {
	
	TreatmentDetail findById(int id);
	 
    TreatmentDetail findByName(String name);
 
    void saveTreatmentDt(TreatmentDetail treatmentdt);
 
    void updateTreatmentDt(TreatmentDetail treatmentdt);
 
    void deleteTreatmentDtById(int id);
 
    void deleteAllTreatmentDt();
 
    List<TreatmentDetail> findAllTreatmentDt();
    
    List<Object[]> findNotAllergic(int id);
 
    boolean isTreatmentDtExist(TreatmentDetail treatmentdt);

}
