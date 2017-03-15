package com.csc.team2.service;

import java.util.List;

import com.csc.team2.model.Medicine;

public interface IMedicineService {
	Medicine findById(int id);
	 
	Medicine findByName(String name);
 
    void saveMedicine(Medicine medicine);
 
    void updateMedicine(Medicine medicine);
 
    void deleteMedicineById(int id);
 
    void deleteAllMedicines();
 
    List<Medicine> findAllMedicines();
    
    List<Medicine> findByType(int typeid);
 
    boolean isMedicineExist(Medicine medicine);
}
