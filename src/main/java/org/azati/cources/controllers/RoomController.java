package org.azati.cources.controllers;

import org.azati.cources.dto.RoomDTO;
import org.azati.cources.dictionaries.DirectoryStatus;
import org.azati.cources.entity.Room;
import org.azati.cources.jms.Sender;
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
import java.util.List;

@Controller
public class RoomController {

    public static Logger log = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomService roomService;

    @Autowired
    private Sender sender;

    @ResponseBody
    @RequestMapping(value = "/newroom", params = {"id"}, method = RequestMethod.GET)
    public String addRoom(Model model, @RequestParam(value = "id") Long roomId) {
        DirectoryStatus directoryStatus = new DirectoryStatus();
        directoryStatus.setLinkId(1);
        Room room = new Room();
        room.setDirectoryStatus(directoryStatus);
        room.setRoomId(roomId);
        room.setFreeRoom(false);
        room.setCostPerHour(12);
        room.setNumberOfBeds(2);
        roomService.addRoom(room);

        return "add room successful";
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
