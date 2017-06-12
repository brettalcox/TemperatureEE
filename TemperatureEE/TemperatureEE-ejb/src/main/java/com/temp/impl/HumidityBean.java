/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temp.impl;

import com.bean.AbstractBean;
import com.temp.HumidityBeanLocal;
import com.temp.model.Humidity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 *
 * @author brettalcox
 */
@Stateless
public class HumidityBean extends AbstractBean implements HumidityBeanLocal {
    @Override
    public String logInterval(Humidity humidity) {
        if(humidity != null) {
            try {
                getEntityManager().persist(humidity);
                getEntityManager().flush();
            }
              catch (PersistenceException ex) {
                Logger.getLogger(HumidityBean.class.getName()).log(Level.SEVERE, null, ex);
                return "Unable to create interval.";
            }
            return "Interval created.";
        }
        return "Unable to create interval.";
    }
    
    @Override
    public List<Humidity> getAllIntervals() {
        TypedQuery<Humidity> query = getEntityManager().createNamedQuery("Humidity.findAll", Humidity.class);
        List<Humidity> results = query.getResultList();
        
        return results;
    }

    @Override
    public List<Humidity> getLastDayIntervals() {
        TypedQuery<Humidity> query = getEntityManager().createNamedQuery("Humidity.findAll", Humidity.class);
        List<Humidity> results = query.setMaxResults(96).getResultList();
        
        return results;
    }
}
