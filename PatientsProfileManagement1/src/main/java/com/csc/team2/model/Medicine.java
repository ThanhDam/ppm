package com.csc.team2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="medicine")
public class Medicine implements Serializable {

	
private static final long serialVersionUD =1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idmedicine")
	private Integer medicineId;
	
	@Column(name="idtype")
	private Integer typeId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="mfg")
	private String mfg;
	
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

	public String getMfg() {
		return mfg;
	}

	public void setMfg(String mfg) {
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

	public Medicine(Integer medicineId, Integer typeId, String name, String mfg, String producer, String dosage) {
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
		this.mfg = "";
		this.producer = "";
		this.dosage = "";
	}
}
