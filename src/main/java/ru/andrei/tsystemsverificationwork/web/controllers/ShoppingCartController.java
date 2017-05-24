package ru.andrei.tsystemsverificationwork.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.andrei.tsystemsverificationwork.database.models.Order;
import ru.andrei.tsystemsverificationwork.web.services.impl.ShoppingCartService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller responsible for cart
 */
@Controller
@SessionAttributes("cart")
public class ShoppingCartController {

    /**
     * Business logic of cart
     */
    private ShoppingCartService shoppingCartService;
    /**
     * Slf4j logger
     */
    private static final Logger log = LoggerFactory.getLogger(ShoppingCartController.class);
    /**
     * Name of cart size attribute
     */
    private static final String CART_SIZE = "cartSize";
    /**
     * Name of cart attribute
     */
    private static final String CART = "cart";
    /**
     * Name of cart view
     */
    private static final String CART_VIEW = "cart";

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    /**
     * Returns value of cart size
     *
     * @param session session of current user
     * @return cart size
     */
    @RequestMapping(value = "/renewcart")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public String renewCart(HttpSession session) {

        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute(CART);

        if (cart == null)
            return "0";

        return Integer.toString(cart.size());
    }

    private void verifyQuantityAndLog(Integer goodId, Integer quantity) {
        shoppingCartService.verifyQuantity(goodId, quantity);
        log.info("Item id {} is added to cart, quantity: {}", goodId, quantity);
    }

    /**
     * Handles adding good to the cart
     *
     * @param session  session of the current user
     * @param goodId   id of the item is going to be added to cart
     * @param quantity quantity of items
     * @return returns good's jsp view
     */
    @RequestMapping(value = "/buygood", method = RequestMethod.POST)
    @SuppressWarnings("unchecked")
    public String addItemToCard(HttpSession session, @RequestParam Integer goodId,
                                @RequestParam Integer quantity) {

        if (session.getAttribute(CART) == null) {

            HashMap<Integer, Integer> cart = new HashMap<>();
            verifyQuantityAndLog(goodId, quantity);
            cart.put(goodId, quantity);
            session.setAttribute(CART, cart);
            session.setAttribute(CART_SIZE, 1);
        } else {

            HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>) session.getAttribute(CART);
            verifyQuantityAndLog(goodId, quantity);
            cart.put(goodId, quantity);
            session.setAttribute(CART, cart);
            session.setAttribute(CART_SIZE, cart.size());
        }

        return "goods";
    }

    /**
     * Returns view of the cart
     *
     * @param session            session of current user logged on
     * @param model              view attributes
     * @param deleteItemFromCart id of item is going to be deleted from cart
     * @return jsp view of the cart
     */
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    @SuppressWarnings("unchecked")
    public String shoppingCart(HttpSession session, Model model,
                               @RequestParam(required = false) Integer deleteItemFromCart) {

        if (deleteItemFromCart != null && session.getAttribute(CART) != null) {
            HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>) session.getAttribute(CART);
            cart.remove(deleteItemFromCart);
            session.setAttribute(CART, cart);
            session.setAttribute(CART_SIZE, cart.size());
        }

        if (session.getAttribute(CART) == null) {
            model.addAttribute(CART_SIZE, 0);
            return CART_VIEW;
        } else {
            model.addAttribute("goods", shoppingCartService.showCartItems(
                    (Map<Integer, Integer>) session.getAttribute(CART))
            );

            model.addAttribute("order", new Order());

            if (SecurityContextHolder.getContext().getAuthentication() != null &&
                    SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                    !(SecurityContextHolder.getContext().getAuthentication()
                            instanceof AnonymousAuthenticationToken))
                model.addAttribute("clientAddresses", shoppingCartService.getClientAddresses());

        }

        return CART_VIEW;
    }


}
