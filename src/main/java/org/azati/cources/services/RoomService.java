package org.azati.cources.services;

import org.azati.cources.entity.Room;
import org.azati.cources.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    public static Logger log = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    RoomRepository roomRepository;

    public Room addRoom(Room room) {
        roomRepository.save(room);
        return room;
    }

    public Room getRoom(Long room_id) {
        if (roomRepository.findById(room_id).isPresent()) {
            return roomRepository.findById(room_id).get();
        }
        log.debug("");
        throw new RuntimeException("not found room");
    }

    public List<Room> addRooms(List<Room> rooms) {
        for (int i = 0; i < rooms.size(); i++) {
            addRoom((Room)rooms.get(i));
        }
        //rooms.forEach(this::addRoom);
        return rooms;
    }
}
