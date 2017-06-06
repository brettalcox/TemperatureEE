/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temp;

import com.temp.model.Temperature;
import javax.ejb.Local;

/**
 *
 * @author brettalcox
 */
@Local
public interface TemperatureBeanLocal {
    
    public String logInterval(Temperature temperature);
}
