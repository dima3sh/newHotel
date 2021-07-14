package org.azati.cources.controllers;

import org.azati.cources.dto.RoomDTO;
import org.azati.cources.entity.Room;
import org.azati.cources.enums.StatusRoom;
import org.azati.cources.services.RoomService;
import org.azati.cources.utils.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @ResponseBody
    @RequestMapping(value = "/newroom", params = {"id"}, method = RequestMethod.GET)
    public String addRoom(Model model, @RequestParam(value = "id") Long roomId) {
        Room room = new Room ();
        room.setStatusRoom(StatusRoom.clean);
        room.setRoomId(roomId);
        room.setFreeRoom(false);
        room.setCostPerHour(12);
        room.setNumberOfBeds(2);
        roomService.addRoom(room);
        return "add room successful";
    }

    @RequestMapping(value = "/getroom", params = {"id"}, method = RequestMethod.GET)
    public String getRoom(Model model, @RequestParam(value = "id") Long room_id) {
        RoomDTO roomDTO = DTOUtil.creteRoomDTO(roomService.getRoom(room_id));
        model.addAttribute("room", roomDTO);
        return "getroom";
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public String getRooms(Model model) {
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
