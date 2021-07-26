package org.azati.cources.controllers;

import org.azati.cources.dto.GuestDTO;
import org.azati.cources.entity.Guest;
import org.azati.cources.repository.GuestRepository;
import org.azati.cources.services.ChronoService;
import org.azati.cources.services.GuestService;
import org.azati.cources.utils.DTOUtil;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;

@Controller
public class MainController {

    @Autowired
    GuestService guestService;

    @Autowired
    ChronoService chronoService;

    public static Logger log = LoggerFactory.getLogger(GuestRepository.class);

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        List<GuestDTO> firstList = new ArrayList<>();
        guestService.getGuestNeedFree(1, 0).forEach(guest -> firstList.add(DTOUtil.createGuestDTO(guest)));
        List<GuestDTO> secondList = new ArrayList<>();
        guestService.getGuestNeedFree(12, 1).forEach(guest -> secondList.add(DTOUtil.createGuestDTO(guest)));
        firstList.forEach(guestDTO -> {
            secondList.removeIf(guest -> guest.getGuestId() == guestDTO.getGuestId());
        });
        model.addAttribute("firstList", firstList);
        model.addAttribute("secondList", secondList);


        return "index";
    }
}
