/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.messagebean;

import com.temp.HvacBeanLocal;
import com.temp.model.Hvac;
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
@MessageDriven(name = "hvacmdb", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/HVACQUEUE"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "HVACQUEUE"),
    @ActivationConfigProperty(propertyName = "resourceAdapter", propertyValue = "activemq-rar-5.14.5")
   
})
public class HvacListener implements MessageListener {

    @EJB
    HvacBeanLocal hvacBean;
    
    @Override
    public void onMessage(Message message) {
        try {
            if (message != null && message instanceof ObjectMessage) {
                ObjectMessage objectMessage = (ObjectMessage) message;
                HashMap<String, String> messageHashMap = (HashMap<String, String>) objectMessage.getObject();
                Hvac newInterval = new Hvac();

                newInterval.setHvacMode(messageHashMap.get("hvac_mode"));
                newInterval.setHvacState(messageHashMap.get("hvac_state"));
                newInterval.setHasLeaf(Boolean.parseBoolean(messageHashMap.get("has_leaf")));
                newInterval.setLoggedTime(Timestamp.valueOf(messageHashMap.get("logged_time")));

                hvacBean.logInterval(newInterval);

            }
        } catch(JMSException e) {
            Logger.getLogger(HvacListener.class.getName()).log(Level.SEVERE, null, e);
        }    
    }
}
