package com.csc.team2.service;

import java.util.List;

import com.csc.team2.model.Treatment;

public interface TreatmentService  {
	
	Treatment findById(int id);
	 
    Treatment findByName(String name);
 
    void saveTreatment(Treatment treatment);
 
    void updateTreatment(Treatment treatment);
 
    void deleteTreatmentById(int id);
 
    void deleteAllTreatment();
 
    List<Treatment> findAllTreatment();
 
    boolean isTreatmentExist(Treatment treatment);

}
