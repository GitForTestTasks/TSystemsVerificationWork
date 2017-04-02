package ru.tsystemsverificationwork.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tsystemsverificationwork.database.models.ClientAddress;
import ru.tsystemsverificationwork.database.models.Order;
import ru.tsystemsverificationwork.web.editors.ClientAddressEditor;
import ru.tsystemsverificationwork.web.services.impl.OrdersService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class OrdersController {

    private final double ORDERS_PER_PAGE = 10.0;

    private OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(ClientAddress.class, new ClientAddressEditor());
    }

    @RequestMapping(value = "/admin/orders")
    public String getPagedOrders() {

        return "admin/orders";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public String createOrder(Model model, HttpSession session, @Valid Order order, BindingResult bindingResult) {

        if (session.getAttribute("cart") == null || order == null || order.getClientAddressId() == null
                || bindingResult.hasErrors()) {
            return "cart";
        }

        ordersService.createOrder((Map<Integer, Integer>) session.getAttribute("cart"),
                order.getPaymentMethod(), order.getDeliveryMethod(), order.getClientAddressId());
        session.setAttribute("cart", new HashMap<>());
        session.setAttribute("cartSize", 0);
        model.addAttribute("success", true);

        return "cart";
    }

    @RequestMapping(value = "/account/orders", method = RequestMethod.GET)
    public String showOrders(Model model) {

        model.addAttribute("orders", ordersService.getMyOrders());

        return "account/orders";
    }

    @RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
    public String adminOrders(Model model,
                              @RequestParam(required = false) Integer pageid) {

        int numberOfPages = (int) Math.ceil(ordersService.orderSize() / ORDERS_PER_PAGE);

        if (pageid == null) {
            model.addAttribute("orders", ordersService.getAdminPagedOrders(1, (int) ORDERS_PER_PAGE));
            model.addAttribute("numberOfPages", numberOfPages);
            return "admin/orders";
        }

        model.addAttribute("orders", ordersService.getAdminPagedOrders(pageid, (int) ORDERS_PER_PAGE));
        model.addAttribute("numberOfPages", numberOfPages);

        return "admin/orders";
    }
}
