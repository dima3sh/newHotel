package org.azati.cources.controllers;

import org.azati.cources.entity.Room;
import org.azati.cources.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoomController {

    @Autowired
    RoomService roomService;

    @ResponseBody
    @RequestMapping("/getroom/{id}")
    public Room getRoom(@PathVariable Long id) {
        return roomService.getRoom(id);
    }

    /*@ResponseBody
    @RequestMapping("/addRoom/{number_of_beds}")
    public Optional<Room> addRoom(@PathVariable Integer number_of_beds) {
        Room room = new Room();
    }*/
}
