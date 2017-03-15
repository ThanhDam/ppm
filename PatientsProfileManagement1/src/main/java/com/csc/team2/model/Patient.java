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

import org.hibernate.annotations.Type;




@Entity
@Table(name="patient")
public class Patient implements Serializable {
	
	private static final long serialVersionUD =1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer patientId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="sex")
	private String sex;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dob")
	private Date dob;
	
	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
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

	public Patient(Integer patientId, String name, String address, String sex, Date dob) {
		this.patientId = patientId;
		this.name = name;
		this.address = address;
		this.sex = sex;
		this.dob = dob;
	}
	
	public Patient(Patient patient){
		this.patientId = patient.patientId;
		this.name = patient.name;
		this.address = patient.address;
		this.sex = patient.sex;
		this.dob = patient.dob;
	}
	public Patient()
	{
		this.patientId = 0;
		this.name = "";
		this.address = "";
		this.sex = "";
		this.dob = null;
	}
}
