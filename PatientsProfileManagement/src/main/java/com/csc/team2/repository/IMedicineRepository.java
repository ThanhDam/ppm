package com.csc.team2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.csc.team2.model.Medicine;

@Repository
public interface IMedicineRepository extends CrudRepository<Medicine, Integer> {
		Medicine findByName(String name);
		List<Medicine> findByTypeId(int typeid);
}
