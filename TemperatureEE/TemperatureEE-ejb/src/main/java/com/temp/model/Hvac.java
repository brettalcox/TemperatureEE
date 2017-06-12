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
@Table(name="hvac", schema="temperature")
@NamedQuery(name="Hvac.findAll", query="SELECT o FROM Hvac o ORDER BY o.loggedTime DESC")
public class Hvac implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @JsonProperty
    @Column(name="hvac_mode")
    private String hvacMode;
    
    @JsonProperty
    @Column(name="hvac_state")
    private String hvacState;
    
    @JsonProperty
    @Column(name="has_leaf")
    private boolean hasLeaf;
    
    @JsonProperty
    @Column(name="logged_time")
    private Timestamp loggedTime;
    
    public String getHvacMode() { return hvacMode; }
    
    public String getHvacState() { return hvacState; }
    
    public boolean getHasLeaf() { return hasLeaf; }
    
    public Timestamp getLoggedTime() { return loggedTime; }

    public Long getId() {
        return id;
    }
    
    public void setHvacMode(String hvacMode) {
        this.hvacMode = hvacMode;
    }
    
    public void setHvacState(String hvacState) {
        this.hvacState = hvacState;
    }
    
    public void setHasLeaf(boolean hasLeaf) {
        this.hasLeaf = hasLeaf;
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
        if (!(object instanceof Hvac)) {
            return false;
        }
        Hvac other = (Hvac) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.temp.model.Hvac[ id=" + id + " ]";
    }
    
}
