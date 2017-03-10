package com.csc.team2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.team2.model.Medicine;
import com.csc.team2.repository.IMedicineRepository;

@Service("medicineServicce")
public class MedicineServiceImpl implements IMedicineService {
	
	@Autowired
	IMedicineRepository medicineRepos;

	@Override
	public Medicine findById(int id) {
		return medicineRepos.findOne(id);
	}

	@Override
	public Medicine findByName(String name) {
		return medicineRepos.findByName(name);
	}

	@Override
	public void saveMedicine(Medicine medicine) {
		medicineRepos.save(medicine);
		
	}

	@Override
	public void updateMedicine(Medicine medicine) {
		saveMedicine(medicine);
		
	}

	@Override
	public void deleteMedicineById(int id) {
		medicineRepos.delete(id);
		
	}

	@Override
	public void deleteAllMedicines() {
		medicineRepos.deleteAll();
		
	}

	@Override
	public List<Medicine> findAllMedicines() {
		return medicineRepos.findAll();
	}

	@Override
	public boolean isMedicineExist(Medicine medicine) {
		// TODO Auto-generated method stub
		return false;
	}

}
