package ru.tsystemsverificationwork.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tsystemsverificationwork.web.models.Good;
import ru.tsystemsverificationwork.web.services.GoodsService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GoodsController {


    private GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @RequestMapping(value="/admin/creategood", method= RequestMethod.GET)
    public String createGoodGet(Model model) {

        model.addAttribute("good", new Good());
        return "admin/creategood";
    }


    @RequestMapping(value="/admin/creategood", method= RequestMethod.POST)
    public String createGood(Model model, @Valid Good good, BindingResult bindingResult) {


        if(bindingResult.hasErrors()) {
            return "admin/creategood";
        }

        goodsService.createGood(good);
        return "admin/goodcreated";
    }

    @RequestMapping(value = "/goods/{pageid}", method = RequestMethod.GET)
    public String getPaginatorGoods(Model model, @PathVariable int pageid) {



        List<Good> goods = goodsService.getGoodsPage(pageid, 10);
        int numberOfPages = (int) Math.ceil(goodsService.goodsSize()/10.0);


        model.addAttribute("goods", goods);
        model.addAttribute("numberOfPages", numberOfPages);

        return "goods";
    }
}
