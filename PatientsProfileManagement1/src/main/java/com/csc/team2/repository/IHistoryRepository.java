package com.csc.team2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csc.team2.model.History;

@Repository
public interface IHistoryRepository extends JpaRepository<History, Integer> {
	List<History> findByTreatmentId(int id);

}
