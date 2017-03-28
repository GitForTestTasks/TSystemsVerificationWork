package ru.tsystemsverificationwork.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tsystemsverificationwork.web.models.Client;
import ru.tsystemsverificationwork.web.services.ClientsService;


import java.util.List;


@Controller
public class ClientsController {

    private ClientsService clientsService;

    @Autowired
    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @RequestMapping(value="/clients", method= RequestMethod.GET)
    public String showClients(Model model) {


        List<Client> clientList = clientsService.getCurrent();

        model.addAttribute("clientList", clientList);

        return "clients";
    }

    @RequestMapping(value="/createclient", method= RequestMethod.GET)
        public String createClient(Model model) {

        return "createclient";
    }


}
