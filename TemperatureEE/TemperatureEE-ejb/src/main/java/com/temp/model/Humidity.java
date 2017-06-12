/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author brettalcox
 */
@Entity
@Table(name="humidity", schema="temperature")
@NamedQuery(name="Humidity.findAll", query="SELECT o FROM Humidity o ORDER BY o.loggedTime DESC")
public class Humidity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @JsonProperty
    @Column(name="ambient_humidity")
    private double ambientHumidity;
    
    @JsonProperty
    @Column(name="outside_humidity")
    private double outsideHumidity;
    
    @JsonProperty
    @Column(name="logged_time")
    private Timestamp loggedTime;
    
    public double getAmbientHumidity() { return ambientHumidity; }

    public double getOutsideHumidity() { return outsideHumidity; }
    
    public Timestamp getLoggedTime() { return loggedTime; }

    public Long getId() {
        return id;
    }
    
    public void setAmbientHumidity(double ambientHumidity) {
        this.ambientHumidity = ambientHumidity;
    }
    
    public void setOutsideHumidity(double outsideHumidity) {
        this.outsideHumidity = outsideHumidity;
    }
    
    public void setLoggedTime(Timestamp loggedTime) {
        this.loggedTime = loggedTime;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Humidity)) {
            return false;
        }
        Humidity other = (Humidity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.temp.model.Humidity[ id=" + id + " ]";
    }
    
}
