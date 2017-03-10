package com.csc.team2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="treatment_detail")
public class TreatmentDt implements Serializable{
	

private static final long serialVersionUD =1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="id")
	private Integer id;
	
	@Column(name = "idTreatment")
	private Integer idTreatment;
	
	@Column(name = "idMedicine")
	private Integer idMedicine;
	
	@Column(name = "diseases")
	private String diseases;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdTreatment() {
		return idTreatment;
	}

	public void setIdTreatment(Integer idTreatment) {
		this.idTreatment = idTreatment;
	}

	public Integer getIdMedicine() {
		return idMedicine;
	}

	public void setIdMedicine(Integer idMedicine) {
		this.idMedicine = idMedicine;
	}

	public String getDiseases() {
		return diseases;
	}

	public void setDiseases(String diseases) {
		this.diseases = diseases;
	}
	
	
	
	
	
}