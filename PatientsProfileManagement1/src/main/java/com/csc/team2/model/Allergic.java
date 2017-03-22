package com.csc.team2.model;


import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 *
 * @author DIEP
 */
@Entity
@Table(name = "allergic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Allergic.findAll", query = "SELECT a FROM Allergic a")
    , @NamedQuery(name = "Allergic.findById", query = "SELECT a FROM Allergic a WHERE a.id = :id")
    , @NamedQuery(name = "Allergic.findByMedicineId", query = "SELECT a FROM Allergic a WHERE a.medicineId = :medicineId")})
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@allergicId", scope= Allergic.class)

public class Allergic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "medicine_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Medicine medicineId;

	@JoinColumn(name = "patient_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
	@JsonBackReference
    private Patient patientId;

    public Allergic() {
    }

    public Allergic(Integer id) {
        this.id = id;
    }

    public Allergic(Integer id, Medicine medicineId) {
        this.id = id;
        this.medicineId = medicineId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }
    
    public Medicine getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Medicine medicineId) {
		this.medicineId = medicineId;
	}


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Allergic)) {
            return false;
        }
        Allergic other = (Allergic) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "asasdsa.Allergic[ id=" + id + " ]";
    }
    
}
