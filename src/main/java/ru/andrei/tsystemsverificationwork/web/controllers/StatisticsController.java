package ru.andrei.tsystemsverificationwork.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.andrei.tsystemsverificationwork.web.services.impl.StatisticsService;


@Controller
public class StatisticsController {

    private StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @RequestMapping(value = "/admin/toptengoods")
    public String showTopTenSales(Model model) {

        model.addAttribute("topGoods", statisticsService.topTenGoods());
        return "admin/toptengoods";
    }

    @RequestMapping(value = "/admin/toptenclients")
    public String showTopTenClients(Model model) {

        model.addAttribute("topClients", statisticsService.topTenClients());
        return "admin/toptenclients";
    }

    @RequestMapping(value = "/admin/income")
    public String income(Model model) {

        model.addAttribute("income", statisticsService.getIncome());

        return "admin/income";
    }
}
