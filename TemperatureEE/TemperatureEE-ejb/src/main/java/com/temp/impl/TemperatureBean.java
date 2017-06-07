/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temp.impl;

import com.bean.AbstractBean;
import com.temp.TemperatureBeanLocal;
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
public class TemperatureBean extends AbstractBean implements TemperatureBeanLocal {

    @Override
    public String logInterval(Temperature temperature) {
        if(temperature != null) {
            try {
                getEntityManager().persist(temperature);
                getEntityManager().flush();
            }
              catch (PersistenceException ex) {
                Logger.getLogger(TemperatureBean.class.getName()).log(Level.SEVERE, null, ex);
                return "Unable to create interval.";
            }
            return "Interval created.";
        }
        return "Unable to create interval.";
    }

    @Override
    public List<Temperature> getAllIntervals() {
        TypedQuery<Temperature> query = getEntityManager().createNamedQuery("Temperature.findAll", Temperature.class);
        List<Temperature> results = query.getResultList();
        
        return results;
    }

    @Override
    public List<Temperature> getLastDayIntervals() {
        TypedQuery<Temperature> query = getEntityManager().createNamedQuery("Temperature.findAll", Temperature.class);
        List<Temperature> results = query.setMaxResults(96).getResultList();
        
        return results;
    }

}
