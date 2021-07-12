package org.azati.cources.controllers;

import org.azati.cources.dto.RoomDTO;
import org.azati.cources.services.RoomService;
import org.azati.cources.utils.UtilsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
public class RoomController {

    @Autowired
    RoomService roomService;

    @RequestMapping(value = "/getroom", params = {"id"}, method = RequestMethod.GET)
    public String getRoom(Model model, @RequestParam(value = "id") Long room_id) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO = UtilsDTO.creteRoomDTO(roomService.getRoom(room_id));
        model.addAttribute("room", roomDTO);
        return "getroom";
    }

}
