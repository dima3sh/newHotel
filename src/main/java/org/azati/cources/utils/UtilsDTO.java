package org.azati.cources.utils;

import org.azati.cources.dto.GuestDTO;
import org.azati.cources.dto.RoomDTO;
import org.azati.cources.entity.Guest;
import org.azati.cources.entity.Room;

public class UtilsDTO {

    public static RoomDTO creteRoomDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoom_id(room.getRoom_id());
        roomDTO.setFreeRoom(room.getFreeRoom());
        roomDTO.setStatusRoom(room.getStatusRoom());
        roomDTO.setEquipments(room.getEquipments());
        roomDTO.setGuests(room.getGuests());
        roomDTO.setCostPerHour(room.getCostPerHour());
        roomDTO.setNumberOfBeds(room.getNumberOfBeds());
        return roomDTO;
    }

    public static GuestDTO createGuestDTO(Guest guest) {
        GuestDTO guestDTo = new GuestDTO();
        guestDTo.setGuest_id(guest.getGuest_id());
        guestDTo.setArrivalTime(guest.getArrivalTime());
        guestDTo.setInvoice(guest.getInvoice());
        guestDTo.setDepartureTime(guest.getDepartureTime());
        guestDTo.setRoom_id(guest.getRoom_id());
        return guestDTo;
    }
}
