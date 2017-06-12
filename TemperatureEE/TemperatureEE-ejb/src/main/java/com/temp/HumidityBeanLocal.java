/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temp;

import com.temp.model.Humidity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author brettalcox
 */
@Local
public interface HumidityBeanLocal {
    public String logInterval(Humidity humidity);
    
    public List<Humidity> getAllIntervals();
    
    public List<Humidity> getLastDayIntervals();
}
