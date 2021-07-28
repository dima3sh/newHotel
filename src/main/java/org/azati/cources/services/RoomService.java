package org.azati.cources.services;

import org.azati.cources.dictionaries.DirectoryStatus;
import org.azati.cources.entity.Equipment;
import org.azati.cources.entity.Guest;
import org.azati.cources.entity.Room;
import org.azati.cources.enums.StatusRoom;
import org.azati.cources.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RoomService {

    public static Logger log = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    GuestService guestService;

    @Autowired
    EquipmentService equipmentService;

    public Room addRoom(Room room) {
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

    public Guest moveGuest(Room room, Long guestId) {
        Guest guest = guestService.getGuest(guestId);
        updateFreeRoomStatus(guest.getGuestRoomId().getRoomId(), true);
        Room oldRoom = getRoom(guest.getGuestRoomId().getRoomId());
        oldRoom.getGuests().remove(guest);
        updateRoom(oldRoom);
        room.setGuests(Collections.singletonList(guest));
        guest.setGuestRoomId(room);
        updateFreeRoomStatus(room.getRoomId(), false);
        return guest;
    }

    public List<Equipment> moveEquipments(Room room, List<Long> equipmentsId) {
        List<Equipment> equipmentList = new ArrayList<>();
        equipmentsId.forEach(equipmentId -> {
            Room oldRoom = getRoom(equipmentService.getEquipment(equipmentId).getRoom().getRoomId());
            updateRoom(oldRoom);
            Equipment equipment = equipmentService.getEquipment(equipmentId);
            equipment.setRoom(room);
            equipmentList.add(equipment);
            equipmentService.updateEquipment(equipment);
        });
        room.setEquipments(equipmentList);
        return equipmentList;
    }

    public Room roomFactory(Long roomId, Boolean isFreeRoom, Integer numberOfBeds, Integer costPerHour
            , StatusRoom statusRoom) {
        Room room = new Room();
        room.setFreeRoom(isFreeRoom);
        room.setNumberOfBeds(numberOfBeds);
        room.setCostPerHour(costPerHour);
        DirectoryStatus directoryStatus = new DirectoryStatus();
        directoryStatus.setStatusRoom(statusRoom);
        directoryStatus.setLinkId(statusRoom.getIndex());
        room.setDirectoryStatus(directoryStatus);
        room.setRoomId(roomId);
        return room;
    }

    public List<Room> getRooms(Integer numberPage, Integer countRecord, String sortBy) {
        Pageable elements = PageRequest.of(numberPage, countRecord, Sort.by(sortBy));
        Page<Room> allRooms = roomRepository.findAll(elements);
        return allRooms.getContent();
    }

    public Long getCountRecords() {
        return roomRepository.count();
    }

}
