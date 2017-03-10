package com.csc.team2.service;

import java.util.List;

import com.csc.team2.model.TypeMedicine;

public interface ITypeMedicineService {
	TypeMedicine findById(int id);
	 
	TypeMedicine findByName(String name);
 
    void saveTypeMedicine(TypeMedicine typeMedicine);
 
    void updateTypeMedicine(TypeMedicine typeMedicine);
 
    void deleteTypeMedicineById(int id);
 
    void deleteAllTypeMedicines();
 
    List<TypeMedicine> findAllTypeMedicines();
 
    boolean isTypeMedicineExist(TypeMedicine typeMedicine);
}
