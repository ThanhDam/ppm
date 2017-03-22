package com.csc.team2.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csc.team2.model.Medicine;
import com.csc.team2.model.TreatmentDetail;

@Repository
public interface ITreatmentDtRepository extends CrudRepository<TreatmentDetail, Integer>{

	//User findByName(String name);
	@Query(value="SELECT m.id,m.name,m.mfg,m.producer,m.dosage FROM Medicine m where not exists(select a.medicine_id from Allergic a where m.id=a.medicine_id and a.patient_id=:id)", nativeQuery = true)
    public List<Object[]> findNotAllergic(@Param("id") int id);
}
