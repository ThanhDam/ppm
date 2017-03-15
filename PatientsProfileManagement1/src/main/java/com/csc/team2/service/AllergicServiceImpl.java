package com.csc.team2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.team2.model.Allergic;
import com.csc.team2.repository.IAllergicRepository;

@Service
public class AllergicServiceImpl implements IAllergicService {
	
	@Autowired
	IAllergicRepository allergicRepos;

	@Override
	public Allergic findById(int id) {
		return allergicRepos.findOne(id);
	}

	@Override
	public void saveAllergic(Allergic allergic) {
		allergicRepos.save(allergic);
		
	}

	@Override
	public void updateAllergic(Allergic allergic) {
		saveAllergic(allergic);
		
	}

	@Override
	public void deleteAllergicById(int id) {
		allergicRepos.delete(id);
		
	}

	@Override
	public void deleteAllAllergics() {
		allergicRepos.deleteAll();
		
	}

	@Override
	public List<Allergic> findAllAllergics() {
		return allergicRepos.findAll();
	}

	@Override
	public boolean isAllergicExist(Allergic allergic) {
		// TODO Auto-generated method stub
		return false;
	}

}
