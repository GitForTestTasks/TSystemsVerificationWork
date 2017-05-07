package ru.andrei.tsystemsverificationwork.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.andrei.tsystemsverificationwork.database.models.ClientAddress;
import ru.andrei.tsystemsverificationwork.database.models.Order;
import ru.andrei.tsystemsverificationwork.web.editors.ClientAddressEditor;
import ru.andrei.tsystemsverificationwork.web.services.impl.OrdersService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

/**
 * Controller responsible for orders
 */
@Controller
public class OrdersController {

    /**
     * View attribute orders
     */
    private static final String ORDERS = "orders";
    /**
     * Variable is used for determining what quantity of results per page appear
     */
    private static final double ORDERS_PER_PAGE = 10.0;
    /**
     * Business logic of orders
     */
    private OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    /**
     * Method binds string value of id to long value of client address id
     *
     * @param binder object of ClientAddressEditor
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(ClientAddress.class, new ClientAddressEditor());
    }

    /**
     * Handles all changes happening with good entity and returns result
     *
     * @param model         view attributes
     * @param session       server session scope
     * @param order         order to be changed
     * @param bindingResult returning results
     * @return jsp view of success if operation is done
     */
    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    @SuppressWarnings("unchecked")
    public String createOrder(Model model, HttpSession session, @Valid Order order, BindingResult bindingResult) {

        if (session.getAttribute("cart") == null || order == null
                || bindingResult.hasErrors()) {
            return "cart";
        }

        if (order.getClientAddressId() == null)
            return "account/clientaddresses";

        ordersService.createOrder((Map<Integer, Integer>) session.getAttribute("cart"),
                order.getPaymentMethod(), order.getDeliveryMethod(), order.getClientAddressId());
        session.removeAttribute("cart");
        session.setAttribute("cartSize", 0);
        model.addAttribute(ORDERS, ordersService.getMyOrders());

        return "account/orders";
    }

    /**
     * Returns orders of current logged user
     *
     * @param model view attributes
     * @return jsp view
     */
    @RequestMapping(value = "/account/orders", method = RequestMethod.GET)
    public String showOrders(Model model) {

        model.addAttribute(ORDERS, ordersService.getMyOrders());

        return "account/orders";
    }

    /**
     * Returns info of requested order by id
     *
     * @param model   view attributes
     * @param orderId id to be found and returned
     * @return jsp view with detailed information about order
     */
    @RequestMapping(value = "/account/orderinfo")
    public String orderIfno(Model model, @RequestParam(required = true) Long orderId) {

        if (orderId != null && ordersService.verificateRequestedOrder(orderId))
            returnEditOrderView(model, orderId);

        return "account/orderinfo";
    }

    /**
     * Returns paged list of all orders
     *
     * @param model      view attributes
     * @param pageNumber number of page is going to be returned
     * @return jsp view with orders on it
     */
    @RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
    public String adminOrders(Model model,
                              @RequestParam(required = false) Integer pageNumber) {

        int numberOfPages = (int) Math.ceil(ordersService.orderSize() / ORDERS_PER_PAGE);

        if (pageNumber == null) {
            model.addAttribute(ORDERS, ordersService.getAdminPagedOrders(1, (int) ORDERS_PER_PAGE));
            model.addAttribute("numberOfPages", numberOfPages);
            return "admin/orders";
        }

        model.addAttribute(ORDERS, ordersService.getAdminPagedOrders(pageNumber, (int) ORDERS_PER_PAGE));
        model.addAttribute("numberOfPages", numberOfPages);

        return "admin/orders";
    }

    /**
     * Returns view with an opportunity to change its status
     *
     * @param model   view attributes
     * @param orderId order id of order is going to be changed
     * @return jsp view editorder
     */
    @RequestMapping(value = "/admin/editorder", method = RequestMethod.GET)
    public String editOrders(Model model, @RequestParam(required = false) Long orderId) {

        returnEditOrderView(model, orderId);

        return "admin/editorder";
    }

    /**
     * Handles post request with changed order
     *
     * @param model view attributes
     * @param order order that is going to be changed
     * @return jsp view with results
     */
    @RequestMapping(value = "/admin/editorder", method = RequestMethod.POST)
    public String editOrders(Model model, Order order) {

        ordersService.updateOrderStatus(order.getOrderStatus(), order.getOrderId());
        returnEditOrderView(model, order.getOrderId());
        model.addAttribute("success", true);

        return "admin/editorder";
    }

    private void returnEditOrderView(Model model, Long orderId) {

        model.addAttribute("order", ordersService.getOrder(orderId));
        model.addAttribute("goods", ordersService.getRelatedGoods(orderId));
        model.addAttribute("address", ordersService.getAddressByOrderId(orderId));
    }
}
