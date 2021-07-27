package org.azati.cources.controllers;

import org.azati.cources.dto.GuestDTO;
import org.azati.cources.services.ChronoService;
import org.azati.cources.services.GuestService;
import org.azati.cources.utils.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    GuestService guestService;

    @Autowired
    ChronoService chronoService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model
            , @RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size
            , @RequestParam(value = "sort", defaultValue = "guestId") String sortBy) {
        List<GuestDTO> firstList = new ArrayList<>();
        guestService.getGuestNeedFree(1, 0).forEach(guest -> firstList.add(DTOUtil.createGuestDTO(guest)));
        List<GuestDTO> secondList = new ArrayList<>();
        guestService.getGuestNeedFree(12, 1).forEach(guest -> secondList.add(DTOUtil.createGuestDTO(guest)));
        firstList.forEach(guestDTO -> secondList.removeIf(guest -> guest.getGuestId() == guestDTO.getGuestId()));
        model.addAttribute("firstList", firstList);
        model.addAttribute("secondList", secondList);
        model.addAttribute("page", page);
        model.addAttribute("location", "index");
        model.addAttribute("size", size);
        model.addAttribute("sort", sortBy);
        return "index";
    }
}
