package com.csc.team2.service;

import java.util.List;

import com.csc.team2.model.TypeOfMedicine;

public interface ITypeMedicineService {
	TypeOfMedicine findById(int id);
	 
	TypeOfMedicine findByName(String name);
 
    void saveTypeMedicine(TypeOfMedicine typeMedicine);
 
    void updateTypeMedicine(TypeOfMedicine typeMedicine);
 
    void deleteTypeMedicineById(int id);
 
    void deleteAllTypeMedicines();
 
    List<TypeOfMedicine> findAllTypeMedicines();
 
    boolean isTypeMedicineExist(TypeOfMedicine typeMedicine);
}
