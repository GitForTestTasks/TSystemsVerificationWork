package ru.tsystemsverificationwork.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tsystemsverificationwork.web.models.Client;
import ru.tsystemsverificationwork.web.services.ProfileService;

import javax.validation.Valid;


@Controller
public class ProfileController {

    private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @RequestMapping(value = "/account/profile")
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

}
