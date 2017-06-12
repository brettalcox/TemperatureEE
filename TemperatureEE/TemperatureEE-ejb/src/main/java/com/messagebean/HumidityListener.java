/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.messagebean;

import com.temp.HumidityBeanLocal;
import com.temp.model.Humidity;
import java.sql.Timestamp;
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
@MessageDriven(name = "humiditymdb", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/HUMIDQUEUE"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "HUMIDQUEUE"),
    @ActivationConfigProperty(propertyName = "resourceAdapter", propertyValue = "activemq-rar-5.14.5")
   
})
public class HumidityListener implements MessageListener {

    @EJB
    HumidityBeanLocal humidityBean;
    
    @Override
    public void onMessage(Message message) {
        try {
            if (message != null && message instanceof ObjectMessage) {
                ObjectMessage objectMessage = (ObjectMessage) message;
                HashMap<String, String> messageHashMap = (HashMap<String, String>) objectMessage.getObject();
                Humidity newInterval = new Humidity();

                newInterval.setAmbientHumidity(Double.parseDouble(messageHashMap.get("ambient_humidity")));
                newInterval.setOutsideHumidity(Double.parseDouble(messageHashMap.get("outside_humidity")));
                newInterval.setLoggedTime(Timestamp.valueOf(messageHashMap.get("logged_time")));

                humidityBean.logInterval(newInterval);

            }
        } catch(JMSException e) {
            Logger.getLogger(HumidityListener.class.getName()).log(Level.SEVERE, null, e);
        }    
    }
}
