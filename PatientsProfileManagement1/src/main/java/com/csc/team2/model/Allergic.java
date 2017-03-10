package com.csc.team2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="allergic_detail")
public class Allergic implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="idPatient")
	@NotNull
	private Integer idPatient;
	
	@Column(name="idMedicine")
	@NotNull
	private Integer idMedicine;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(Integer idPatient) {
		this.idPatient = idPatient;
	}

	public Integer getIdMedicine() {
		return idMedicine;
	}

	public void setIdMedicine(Integer idMedicine) {
		this.idMedicine = idMedicine;
	}

	public Allergic(Integer id, Integer idPatient, Integer idMedicine) {
		this.id = id;
		this.idPatient = idPatient;
		this.idMedicine = idMedicine;
	}

	public Allergic(Allergic a) {
		this.id = a.id;
		this.idPatient = a.idPatient;
		this.idMedicine = a.idMedicine;
	}
	
	
	
}
