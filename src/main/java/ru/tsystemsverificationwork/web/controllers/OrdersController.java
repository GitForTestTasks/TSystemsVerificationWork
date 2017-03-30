package ru.tsystemsverificationwork.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrdersController {

    @RequestMapping(value = "/admin/orders")
    public String getPagedOrders() {

        return "admin/orders";
    }
}
