package org.azati.cources.controllers;


import net.sf.jasperreports.engine.JRException;
import org.azati.cources.dto.GuestDTO;
import org.azati.cources.dto.RoomDTO;
import org.azati.cources.entity.Guest;

import org.azati.cources.entity.Room;
import org.azati.cources.repository.GuestRepository;
import org.azati.cources.services.GuestService;
import org.azati.cources.services.RoomService;
import org.azati.cources.utils.DTOUtil;
import org.azati.cources.utils.ReportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


@Controller
public class GuestController {

    public static Logger log = LoggerFactory.getLogger(GuestRepository.class);

    @Autowired
    GuestService guestService;

    @Autowired
    RoomService roomService;

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @ResponseBody
    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "hello " + name;
    }

    @RequestMapping("/newguest")
    public String newGuest(Model model) {
        List<RoomDTO> roomsDTO = new ArrayList<>();
        roomService.getAllFreeRoom().forEach(room -> roomsDTO.add(DTOUtil.creteRoomDTO(room)));
        model.addAttribute("rooms", roomsDTO);
        return "newguest";
    }

    @ResponseBody
    @RequestMapping("/getguest/{guest_id}")
    public Guest getGuest(@PathVariable Long guest_id) {
        return guestService.getGuest(guest_id);
    }

    @ResponseBody
    @RequestMapping("/getguests/{name}")
    public List<Guest> getGuests(@PathVariable String name) {
        return guestService.getGuests(name);
    }

    @RequestMapping(value = "/guests")
    public String guests(Model model) {
        log.info("path : /guests ; print information guests");
        List<GuestDTO> guestsDTO = new ArrayList<>();
        guestService.getGuests().forEach(guest -> guestsDTO.add(DTOUtil.createGuestDTO(guest)));
        model.addAttribute("guests", guestsDTO);
        return "guests";
    }

    @RequestMapping(value = "/guests", params = {"guest_id"}, method = RequestMethod.GET)
    public String guests(Model model, @RequestParam(value = "guest_id") Long guestId) {
        log.info("path : /guests ; print information guests");
        List<GuestDTO> guestsDTO = new ArrayList<>();
        roomService.updateFreeRoomStatus(guestService.getGuest(guestId).getGuestRoomId().getRoomId(),true);
        guestService.removeGuest(guestId);
        guestService.getGuests().forEach(guest -> guestsDTO.add(DTOUtil.createGuestDTO(guest)));
        model.addAttribute("guests", guestsDTO);
        return "guests";
    }

    @RequestMapping(value = "/newguest", params = {"name", "phone", "email", "room_id", "time_in", "time_out"},
            method = RequestMethod.GET )
    public String addGuest(Model model, @RequestParam(value = "name") String name,
                           @RequestParam(value = "phone") String phone, @RequestParam(value = "email") String email,
                           @RequestParam(value = "room_id") Long roomId,
                           @RequestParam ("time_in") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeIn,
                           @RequestParam ("time_out") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeOut) {
        log.info("path : /guests ; print information guests");
        Guest newGuest = new Guest();
        newGuest.setName(name);
        newGuest.setEmailAddress(email);
        newGuest.setPhoneNumber(phone);
        Room room = new Room();
        room.setRoomId(roomId);
        newGuest.setGuestRoomId(room);
        newGuest.setDepartureTime(timeOut);
        newGuest.setArrivalTime(timeIn);
        newGuest.setInvoice(new Integer(0));
        guestService.addGuest(newGuest);
        roomService.updateFreeRoomStatus(roomId, false);
        List<GuestDTO> guestsDTO = new ArrayList<>();
        guestService.getGuests().forEach(guest -> guestsDTO.add(DTOUtil.createGuestDTO(guest)));
        model.addAttribute("guests", guestsDTO);
        return "guests";
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

    @RequestMapping(value = "/guest", params = {"id"} , method = RequestMethod.GET)
    public String getRooms(Model model, @RequestParam(value = "id") Long guestId) {
        log.info("path : /guest ; print information about guest");

        model.addAttribute("guest", guestService.getGuest(guestId));
        return "guest";
    }

    @ResponseBody
    @RequestMapping(value = "/report/guest", params = {"id"} , method = RequestMethod.GET)
    public String getReportGuest(@RequestParam(value = "id") Long guestId) {
        log.info("path : /guest ; print information about guest");
        try {
            ReportUtil.createPDFReport(guestService.getGuest(guestId), "hello");
        }catch (JRException e) {
            log.info("JRException with stackTrace: " + e.getStackTrace());
        }
        return "report create";
    }

}
