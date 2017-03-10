package com.csc.team2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.team2.repository.ITypeMedicneRepository;
import com.csc.team2.model.TypeMedicine;

@Service("typeMedicineService")
public class TypeMedicineServiceImpl implements ITypeMedicineService {
	
	@Autowired
	ITypeMedicneRepository typeMedicineRepos;

	@Override
	public TypeMedicine findById(int id) {
		return typeMedicineRepos.findOne(id);
	}

	@Override
	public TypeMedicine findByName(String name) {
		return typeMedicineRepos.findBytypeName(name);
	}

	@Override
	public void saveTypeMedicine(TypeMedicine typeMedicine) {
		typeMedicineRepos.save(typeMedicine);
		
	}

	@Override
	public void updateTypeMedicine(TypeMedicine typeMedicine) {
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
	public List<TypeMedicine> findAllTypeMedicines() {
		return typeMedicineRepos.findAll();
	}

	@Override
	public boolean isTypeMedicineExist(TypeMedicine typeMedicine) {
		// TODO Auto-generated method stub
		return false;
	}

}
