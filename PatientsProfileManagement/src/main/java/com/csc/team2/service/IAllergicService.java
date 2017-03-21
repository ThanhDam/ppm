package com.csc.team2.service;

import java.util.List;

import com.csc.team2.model.Allergic;

public interface IAllergicService {
	Allergic findById(int id);
 
    void saveAllergic(Allergic allergic);
    
    void saveAllergic(List<Allergic> allergicList);
 
    void updateAllergic(Allergic allergic);
 
    void deleteAllergicById(int id);
 
    void deleteAllAllergics();
 
    List<Allergic> findAllAllergics();
 
    boolean isAllergicExist(Allergic allergic);
}
