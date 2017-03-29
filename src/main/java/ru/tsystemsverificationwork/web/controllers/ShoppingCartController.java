package ru.tsystemsverificationwork.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Andrei on 3/29/2017.
 */
@Controller
public class ShoppingCartController {

    @RequestMapping(value = "/buygood")
    public String addItemToCard() {

        return null;
    }

}
