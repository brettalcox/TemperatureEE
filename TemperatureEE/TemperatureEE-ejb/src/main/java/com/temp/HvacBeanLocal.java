/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temp;

import com.temp.model.Hvac;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author brettalcox
 */
@Local
public interface HvacBeanLocal {
    public String logInterval(Hvac hvac);
    
    public List<Hvac> getAllIntervals();
    
    public List<Hvac> getLastDayIntervals();
}
