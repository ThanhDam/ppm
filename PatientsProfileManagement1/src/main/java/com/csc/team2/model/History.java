package com.csc.team2.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="history")
public class History implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTreatment")
    private Integer treatmentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "contentchange")
    private String contentchange;
//    @JoinColumn(name = "treatment_id", referencedColumnName = "id")
//    @ManyToOne(optional = false)
//    private Treatment treatmentId;

    public History() {
    }

    public History(Integer id) {
        this.id = id;
    }

    public History(Integer id, Date dateTime, String contentchange) {
        this.id = id;
        this.dateTime = dateTime;
        this.contentchange = contentchange;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getContentchange() {
        return contentchange;
    }

    public void setContentchange(String contentchange) {
        this.contentchange = contentchange;
    }

}
