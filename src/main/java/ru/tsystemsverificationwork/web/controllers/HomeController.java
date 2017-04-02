package ru.tsystemsverificationwork.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome() {

        return "home";
    }

    @RequestMapping(value = "/admin/admin")
    public String showAdmin() {

        return "admin/admin";
    }
}
