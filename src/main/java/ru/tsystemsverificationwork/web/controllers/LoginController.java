package ru.tsystemsverificationwork.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tsystemsverificationwork.database.models.Client;
import ru.tsystemsverificationwork.web.services.impl.ClientsService;

import javax.validation.Valid;


@Controller
public class LoginController {

    private ClientsService clientsService;

    @Autowired
    public LoginController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @RequestMapping(value = "/login")
    public String showLogin() {

        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logOut() {

        return "logout";
    }

    @RequestMapping(value = "/account/createaccount", method = RequestMethod.GET)
    public String createAccount(Model model, Client client) {


        model.addAttribute("Client", new Client());

        return "account/createaccount";
    }

    @RequestMapping(value = "/account/createaccount", method = RequestMethod.POST)
    public String createAccount(Model model, @Valid Client good, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "account/createaccount";
        }

        if (clientsService.clientExists(good.getEmail())) {
            bindingResult.rejectValue("email", "DublicateEmail.client.email");
            return "account/createaccount";
        }

        clientsService.createAccount(good);
        return "account/accountcreated";
    }


}
