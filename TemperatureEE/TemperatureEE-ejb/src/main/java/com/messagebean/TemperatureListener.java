/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.messagebean;

import com.temp.TemperatureBeanLocal;
import com.temp.impl.TemperatureBean;
import com.temp.model.Temperature;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author brettalcox
 */
@MessageDriven(name = "temperaturemdb", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/TEMPQUEUE"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "TEMPQUEUE"),
    @ActivationConfigProperty(propertyName = "resourceAdapter", propertyValue = "activemq-rar-5.14.5")
   
})
public class TemperatureListener implements MessageListener {

    @EJB
    TemperatureBeanLocal temperatureBean;
            
    @Override
    public void onMessage(Message message) {
        try {
            if (message != null && message instanceof ObjectMessage) {
                ObjectMessage objectMessage = (ObjectMessage) message;
                HashMap<String, String> messageHashMap = (HashMap<String, String>) objectMessage.getObject();
                Temperature newInterval = new Temperature();

                newInterval.setAmbientTemperature(Double.parseDouble(messageHashMap.get("ambient_temperature")));
                newInterval.setAmbientHumidity(Double.parseDouble(messageHashMap.get("ambient_humidity")));
                newInterval.setTargetTemperature(Double.parseDouble(messageHashMap.get("target_temperature")));
                newInterval.setOutsideTemperature(Double.parseDouble(messageHashMap.get("outside_temperature")));
                newInterval.setOutsideHumidity(Double.parseDouble(messageHashMap.get("outside_humidity")));
                newInterval.setHvacMode(messageHashMap.get("hvac_mode"));
                newInterval.setHvacState(messageHashMap.get("hvac_state"));
                newInterval.setHasLeaf(Boolean.parseBoolean(messageHashMap.get("has_leaf")));
                newInterval.setCurrentWeather(messageHashMap.get("current_weather"));

                temperatureBean.logInterval(newInterval);

            }
        } catch(JMSException e) {
            Logger.getLogger(TemperatureListener.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
