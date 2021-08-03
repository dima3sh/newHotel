package org.azati.cources.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class AuthorizationController {

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String index(Model model) {

        return "login";
    }

    @RequestMapping(value = {"/login"}, params = {"username", "password"}, method = RequestMethod.POST)
    public String login(Model model, @RequestParam("username") String username, @RequestParam("password") String password) {

        return "index";
    }

    @RequestMapping(value = {"/perform_logout"})
    public String logout(Model model) {

        return "index";
    }
}
