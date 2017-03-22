package com.csc.team2.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
/**
*
* @author DIEP
*/
@Entity
@Table(name = "file")
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "File.findAll", query = "SELECT f FROM File f")
   , @NamedQuery(name = "File.findById", query = "SELECT f FROM File f WHERE f.id = :id")})
public class File implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Basic(optional = false)
   @Column(name = "id")
   private Integer id;
   
   @Lob
   @Column(name = "image")
   private byte[] image;
   
   @JoinColumn(name = "treatment_id", referencedColumnName = "id")
   @ManyToOne(optional = false)
   private Treatment treatmentId;

   public File() {
   }

   public File(Integer id) {
       this.id = id;
   }

   public Integer getId() {
       return id;
   }

   public void setId(Integer id) {
       this.id = id;
   }

   public byte[] getImage() {
       return image;
   }

   public void setImage(byte[] image) {
       this.image = image;
   }

   public Treatment getTreatmentId() {
       return treatmentId;
   }

   public void setTreatmentId(Treatment treatmentId) {
       this.treatmentId = treatmentId;
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
       if (!(object instanceof File)) {
           return false;
       }
       File other = (File) object;
       if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
           return false;
       }
       return true;
   }

   @Override
   public String toString() {
       return "entity.File[ id=" + id + " ]";
   }
   
}