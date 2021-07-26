package org.azati.cources.services;

import org.azati.cources.entity.Equipment;
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
        log.info("add room : " + room.toString());
        roomRepository.addRoom(room.getFreeRoom(), room.getNumberOfBeds(),
                room.getCostPerHour(), room.getDirectoryStatus().getLinkId());
        return room;
    }

    public Room updateFreeRoomStatus(Long roomId, Boolean status) {
        Room room = roomRepository.findById(roomId).get();
        room.setFreeRoom(status);
        room = roomRepository.save(room);
        return room;
    }

    public Room getRoom(Long room_id) {
        if (roomRepository.findById(room_id).isPresent()) {
            return roomRepository.findById(room_id).get();
        }
        log.debug("throw RuntimeException");
        throw new RuntimeException("not found room");
    }

    public List<Room> addRooms(List<Room> rooms) {
        log.info("add several rooms");
        rooms.forEach(this::addRoom);
        return rooms;
    }

    public List<Room> getRooms() {
        return (List<Room>) roomRepository.findAll();
    }

    public void removeRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }

    public List<Equipment> getEquipmentByRoomID(Long roomId) {
        return getRoom(roomId).getEquipments();
    }

    public List<Room> getAllFreeRoom() {
        return roomRepository.findAllByIsFreeRoom(true);
    }

    public Room updateRoom(Room room) {
        Room upRoom = roomRepository.findById(room.getRoomId()).get();
        upRoom.setDirectoryStatus(room.getDirectoryStatus());
        upRoom.setNumberOfBeds(room.getNumberOfBeds());
        upRoom.setFreeRoom(room.getFreeRoom());
        upRoom.setCostPerHour(room.getCostPerHour());
        roomRepository.save(upRoom);
        return room;
    }
}
