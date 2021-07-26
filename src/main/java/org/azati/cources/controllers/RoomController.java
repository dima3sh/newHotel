package org.azati.cources.controllers;

import org.azati.cources.dto.EquipmentDTO;
import org.azati.cources.dto.GuestDTO;
import org.azati.cources.dto.RoomDTO;
import org.azati.cources.dictionaries.DirectoryStatus;
import org.azati.cources.entity.Equipment;
import org.azati.cources.entity.Guest;
import org.azati.cources.entity.Room;
import org.azati.cources.enums.StatusRoom;
import org.azati.cources.jms.Sender;
import org.azati.cources.services.EquipmentService;
import org.azati.cources.services.GuestService;
import org.azati.cources.services.RoomService;
import org.azati.cources.utils.DTOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class RoomController {

    public static Logger log = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomService roomService;

    @Autowired
    private Sender sender;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private GuestService guestService;


    @RequestMapping(value = "/newroom")
    public String addRoom(Model model) {
        model.addAttribute("isEdit", false);
        return "addroom";
    }

    @RequestMapping(value = "/newroom", params = {"number_of_beds", "cost_per_hour", "status_room",
            "equipments", "guest", "room_id"}, method = RequestMethod.GET)
    public String addRoom(Model model, @RequestParam("number_of_beds") Integer numberOfBeds,
                          @RequestParam("cost_per_hour") Integer costPerHour, @RequestParam("status_room") Integer statusRoom,
                          @RequestParam("equipments") List<Long> equipmentsId, @RequestParam("guest") Long guestId
            , @RequestParam("room_id") Long roomId) {
        Room room = new Room();
        room.setFreeRoom(true);
        room.setNumberOfBeds(numberOfBeds);
        room.setCostPerHour(costPerHour);
        DirectoryStatus directoryStatus = new DirectoryStatus();
        directoryStatus.setStatusRoom(StatusRoom.values()[statusRoom - 1]);
        directoryStatus.setLinkId(statusRoom);
        room.setDirectoryStatus(directoryStatus);
        room.setRoomId(roomId);
        List<Equipment> equipmentList = new ArrayList<>();
        if (guestId != null) {
            Guest guest = guestService.getGuest(guestId);
            roomService.updateFreeRoomStatus(guest.getGuestRoomId().getRoomId(), true);
            Room oldRoom = roomService.getRoom(guest.getGuestRoomId().getRoomId());
            oldRoom.getGuests().remove(guest);
            roomService.updateRoom(oldRoom);
            room.setGuests(Collections.singletonList(guest));
            guest.setGuestRoomId(room);
            roomService.updateFreeRoomStatus(room.getRoomId(), false);
        }

        if (equipmentsId.size() != 0) {
            equipmentsId.forEach(equipmentId -> {
                Room oldRoom = roomService.getRoom(equipmentService.getEquipment(equipmentId).getRoom().getRoomId());
                //oldRoom.getEquipments().remove(equipmentService.getEquipment(equipmentId));
                roomService.updateRoom(oldRoom);
                Equipment equipment = equipmentService.getEquipment(equipmentId);
                equipment.setRoom(room);
                equipmentList.add(equipment);
                equipmentService.updateEquipment(equipment);
            });
            room.setEquipments(equipmentList);
        }

        roomService.updateRoom(room);
        List<RoomDTO> roomsDTO = new ArrayList<>();
        roomService.getRooms().forEach(r -> {
            if (r.getRoomId() == roomId) {
                r.setEquipments(room.getEquipments());
                r.setGuests(room.getGuests());
            }
            roomsDTO.add(DTOUtil.creteRoomDTO(r));
        });

        model.addAttribute("rooms", roomsDTO);
        return "rooms";
    }

    @RequestMapping(value = "/newroom", params = {"number_of_beds", "cost_per_hour"}, method = RequestMethod.GET)
    public String addRoom(Model model, @RequestParam("number_of_beds") Integer numberOfBeds,
                          @RequestParam("cost_per_hour") Integer costPerHour) {
        Room room = new Room();
        room.setFreeRoom(true);
        room.setNumberOfBeds(numberOfBeds);
        room.setCostPerHour(costPerHour);
        DirectoryStatus directoryStatus = new DirectoryStatus();
        directoryStatus.setStatusRoom(StatusRoom.clean);
        directoryStatus.setLinkId(StatusRoom.clean.getIndex());
        room.setDirectoryStatus(directoryStatus);

        roomService.addRoom(room);
        List<RoomDTO> roomsDTO = new ArrayList<>();
        roomService.getRooms().forEach(r -> roomsDTO.add(DTOUtil.creteRoomDTO(r)));
        model.addAttribute("rooms", roomsDTO);
        return "rooms";
    }

    @RequestMapping(value = "/edit/room", params = {"id"}, method = RequestMethod.GET)
    public String addRoom(Model model, @RequestParam("id") Long roomId) {
        RoomDTO roomDTO = DTOUtil.creteRoomDTO(roomService.getRoom(roomId));
        List<EquipmentDTO> equipmentDTOList = new ArrayList<>();
        equipmentService.getAllEquipments().forEach(equipment -> {
            if (equipment.getRoom().getRoomId() == 20 || equipment.getRoom().getRoomId().equals(roomId)) {
                equipmentDTOList.add(DTOUtil.createEquipmentDTO(equipment));
            }
        });
        List<GuestDTO> guestDTOList = new ArrayList<>();
        guestService.getGuests().forEach(guest -> guestDTOList.add(DTOUtil.createGuestDTO(guest)));
        model.addAttribute("roomStatuses", StatusRoom.values());
        model.addAttribute("room", roomDTO);
        model.addAttribute("isEdit", true);
        model.addAttribute("equipments", equipmentDTOList);
        model.addAttribute("guests", guestDTOList);
        return "addroom";
    }

    @RequestMapping(value = "/getroom", params = {"id"}, method = RequestMethod.GET)
    public String getRoom(Model model, @RequestParam(value = "id") Long room_id) {
        log.info("path : /getroom ; id = " + room_id.toString() + " ; get room with id");
        RoomDTO roomDTO = DTOUtil.creteRoomDTO(roomService.getRoom(room_id));
        model.addAttribute("room", roomDTO);
        return "getroom";
    }

    @ResponseBody
    @RequestMapping(value = "/addrooms")
    public String addRooms() {
        log.info("path : /addrooms ; controller RoomController");

        sender.sendMessage("queue.in", "getAllRooms");
        return "addrooms";
    }

    @RequestMapping(value = "/rooms")
    public String getRooms(Model model) {
        log.info("path : /rooms ; print all rooms");

        List<RoomDTO> roomsDTO = new ArrayList<>();
        roomService.getRooms().forEach(room -> roomsDTO.add(DTOUtil.creteRoomDTO(room)));
        model.addAttribute("rooms", roomsDTO);
        return "rooms";
    }

    @RequestMapping(value = "/rooms", params = {"room_id"}, method = RequestMethod.GET)
    public String getRooms(Model model, @RequestParam(value = "room_id") Long room_id) {
        List<RoomDTO> roomsDTO = new ArrayList<>();
        roomService.removeRoom(room_id);
        roomService.getRooms().forEach(room -> roomsDTO.add(DTOUtil.creteRoomDTO(room)));
        model.addAttribute("rooms", roomsDTO);
        return "rooms";
    }

}
