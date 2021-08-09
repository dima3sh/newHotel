package org.azati.cources.controllers;

import org.azati.cources.entity.AppUser;
import org.azati.cources.enums.UserRoles;
import org.azati.cources.services.UserService;
import org.azati.cources.utils.ModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StaffController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/staff", method = RequestMethod.GET)
    public String getRooms(Model model, @RequestParam(value = "user_id", defaultValue = "") Long userId
            , @RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size
            , @RequestParam(value = "sort", defaultValue = "userId") String sortBy) {
        if(userId != null) {

        }
        List<AppUser> users = userService.getAllUsers()
                .stream()
                .filter(user -> user.getUserRole().getUserRole() != UserRoles.CHIEF).collect(Collectors.toList());
        model.addAttribute("users", users);
        ModelUtil.setStandardModelElements(model, page, size, sortBy, (int) (Math.ceil(userService.getCountRecords() * 1.0 / size)), "staff");
        return "staff";
    }
}
