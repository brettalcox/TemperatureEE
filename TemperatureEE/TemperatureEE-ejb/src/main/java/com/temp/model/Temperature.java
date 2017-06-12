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
@Table(name="temperature", schema="temperature")
@NamedQuery(name="Temperature.findAll", query="SELECT o FROM Temperature o ORDER BY o.loggedTime DESC")
public class Temperature implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @JsonProperty
    @Column(name="ambient_temperature")
    private double ambientTemperature;
    
    @JsonProperty
    @Column(name="target_temperature")
    private double targetTemperature;
    
    @JsonProperty
    @Column(name="outside_temperature")
    private double outsideTemperature;
    
    @JsonProperty
    @Column(name="current_weather")
    private String currentWeather;
    
    @JsonProperty
    @Column(name="logged_time")
    private Timestamp loggedTime;
    
    public void Temperature(double ambientTemperature, double targetTemperature, 
            double outsideTemperature, String currentWeather, Timestamp loggedTime) {
        this.ambientTemperature = ambientTemperature;
        this.targetTemperature = targetTemperature;
        this.outsideTemperature = outsideTemperature;
        this.currentWeather = currentWeather;
        this.loggedTime = loggedTime;
    }

    public Long getId() { return id; }
    
    public double getAmbientTemperature() { return ambientTemperature; }
        
    public double getTargetTemperature() { return targetTemperature; }

    public double getOutsideTemperature() { return outsideTemperature; }
    
    public String getCurrentWeather() { return currentWeather; }
    
    public Timestamp getLoggedTime() { return loggedTime; }
    
    public void setAmbientTemperature(double ambientTemperature) {
        this.ambientTemperature = ambientTemperature;
    }
    
    public void setTargetTemperature(double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }
    
    public void setOutsideTemperature(double outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }
    
    public void setCurrentWeather(String currentWeather) {
        this.currentWeather = currentWeather;
    }
    
    public void setLoggedTime(Timestamp loggedTime) {
        this.loggedTime = loggedTime;
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
        if (!(object instanceof Temperature)) {
            return false;
        }
        Temperature other = (Temperature) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.temp.model.Temperature[ id=" + id + " ]";
    }
    
}
