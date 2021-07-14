package org.azati.cources.jms;

import org.azati.cources.controllers.RoomController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class Sender {

    public static Logger log = LoggerFactory.getLogger(Sender.class);

    @Autowired
    JmsTemplate jmsTemplate;


    public void sendMessage(final String queueName, final String message) {
        log.info("send message: " + message + "; into : " + queueName);
        jmsTemplate.send(queueName, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

}
