package org.azati.cources.controllers;

import org.azati.cources.dto.EquipmentDTO;
import org.azati.cources.dto.GuestDTO;
import org.azati.cources.dto.RoomDTO;
import org.azati.cources.entity.Guest;
import org.azati.cources.entity.Room;
import org.azati.cources.enums.StatusRoom;
import org.azati.cources.jms.Sender;
import org.azati.cources.services.EquipmentService;
import org.azati.cources.services.GuestService;
import org.azati.cources.services.RoomService;
import org.azati.cources.utils.DTOUtil;
import org.azati.cources.utils.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private Sender sender;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private GuestService guestService;

    @Value("${warehouse.id}")
    private Long warehouseId;


    @RequestMapping(value = "/newroom")
    public String addRoom(Model model) {
        model.addAttribute("isEdit", false);
        model.addAttribute("warehouseId", warehouseId);
        return "addroom";
    }

    @RequestMapping(value = "/newroom", params = {"number_of_beds", "cost_per_hour", "status_room",
            "equipments", "guest", "room_id"}, method = RequestMethod.GET)
    public String addRoom(Model model, @RequestParam("number_of_beds") Integer numberOfBeds
            , @RequestParam("cost_per_hour") Integer costPerHour
            , @RequestParam("status_room") Integer statusRoom
            , @RequestParam("equipments") List<Long> equipmentsId
            , @RequestParam("guest") Long guestId
            , @RequestParam("room_id") Long roomId
            , @RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size
            , @RequestParam(value = "sort", defaultValue = "roomId") String sortBy) {

        Room room = roomService.roomFactory(roomId, true, numberOfBeds, costPerHour, StatusRoom.values()[statusRoom - 1]);

        if (guestId != null) {
            roomService.moveGuest(room, guestId);
        }
        if (equipmentsId.size() != 0) {
            roomService.moveEquipments(room, equipmentsId);
        }

        List<Guest> guestList = guestService.getGuests().stream()
                .filter(guest -> guest.getGuestRoomId()
                        .getRoomId().equals(roomId)).collect(Collectors.toList());
        room.setGuests(guestList);
        roomService.updateRoom(room);

        List<RoomDTO> roomsDTO = DTOUtil.createRoomsDTO(roomService.getRooms(page - 1, size, sortBy));
        roomService.getRooms().forEach(roomImp -> {
            if (roomImp.getRoomId().equals(roomId)) {
                roomImp.setEquipments(room.getEquipments());
                roomImp.setGuests(room.getGuests());
            }
        });

        ModelUtil.setStandardModelElements(model, page, size, sortBy, (int) (Math.ceil(roomService.getCountRecords() * 1.0 / size)), "rooms");
        model.addAttribute("rooms", roomsDTO);
        return "rooms";
    }

    @RequestMapping(value = "/newroom", params = {"number_of_beds", "cost_per_hour"}, method = RequestMethod.GET)
    public String addRoom(Model model, @RequestParam("number_of_beds") Integer numberOfBeds
            , @RequestParam("cost_per_hour") Integer costPerHour
            , @RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size
            , @RequestParam(value = "sort", defaultValue = "roomId") String sortBy) {

        Room room = roomService.roomFactory(null, true, numberOfBeds, costPerHour, StatusRoom.CLEAN);
        roomService.addRoom(room);

        List<RoomDTO> roomsDTO = DTOUtil.createRoomsDTO(roomService.getRooms(page - 1, size, sortBy));
        ModelUtil.setStandardModelElements(model, page, size, sortBy, (int) (Math.ceil(roomService.getCountRecords() * 1.0 / size)), "rooms");

        model.addAttribute("rooms", roomsDTO);
        return "rooms";
    }

    @RequestMapping(value = "/edit/room", params = {"id"}, method = RequestMethod.GET)
    public String addRoom(Model model, @RequestParam("id") Long roomId) {

        RoomDTO roomDTO = DTOUtil.creteRoomDTO(roomService.getRoom(roomId));
        List<EquipmentDTO> equipmentDTOList = new ArrayList<>();
        equipmentService.getAllEquipments().forEach(equipment -> {
            if (equipment.getRoom().getRoomId().equals(warehouseId) || equipment.getRoom().getRoomId().equals(roomId)) {
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
        model.addAttribute("warehouseId", warehouseId);
        return "addroom";
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public String getRooms(Model model, @RequestParam(value = "room_id", defaultValue = "") Long room_id
            , @RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size
            , @RequestParam(value = "sort", defaultValue = "roomId") String sortBy) {

        List<RoomDTO> roomsDTO = DTOUtil.createRoomsDTO(roomService.getRooms(page - 1, size, sortBy));
        if (room_id != null) {
            roomService.removeRoom(room_id);
        }
        ModelUtil.setStandardModelElements(model, page, size, sortBy, (int) (Math.ceil(roomService.getCountRecords() * 1.0 / size)), "rooms");
        model.addAttribute("rooms", roomsDTO);
        return "rooms";
    }

    @ResponseBody
    @RequestMapping(value = "/addrooms")
    public String addRooms() {

        sender.sendMessage("queue.in", "getAllRooms");
        return "addrooms";
    }
}
