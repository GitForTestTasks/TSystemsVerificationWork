package ru.tsystemsverificationwork.web.controllers;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tsystemsverificationwork.web.models.Good;
import ru.tsystemsverificationwork.web.models.Order;
import ru.tsystemsverificationwork.web.models.enums.PaymentMethod;
import ru.tsystemsverificationwork.web.services.ShoppingCartService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Andrei on 3/29/2017.
 */
@Controller
@SessionAttributes("cart")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(value = "/buygood", method = RequestMethod.GET)
    public String addItemToCard(HttpSession session, @RequestParam Integer goodId,
                                @RequestParam Integer quantity) {

        if (session.getAttribute("cart") == null) {

            Map<Integer, Integer> cart = new HashMap<>();
            cart.put(goodId, quantity);
            session.setAttribute("cart", cart);
            session.setAttribute("cartSize", 1);
        } else {

            Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

            Logger log = Logger.getLogger(ShoppingCartController.class.getName());
            log.log(Level.WARNING, cart.toString());
            cart.put(goodId, quantity);
            session.setAttribute("cart", cart);
            session.setAttribute("cartSize", cart.size());
        }

        return "goods";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String shoppingCart(HttpSession session, Model model,
                               @RequestParam(required = false) Integer deleteItemFromCart) {

        if (deleteItemFromCart != null && session.getAttribute("cart") != null) {
            Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
            cart.remove(deleteItemFromCart);
            session.setAttribute("cart", cart);
            session.setAttribute("cartSize", cart.size());
        }

        if (session.getAttribute("cart") == null)
            return "cart";
        else {
            model.addAttribute("goods", shoppingCartService.showCartItems(
                    (Map<Integer, Integer>) session.getAttribute("cart"))
            );
        }
        model.addAttribute("order", new Order());
        model.addAttribute("paymentMethod", PaymentMethod.values());

        return "cart";
    }

    @RequestMapping(value = "/createorder")
//    @Secured()
    public String createOrder() {


        return "account/orders";
    }

}