package org.azati.cources.controllers;

import org.azati.cources.dto.GuestDTO;
import org.azati.cources.services.ChronoService;
import org.azati.cources.services.GuestService;
import org.azati.cources.utils.DTOUtil;
import org.azati.cources.utils.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        List<GuestDTO> firstList = DTOUtil.createGuestsDTO(guestService.getGuestNeedFree(1, 0));
        List<GuestDTO> secondList = DTOUtil.createGuestsDTO(guestService.getGuestNeedFree(12, 1));
        firstList.forEach(guestDTO -> secondList.removeIf(guest -> guest.getGuestId().equals(guestDTO.getGuestId())));
        ModelUtil.setStandardModelElements(model, page, size, sortBy, (int) (Math.ceil(guestService.getCountRecords() * 1.0 / size)), "index");
        model.addAttribute("firstList", firstList);
        model.addAttribute("secondList", secondList);
        return "index";
    }
}
