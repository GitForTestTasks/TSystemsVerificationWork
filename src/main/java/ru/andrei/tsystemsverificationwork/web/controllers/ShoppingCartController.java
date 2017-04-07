package ru.andrei.tsystemsverificationwork.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.andrei.tsystemsverificationwork.database.models.Order;
import ru.andrei.tsystemsverificationwork.web.services.impl.ShoppingCartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
@SessionAttributes("cart")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @RequestMapping(value = "/renewcart")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public String renewCart(HttpSession session) {
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

        if (cart == null)
            return "0";

        return Integer.toString(cart.size());
    }

    @RequestMapping(value = "/buygood", method = RequestMethod.GET)
    @SuppressWarnings("unchecked")
    public String addItemToCard(HttpSession session, @RequestParam Integer goodId,
                                @RequestParam Integer quantity) {

        if (session.getAttribute("cart") == null) {

            Map<Integer, Integer> cart = new HashMap<>();
            shoppingCartService.verifyQuantity(goodId, quantity);
            cart.put(goodId, quantity);
            session.setAttribute("cart", cart);
            session.setAttribute("cartSize", 1);
        } else {

            Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
            shoppingCartService.verifyQuantity(goodId, quantity);
            cart.put(goodId, quantity);
            session.setAttribute("cart", cart);
            session.setAttribute("cartSize", cart.size());
        }

        return "goods";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    @SuppressWarnings("unchecked")
    public String shoppingCart(HttpSession session, Model model,
                               @RequestParam(required = false) Integer deleteItemFromCart,
                               HttpServletRequest request) {

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

            model.addAttribute("order", new Order());

            if (SecurityContextHolder.getContext().getAuthentication() != null &&
                    SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                    !(SecurityContextHolder.getContext().getAuthentication()
                            instanceof AnonymousAuthenticationToken))
                model.addAttribute("clientAddresses", shoppingCartService.retriveAllAddresses());

        }

        return "cart";
    }


}
