/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.temp.mitochondria;

import java.util.HashMap;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author brettalcox
 */
public class MQFactory {

    private Connection connection;
    private ConnectionFactory connectionFactory;
    private Session session;
    private Queue queue;

    public void MQFactory() {

    }

    public void init(String queueName) {
        try {
            connectionFactory = new ActiveMQConnectionFactory(System.getProperty("broker_url"));
            connection = connectionFactory.createConnection(System.getProperty("activemq_user"), System.getProperty("activemq_pass"));
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            queue = session.createQueue(queueName);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(HashMap<String, String> data) {
        try {
            MessageProducer producer = session.createProducer(queue);
            ObjectMessage message = session.createObjectMessage(data);
            producer.send(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
