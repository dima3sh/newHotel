package org.azati.cources.controllers;


import net.sf.jasperreports.engine.JRException;
import org.azati.cources.dto.GuestDTO;
import org.azati.cources.dto.RoomDTO;
import org.azati.cources.entity.Guest;
import org.azati.cources.repository.GuestRepository;
import org.azati.cources.services.GuestService;
import org.azati.cources.services.RoomService;
import org.azati.cources.utils.DTOUtil;
import org.azati.cources.utils.ModelUtil;
import org.azati.cources.utils.ReportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class GuestController {

    public static Logger log = LoggerFactory.getLogger(GuestRepository.class);

    @Autowired
    GuestService guestService;

    @Autowired
    RoomService roomService;

    @Value("${warehouse.id}")
    private Long warehouseId;

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
        model.addAttribute("isEdit", false);
        model.addAttribute("warehouseId", warehouseId);
        return "newguest";
    }

    @RequestMapping(value = "/edit/guest", params = {"id"}, method = RequestMethod.GET)
    public String editGuest(Model model, @RequestParam(value = "id") Long guestId) {
        log.info("path : /guests ; print information guests");
        GuestDTO guestDTO = DTOUtil.createGuestDTO(guestService.getGuest(guestId));
        List<RoomDTO> roomsDTO = new ArrayList<>();
        roomService.getAllFreeRoom().forEach(room -> roomsDTO.add(DTOUtil.creteRoomDTO(room)));
        model.addAttribute("rooms", roomsDTO);
        model.addAttribute("guest", guestDTO);
        model.addAttribute("isEdit", true);
        model.addAttribute("warehouseId", warehouseId);
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

    @RequestMapping(value = "/guests",  method = RequestMethod.GET)
    public String guests(Model model, @RequestParam(value = "guest_id", defaultValue = "") Long guestId
            , @RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size
            , @RequestParam(value = "sort", defaultValue = "guestId") String sortBy) {

        List<GuestDTO> guestsDTO = new ArrayList<>();
        if(guestId != null) {
            roomService.updateFreeRoomStatus(guestService.getGuest(guestId).getGuestRoomId().getRoomId(), true);
            guestService.removeGuest(guestId);
        }
        guestService.getGuests(page - 1, size, sortBy).forEach(guest -> guestsDTO.add(DTOUtil.createGuestDTO(guest)));
        ModelUtil.setStandardModelElements(model, page, size, sortBy, (int)(Math.ceil(guestService.getCountRecords() * 1.0 / size)), "guests");
        model.addAttribute("guests", guestsDTO);
        return "guests";
    }

    @RequestMapping(value = "/newguest", params = {"name", "phone", "email", "room_id", "time_in", "time_out", "guest_id"},
            method = RequestMethod.GET)
    public String editGuest(Model model, @RequestParam(value = "name") String name
            , @RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size
            , @RequestParam(value = "sort", defaultValue = "guestId") String sortBy,
                            @RequestParam(value = "phone") String phone, @RequestParam(value = "email") String email,
                            @RequestParam(value = "room_id") Long roomId,
                            @RequestParam("time_in") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeIn,
                            @RequestParam("time_out") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeOut,
                            @RequestParam("guest_id") Long guestId) {
        Guest newGuest = guestService.guestFactory(name, email, phone, roomId, timeOut, timeIn, 0);
        newGuest.setGuestId(guestId);
        guestService.updateGuest(newGuest);
        roomService.updateFreeRoomStatus(guestService.getGuest(guestId).getGuestRoomId().getRoomId(), true);
        roomService.updateFreeRoomStatus(roomId, false);
        List<GuestDTO> guestsDTO = new ArrayList<>();
        guestService.getGuests(page - 1, size, sortBy).forEach(guest -> guestsDTO.add(DTOUtil.createGuestDTO(guest)));
        ModelUtil.setStandardModelElements(model, page, size, sortBy, (int)(Math.ceil(guestService.getCountRecords() * 1.0 / size)), "guests");
        model.addAttribute("guests", guestsDTO);
        return "guests";
    }

    @RequestMapping(value = "/newguest", params = {"name", "phone", "email", "room_id", "time_in", "time_out"},
            method = RequestMethod.GET)
    public String addGuest(Model model, @RequestParam(value = "name") String name
            , @RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size
            , @RequestParam(value = "sort", defaultValue = "guestId") String sortBy,
                           @RequestParam(value = "phone") String phone, @RequestParam(value = "email") String email,
                           @RequestParam(value = "room_id") Long roomId,
                           @RequestParam("time_in") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeIn,
                           @RequestParam("time_out") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeOut) {
        Guest newGuest = guestService.guestFactory(name, email, phone, roomId, timeOut, timeIn, 0);
        guestService.addGuest(newGuest);
        roomService.updateFreeRoomStatus(roomId, false);
        List<GuestDTO> guestsDTO = new ArrayList<>();
        guestService.getGuests(page - 1, size, sortBy).forEach(guest -> guestsDTO.add(DTOUtil.createGuestDTO(guest)));
        ModelUtil.setStandardModelElements(model, page, size, sortBy, (int)(Math.ceil(guestService.getCountRecords() * 1.0 / size)), "guests");
        model.addAttribute("guests", guestsDTO);
        return "guests";
    }

    @ResponseBody
    @RequestMapping(value = "/report/guest", params = {"id"}, method = RequestMethod.GET)
    public String getReportGuest(@RequestParam(value = "id") Long guestId) {
        log.info("path : /guest ; print information about guest");
        try {
            ReportUtil.createPDFReport(guestService.getGuest(guestId), "hello");
        } catch (JRException e) {
            log.info("JRException with stackTrace: " + Arrays.toString(e.getStackTrace()));
        }
        return "report create";
    }

}
