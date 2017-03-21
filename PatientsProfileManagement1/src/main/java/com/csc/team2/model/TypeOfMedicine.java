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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 *
 * @author DIEP
 */
@Entity
@Table(name = "type_of_medicine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeOfMedicine.findAll", query = "SELECT t FROM TypeOfMedicine t")
    , @NamedQuery(name = "TypeOfMedicine.findById", query = "SELECT t FROM TypeOfMedicine t WHERE t.id = :id")
    , @NamedQuery(name = "TypeOfMedicine.findByTypename", query = "SELECT t FROM TypeOfMedicine t WHERE t.typename = :typename")})
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="typemedicineId")
public class TypeOfMedicine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "typename")
    private String typename;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeId")
    @JsonBackReference
    private List<Medicine> medicineList;

    public TypeOfMedicine() {
    }

    public TypeOfMedicine(Integer id) {
        this.id = id;
    }

    public TypeOfMedicine(Integer id, String typename) {
        this.id = id;
        this.typename = typename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    @XmlTransient
    public List<Medicine> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<Medicine> medicineList) {
        this.medicineList = medicineList;
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
        if (!(object instanceof TypeOfMedicine)) {
            return false;
        }
        TypeOfMedicine other = (TypeOfMedicine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "asasdsa.TypeOfMedicine[ id=" + id + " ]";
    }
    
}

