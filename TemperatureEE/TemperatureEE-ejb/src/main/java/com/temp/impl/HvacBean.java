/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temp.impl;

import com.bean.AbstractBean;
import com.temp.HvacBeanLocal;
import com.temp.model.Hvac;
import com.temp.model.Temperature;
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
public class HvacBean extends AbstractBean implements HvacBeanLocal {
    @Override
    public String logInterval(Hvac hvac) {
        if(hvac != null) {
            try {
                getEntityManager().persist(hvac);
                getEntityManager().flush();
            }
              catch (PersistenceException ex) {
                Logger.getLogger(HvacBean.class.getName()).log(Level.SEVERE, null, ex);
                return "Unable to create interval.";
            }
            return "Interval created.";
        }
        return "Unable to create interval.";
    }
    
    @Override
    public List<Hvac> getAllIntervals() {
        TypedQuery<Hvac> query = getEntityManager().createNamedQuery("Hvac.findAll", Hvac.class);
        List<Hvac> results = query.getResultList();
        
        return results;
    }

    @Override
    public List<Hvac> getLastDayIntervals() {
        TypedQuery<Hvac> query = getEntityManager().createNamedQuery("Hvac.findAll", Hvac.class);
        List<Hvac> results = query.setMaxResults(96).getResultList();
        
        return results;
    }
}
