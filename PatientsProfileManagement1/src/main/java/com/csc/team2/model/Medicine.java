package com.csc.team2.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="medicine")
public class Medicine implements Serializable {

	
private static final long serialVersionUD =1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer medicineId;
	
	@Column(name="idtype")
	private Integer typeId;
	
	@Column(name="name")
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name="mfg")
	private Date mfg;
	
	@Column(name="producer")
	private String producer;
	
	@Column(name="dosage")
	private String dosage;

	public Integer getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getMfg() {
		return mfg;
	}

	public void setMfg(Date mfg) {
		this.mfg = mfg;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public Medicine(Integer medicineId, Integer typeId, String name, Date mfg, String producer, String dosage) {
		this.medicineId = medicineId;
		this.typeId = typeId;
		this.name = name;
		this.mfg = mfg;
		this.producer = producer;
		this.dosage = dosage;
	}
	
	public Medicine(Medicine medicine){
		this.medicineId = medicine.medicineId;
		this.typeId = medicine.typeId;
		this.name = medicine.name;
		this.mfg = medicine.mfg;
		this.producer = medicine.producer;
		this.dosage = medicine.dosage;
	}
	public Medicine(){
		this.medicineId = 0;
		this.typeId = 0;
		this.name = "";
		this.mfg = null;
		this.producer = "";
		this.dosage = "";
	}
}
