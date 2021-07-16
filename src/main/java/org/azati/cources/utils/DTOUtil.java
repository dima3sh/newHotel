package org.azati.cources.utils;

import org.azati.cources.dto.EquipmentDTO;
import org.azati.cources.dto.GuestDTO;
import org.azati.cources.dto.RoomDTO;
import org.azati.cources.entity.Equipment;
import org.azati.cources.entity.Guest;
import org.azati.cources.entity.Room;

public class DTOUtil {

    public static RoomDTO creteRoomDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomId(room.getRoomId());
        roomDTO.setFreeRoom(room.getFreeRoom());
        roomDTO.setEquipments(room.getEquipments());
        roomDTO.setStatusRoom(room.getDirectoryStatus().getStatusRoom());
        roomDTO.setGuests(room.getGuests());
        roomDTO.setCostPerHour(room.getCostPerHour());
        roomDTO.setNumberOfBeds(room.getNumberOfBeds());
        return roomDTO;
    }

    public static EquipmentDTO createEquipmentDTO(Equipment equipment) {
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        equipmentDTO.setEquipmentId(equipment.getEquipment_id());
        equipmentDTO.setEquipmentRoomId(equipment.getRoom().getRoomId());
        equipmentDTO.setStateEquipment(equipment.getEquipmentStateDictionary().getStateEquipment());
        equipmentDTO.setName(equipment.getName());
        equipmentDTO.setWeight(equipment.getWeight());
        equipmentDTO.setCostPerObject(equipment.getCostPerObject());
        return equipmentDTO;
    }

    public static GuestDTO createGuestDTO(Guest guest) {
        GuestDTO guestDTo = new GuestDTO();
        guestDTo.setName(guest.getName());
        guestDTo.setPhoneNumber(guest.getPhoneNumber());
        guestDTo.setEmailAddress(guest.getEmailAddress());
        guestDTo.setGuestId(guest.getGuestId());
        guestDTo.setArrivalTime(guest.getArrivalTime());
        guestDTo.setInvoice(guest.getInvoice());
        guestDTo.setDepartureTime(guest.getDepartureTime());
        guestDTo.setRoomId(guest.getGuestRoomId().getRoomId());
        return guestDTo;
    }
}
