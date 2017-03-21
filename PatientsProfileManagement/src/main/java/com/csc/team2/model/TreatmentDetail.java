package com.csc.team2.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 *
 * @author DIEP
 */
@Entity
@Table(name = "treatment_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TreatmentDetail.findAll", query = "SELECT t FROM TreatmentDetail t")
    , @NamedQuery(name = "TreatmentDetail.findById", query = "SELECT t FROM TreatmentDetail t WHERE t.id = :id")
    , @NamedQuery(name = "TreatmentDetail.findByDiseases", query = "SELECT t FROM TreatmentDetail t WHERE t.diseases = :diseases")})
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@treatmentdetailId")

public class TreatmentDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "diseases")
    private String diseases;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "treatmentdtId")
    private List<File> fileList;
    
    @JoinColumn(name = "treatment_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    //@JsonBackReference
    private Treatment treatmentId;
    @JoinColumn(name = "medicine_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Medicine medicineId;

    public TreatmentDetail() {
    }

    public TreatmentDetail(Integer id) {
        this.id = id;
    }

    public TreatmentDetail(Integer id, String diseases) {
        this.id = id;
        this.diseases = diseases;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiseases() {
        return diseases;
    }

    public void setDiseases(String diseases) {
        this.diseases = diseases;
    }

    public Treatment getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Treatment treatmentId) {
        this.treatmentId = treatmentId;
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
        if (!(object instanceof TreatmentDetail)) {
            return false;
        }
        TreatmentDetail other = (TreatmentDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "asasdsa.TreatmentDetail[ id=" + id + " ]";
    }
    
}

