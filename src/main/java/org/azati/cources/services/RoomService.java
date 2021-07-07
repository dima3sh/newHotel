package org.azati.cources.services;

import org.azati.cources.entity.Room;
import org.azati.cources.oldfile.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public RoomService(){}

    public RoomService (RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    public Room add(Room room) {
        return roomRepository.addRoom(room);
    }

    public Boolean removeById(Integer room_id) {
        return roomRepository.removeById(room_id);
    }

    public Boolean remove(Room room) {
        return roomRepository.removeRoom(room);
    }

    public Room edit(Room room) {
        return roomRepository.editRoom(room);
    }

    public Boolean upCostRoom(){
        return roomRepository.upCostRoom();
    }
}
