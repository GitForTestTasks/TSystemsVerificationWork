package ru.andrei.tsystemsverificationwork.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller responsible for home pages
 */
@Controller
public class HomeController {

    /**
     * Returns home view
     *
     * @return jsp home view
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome() {

        return "home";
    }

    /**
     * Returns admin view
     *
     * @return jsp admin view
     */
    @RequestMapping(value = "/admin/admin")
    public String showAdmin() {

        return "admin/admin";
    }
}
