package ru.andrei.tsystemsverificationwork.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.andrei.tsystemsverificationwork.database.models.Client;
import ru.andrei.tsystemsverificationwork.database.models.ClientAddress;
import ru.andrei.tsystemsverificationwork.web.services.impl.ProfileService;

import javax.validation.Valid;

/**
 * Controller responsible for profile information
 */
@Controller
public class ProfileController {

    /**
     * Business logic related to profile
     */
    private ProfileService profileService;

    private static final String CLIENT_ADDRESS = "account/clientaddress";

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    /**
     * Returns profile view with form
     *
     * @param model view attributes
     * @return jsp view
     */
    @RequestMapping(value = "/account/profile", method = RequestMethod.GET)
    public String viewProfile(Model model) {

        Client client = profileService.getCurrentUser();
        model.addAttribute("client", client);

        return "account/profile";
    }

    /**
     * Handles post request to submit changes
     *
     * @param client client is going to be changed
     * @return jsp view with profile form
     */
    @RequestMapping(value = "/account/profile", method = RequestMethod.POST)
    public String editProfile(@Valid Client client) {

        profileService.updateInformation(client);

        return "account/profile";
    }

    /**
     * Returns jsp view with form of client address
     *
     * @param model           view attributes
     * @param clientAddressId address to appears
     * @return jsp view
     */
    @RequestMapping(value = "/account/clientaddress", method = RequestMethod.GET)
    public String editClientAddress(Model model,
                                    @RequestParam(required = false) Long clientAddressId) {

        if (clientAddressId == null)
            model.addAttribute("clientAddress", new ClientAddress());
        else
            model.addAttribute("clientAddress", profileService.getCalledClientAddress(clientAddressId));

        return CLIENT_ADDRESS;
    }

    /**
     * Handles submitted changes for client's address
     *
     * @param model         view attributes
     * @param clientAddress address is going to be changed
     * @param bindingResult binding results
     * @return view with result of the operation
     */
    @RequestMapping(value = "/account/clientaddress", method = RequestMethod.POST)
    public String editClientAddress(Model model, @Valid ClientAddress clientAddress, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return CLIENT_ADDRESS;
        }

        profileService.updateCliendAddress(clientAddress);
        model.addAttribute("success", true);

        return CLIENT_ADDRESS;
    }

    /**
     * Returns list of client's addresses
     *
     * @param model view attributes
     * @return jsp view
     */
    @RequestMapping(value = "/account/clientaddresses", method = RequestMethod.GET)
    public String showClientAddresses(Model model) {

        model.addAttribute("addresses", profileService.getClientAddresses());

        return "account/clientaddresses";
    }
}
