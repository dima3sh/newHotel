package org.azati.cources.controllers;

import org.azati.cources.entity.Guest;
import org.azati.cources.entity.Room;
import org.azati.cources.services.GuestService;
import org.azati.cources.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Controller
public class GuestController {

    @Autowired
    GuestService guestService;

    @Autowired
    RoomService roomService;

    @ResponseBody
    @RequestMapping("/")
    public String welcome() {
        return guestService.getClass().getSimpleName();
    }

    @ResponseBody
    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "hello " + name;
    }

    @ResponseBody
    @RequestMapping("/newguest/{name}")
    public String newGuest(@PathVariable String name) {
        LocalDateTime date = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
        Guest guest = new Guest();
        guest.setName(name);
        guest.setRoom_id(roomService.getRoom(1L));
        guest.setPhoneNumber("123456");
        guest.setEmailAddress("friend@gmail.com");
        guest.setArrivalTime(LocalDateTime.now());
        guest.setDepartureTime(LocalDateTime.now());
        guest.setInvoice(0);
        guestService.addGuest(guest);
        return date.toString();
    }

    @ResponseBody
    @RequestMapping("/getguest/{guest_id}")
    public Optional<Guest> getGuest(@PathVariable Long guest_id) {
        return guestService.getGuest(guest_id);
    }

    @ResponseBody
    @RequestMapping("/getguests/{name}")
    public List<Guest> getGuests(@PathVariable String name) {
        return guestService.getGuests(name);
    }

    @ResponseBody
    @RequestMapping("/deleteguest/{phone}")
    public Boolean getGuest(@PathVariable String phone) {
        return guestService.removeGuest(phone) == 1;
    }

    @ResponseBody
    @RequestMapping(value = "/updateguestinvoice/{oldInvoice}", params = {"invoice"})
    public List<Guest> updateGuestName(@PathVariable Integer oldInvoice,
                                       @RequestParam(value = "invoice") Integer invoice){
        return guestService.updateInvoice(oldInvoice, invoice);
    }

}
