package ru.tsystemsverificationwork.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tsystemsverificationwork.web.models.Client;
import ru.tsystemsverificationwork.web.models.ClientAddress;
import ru.tsystemsverificationwork.web.services.ProfileService;

import javax.validation.Valid;
import java.util.logging.Level;
import java.util.logging.Logger;


@Controller
public class ProfileController {

    private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @RequestMapping(value = "/account/profile", method = RequestMethod.GET)
    public String viewProfile(Model model) {


        Client client = profileService.getCurrentUser();
        model.addAttribute("client", client);

        return "account/profile";
    }

    @RequestMapping(value = "/account/profile", method = RequestMethod.POST)
    public String editProfile(Model model, @Valid Client client, BindingResult result) {

        profileService.updateInformation(client);

        return "account/profile";
    }

    @RequestMapping(value = "/account/clientaddress", method = RequestMethod.GET)
    public String editClientAddress(Model model) {

        model.addAttribute("clientAddress", profileService.getClientAddress());

        return "account/clientaddress";

    }

    @RequestMapping(value = "/account/clientaddress", method = RequestMethod.POST)
    public String editClientAddress(Model model, @Valid ClientAddress clientAddress, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "account/clientaddress";
        }
        profileService.updateCliendAddress(clientAddress);
        model.addAttribute("success", true);

        return "account/clientaddress";
    }
}
