package com.csc.team2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.team2.model.History;
import com.csc.team2.repository.IHistoryRepository;

@Service
public class HistoryServiceImpl implements IHistoryService {
	
	@Autowired
	IHistoryRepository historyRepos;

	@Override
	public History findById(int id) {
		return historyRepos.findOne(id);
	}



	@Override
	public List<History> findBytreatmentId(int id) {
		return historyRepos.findByTreatmentId(id);
	}

	@Override
	public void saveHistory(History history) {
		historyRepos.save(history);
		
	}

	@Override
	public void updateHistory(History history) {
		saveHistory(history);
		
	}

	@Override
	public void deleteHistoryById(int id) {
		historyRepos.delete(id);
		
	}

	@Override
	public void deleteAllHistory() {
		historyRepos.deleteAll();
		
	}

	@Override
	public List<History> findAllHistorys() {
		return historyRepos.findAll();
	}

	@Override
	public boolean isMedicineExist(History history) {
		// TODO Auto-generated method stub
		return false;
	}


}
