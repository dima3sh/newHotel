package org.azati.cources.controllers;


import org.azati.cources.dto.UserDTO;
import org.azati.cources.entity.AppUser;
import org.azati.cources.enums.UserRoles;
import org.azati.cources.services.UserService;
import org.azati.cources.utils.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Value("${warehouse.id}")
    private Long warehouseId;

    @RequestMapping(value = {"/edit/user"}, params = {"userId"}, method = RequestMethod.GET)
    public String editUser(Model model, @RequestParam("userId") Long userId) {
        AppUser user = userService.getUser(userId);
        UserDTO userDTO = DTOUtil.createUserDTO(user);
        model.addAttribute("user", userDTO);
        model.addAttribute("location", "staff");
        model.addAttribute("userRoles", UserRoles.values());
        return "staffEdit";
    }

    @PostMapping(value = {"/edit/user"})
    public String saveUserDetails(Model model, @RequestBody UserDTO userDto) {

        return "staffEdit";
    }

}
