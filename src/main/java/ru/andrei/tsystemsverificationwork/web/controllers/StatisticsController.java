package ru.andrei.tsystemsverificationwork.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.andrei.tsystemsverificationwork.web.services.impl.StatisticsService;

/**
 * Controller responsible for statistics
 */
@Controller
public class StatisticsController {

    /**
     * Business logic of statistics
     */
    private StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    /**
     * Returns the most popular goods buyed
     *
     * @param model view attributes
     * @return jsp view of statistic
     */
    @RequestMapping(value = "/admin/toptengoods")
    public String showTopTenSales(Model model) {

        model.addAttribute("topGoods", statisticsService.topTenGoods());
        return "admin/toptengoods";
    }

    /**
     * Returns the most active clients
     *
     * @param model view attributes
     * @return jsp view of top clients
     */
    @RequestMapping(value = "/admin/toptenclients")
    public String showTopTenClients(Model model) {

        model.addAttribute("topClients", statisticsService.topTenClients());
        return "admin/toptenclients";
    }

    /**
     * Returns month/week income
     *
     * @param model view attributes
     * @return jsp view with statistics
     */
    @RequestMapping(value = "/admin/income")
    public String income(Model model) {

        model.addAttribute("income", statisticsService.getIncome());

        return "admin/income";
    }
}
