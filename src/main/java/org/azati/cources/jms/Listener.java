package org.azati.cources.jms;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.azati.cources.entity.Room;
import org.azati.cources.services.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.List;

@Component
public class Listener {

    public static Logger log = LoggerFactory.getLogger(Listener.class);
    @Autowired
    RoomService roomService;

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

            room
            list =  gson.fromJson(messageData, List.class);
            roomService.addRooms(list);
        }
        return response;
    }

}