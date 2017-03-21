package com.csc.team2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.csc.team2.model.History;


public interface IHistoryService {
	History findById(int id);
	 
	List<History> findBytreatmentId(int id);
 
    void saveHistory(History history);
 
    void updateHistory(History history);
 
    void deleteHistoryById(int id);
 
    void deleteAllHistory();
 
    List<History> findAllHistorys();
 
    boolean isMedicineExist(History history);
}
