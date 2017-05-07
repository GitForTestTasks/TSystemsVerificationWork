package ru.andrei.tsystemsverificationwork.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.andrei.tsystemsverificationwork.database.models.Client;
import ru.andrei.tsystemsverificationwork.web.services.impl.ClientsService;

import javax.validation.Valid;

/**
 * Controller responsible for logging
 */
@Controller
public class LoginController {

    /**
     * Logging logic of users
     */
    private ClientsService clientsService;

    /**
     * View of create account form
     */
    private static final String CREATE_ACCOUNT = "account/createaccount";

    @Autowired
    public LoginController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    /**
     * Returns login form
     *
     * @return jsp view of logging form
     */
    @RequestMapping(value = "/login")
    public String showLogin() {

        return "login";
    }

    /**
     * Handles spring security logout
     *
     * @return transfers management to spring security
     */
    @RequestMapping(value = "/logout")
    public String logOut() {

        return "logout";
    }

    /**
     * Returns creating user's form
     *
     * @param model view attributes
     * @return jsp view of form
     */
    @RequestMapping(value = "/account/createaccount", method = RequestMethod.GET)
    public String createAccount(Model model) {


        model.addAttribute("Client", new Client());

        return CREATE_ACCOUNT;
    }

    /**
     * Handles logic creating good and returns result
     *
     * @param good          good to be created
     * @param bindingResult validation messages
     * @return result of operation
     */
    @RequestMapping(value = "/account/createaccount", method = RequestMethod.POST)
    public String createAccount(@Valid Client good, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return CREATE_ACCOUNT;
        }

        if (clientsService.clientExists(good.getEmail())) {
            bindingResult.rejectValue("email", "DublicateEmail.client.email");
            return CREATE_ACCOUNT;
        }

        clientsService.createAccount(good);
        return "account/accountcreated";
    }
}
