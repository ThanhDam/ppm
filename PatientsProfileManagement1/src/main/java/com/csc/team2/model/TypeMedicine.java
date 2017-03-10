package com.csc.team2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="type_of_medicine")
public class TypeMedicine implements Serializable {
	
	private static final long serialVersionUD =1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idtype")
	private Integer typeId;
	
	@Column(name="typename")
	private String typeName;

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public TypeMedicine(Integer typeId, String typeName) {
		this.typeId = typeId;
		this.typeName = typeName;
	}
	
	public TypeMedicine(TypeMedicine typeMedicine){
		this.typeId = typeMedicine.typeId;
		this.typeName = typeMedicine.typeName;
	}
	
	public TypeMedicine(){
		this.typeId = 0;
		this.typeName = "";
	}
}
