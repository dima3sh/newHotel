package org.azati.cources.controllers;

import org.azati.cources.entity.Guest;
import org.azati.cources.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.Month;

@Controller
public class GuestController {

    @Autowired
    GuestService guestService;

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
    public Guest newGuest(@PathVariable String name) {
        LocalDateTime date = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
        Guest guest = new Guest(name, "123234", "dd@gmail", 2, date, date);
        guestService.add(guest);
        return guest;
    }
}
