package ru.tsystemsverificationwork.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.tsystemsverificationwork.web.editors.CategoryEditor;
import ru.tsystemsverificationwork.database.models.Category;
import ru.tsystemsverificationwork.database.models.Good;
import ru.tsystemsverificationwork.web.services.impl.GoodsService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GoodsController {


    private GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, new CategoryEditor());
    }

    @RequestMapping(value = "/admin/creategood", method = RequestMethod.GET)
    public String createGoodGet(Model model) {

        model.addAttribute("good", new Good());
        return "admin/creategood";
    }



    @RequestMapping(value = "/admin/creategood", method = RequestMethod.POST)
    public String createGood(Model model, @Valid Good good, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return "admin/creategood";
        }

        goodsService.createGood(good);
        return "admin/goodcreated";
    }

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public String getPaginatorGoods(Model model, @RequestParam(required = false) Integer pageid,
                                    @RequestParam(required = false) String brand,
                                    @RequestParam(required = false) String colour) {

        List<Good> goods;
        if (brand != null || colour != null) {
            goods = goodsService.search(brand, colour);
            model.addAttribute("goods", goods);
            return "goods";
        }

        goods = goodsService.getGoodsPage(pageid, 10);
        int numberOfPages = (int) Math.ceil(goodsService.goodsSize() / 10.0);

        model.addAttribute("goods", goods);
        model.addAttribute("numberOfPages", numberOfPages);

        return "goods";
    }

}
