package com.csc.team2.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="patient")
public class Patient implements Serializable {
	
	private static final long serialVersionUD =1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer patienId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="sex")
	private String sex;
	
	@Column(name="dob")
	private Date dob;
	
	public Integer getPatienId() {
		return patienId;
	}

	public void setPatienId(Integer patienId) {
		this.patienId = patienId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Patient(Integer patienId, String name, String address, String sex, Date dob) {
		this.patienId = patienId;
		this.name = name;
		this.address = address;
		this.sex = sex;
		this.dob = dob;
	}
	
	public Patient(Patient patient){
		this.patienId = patient.patienId;
		this.name = patient.name;
		this.address = patient.address;
		this.sex = patient.sex;
		this.dob = patient.dob;
	}
	public Patient()
	{
		this.patienId = 0;
		this.name = "";
		this.address = "";
		this.sex = "";
		this.dob = null;
	}
}
