package com.csc.team2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.team2.repository.ITypeMedicneRepository;
import com.csc.team2.model.TypeOfMedicine;

@Service("typeMedicineService")
public class TypeMedicineServiceImpl implements ITypeMedicineService {
	
	@Autowired
	ITypeMedicneRepository typeMedicineRepos;

	@Override
	public TypeOfMedicine findById(int id) {
		return typeMedicineRepos.findOne(id);
	}

	@Override
	public TypeOfMedicine findByName(String name) {
		return typeMedicineRepos.findBytypename(name);
	}

	@Override
	public void saveTypeMedicine(TypeOfMedicine typeMedicine) {
		typeMedicineRepos.save(typeMedicine);
		
	}

	@Override
	public void updateTypeMedicine(TypeOfMedicine typeMedicine) {
		saveTypeMedicine(typeMedicine);
		
	}

	@Override
	public void deleteTypeMedicineById(int id) {
		typeMedicineRepos.delete(id);
		
	}

	@Override
	public void deleteAllTypeMedicines() {
		typeMedicineRepos.deleteAll();
		
	}

	@Override
	public List<TypeOfMedicine> findAllTypeMedicines() {
		return (List<TypeOfMedicine>) typeMedicineRepos.findAll();
	}

	@Override
	public boolean isTypeMedicineExist(TypeOfMedicine typeMedicine) {
		// TODO Auto-generated method stub
		return false;
	}

}
