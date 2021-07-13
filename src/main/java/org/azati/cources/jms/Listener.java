package org.azati.cources.jms;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.azati.cources.entity.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class Listener {

    public static Logger log = LoggerFactory.getLogger(Listener.class);

    @JmsListener(destination = "queue.out")
    public String receiveMessage(final Message jsonMessage) throws JMSException {
        String messageData = null;
        String response = null;
        List<Room> list = new ArrayList<>();
        if (jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) jsonMessage;
            messageData = textMessage.getText();
            log.info("get from queue.out; message : " + messageData);

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            Type foo = new TypeToken<List<Room>>(){}.getType();
            list = gson.fromJson(messageData, foo);
            Room room = list.get(0);
            System.out.println();
        }
        return response;
    }

}