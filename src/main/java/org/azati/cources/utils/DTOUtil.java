package org.azati.cources.utils;

import org.azati.cources.dto.EquipmentDTO;
import org.azati.cources.dto.GuestDTO;
import org.azati.cources.dto.RoomDTO;
import org.azati.cources.dto.UserDTO;
import org.azati.cources.entity.AppUser;
import org.azati.cources.entity.Equipment;
import org.azati.cources.entity.Guest;
import org.azati.cources.entity.Room;

import java.util.ArrayList;
import java.util.List;

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
        equipmentDTO.setEquipmentRoomId(equipment.getRoom() == null ? null : equipment.getRoom().getRoomId());
        equipmentDTO.setStateEquipment(equipment.getEquipmentStateDictionary().getStateEquipment());
        equipmentDTO.setName(equipment.getName());
        equipmentDTO.setWeight(equipment.getWeight());
        equipmentDTO.setCostPerObject(equipment.getCostPerObject());
        equipmentDTO.setProducer(equipment.getProducer());
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

    public static UserDTO createUserDTO(AppUser user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserRole(user.getUserRole().getUserRole());
        userDTO.setUsername(user.getUsername());
        userDTO.setName(user.getName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmailAddress(user.getEmailAddress());
        return userDTO;
    }

    public static List<UserDTO> createUsersDTO(List<AppUser> users) {
        List<UserDTO> usersDTO = new ArrayList<>();
        users.forEach(user -> {
            usersDTO.add(createUserDTO(user));
        });
        return usersDTO;
    }

    public static List<RoomDTO> createRoomsDTO(List<Room> rooms) {
        List<RoomDTO> roomsDTO = new ArrayList<>();
        rooms.forEach(room -> {
            roomsDTO.add(creteRoomDTO(room));
        });
        return roomsDTO;
    }

    public static List<GuestDTO> createGuestsDTO(List<Guest> rooms) {
        List<GuestDTO> guestsDTO = new ArrayList<>();
        rooms.forEach(guest -> {
            guestsDTO.add(createGuestDTO(guest));
        });
        return guestsDTO;
    }

    public static List<EquipmentDTO> createEquipmentsDTO(List<Equipment> rooms) {
        List<EquipmentDTO> equipmentsDTO = new ArrayList<>();
        rooms.forEach(equipment -> {
            equipmentsDTO.add(createEquipmentDTO(equipment));
        });
        return equipmentsDTO;
    }
}
