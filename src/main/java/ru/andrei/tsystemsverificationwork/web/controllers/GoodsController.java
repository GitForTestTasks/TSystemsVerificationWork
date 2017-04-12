package ru.andrei.tsystemsverificationwork.web.controllers;


import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.andrei.tsystemsverificationwork.database.models.Category;
import ru.andrei.tsystemsverificationwork.web.editors.CategoryEditor;
import ru.andrei.tsystemsverificationwork.database.models.Good;
import ru.andrei.tsystemsverificationwork.web.services.impl.CategoriesService;
import ru.andrei.tsystemsverificationwork.web.services.impl.GoodsService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Controller
public class GoodsController {

    private final static double GOODS_PER_PAGE = 10.0;
    private GoodsService goodsService;
    private CategoriesService categoriesService;


    @Autowired
    public GoodsController(GoodsService goodsService, CategoriesService categoriesService) {
        this.goodsService = goodsService;
        this.categoriesService = categoriesService;
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, new CategoryEditor());
    }

    @RequestMapping(value = "/admin/creategood", method = RequestMethod.GET)
    public String createGoodGet(Model model, @RequestParam(required = false) Long goodId) {

        if (goodId == null)
            model.addAttribute("good", new Good());
        else
            model.addAttribute("good", goodsService.getGoodById(goodId));
        return "admin/creategood";
    }

    @RequestMapping(value = "/admin/creategood", method = RequestMethod.POST)
    public String createGood(HttpSession session, Model model,
                             @Valid Good good, BindingResult bindingResult,
                             @RequestParam("file") MultipartFile file) {

        if (bindingResult.hasErrors()) {
            return "admin/creategood";
        }

        if (!file.isEmpty()) {
            String mimeType = session.getServletContext().getMimeType(file.getOriginalFilename());
            if (!mimeType.startsWith("image/")) {
                bindingResult.rejectValue("filePath", "FileIsNotImage.good.file");
                return "admin/creategood";
            }

            try {
                byte[] bytes = file.getBytes();

                String rootPath = session.getServletContext().getRealPath("/");
                File dir = new File(rootPath + File.separator + "WEB-INF" +
                        File.separator + "resources" + File.separator + "images");
                if (!dir.exists())
                    dir.mkdirs();

                String extension = FilenameUtils.getExtension(file.getOriginalFilename());
                String fileName = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(20), extension);
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                good.setFilePath(fileName);

            } catch (Exception e) {
                bindingResult.rejectValue("filePath", "Failed to upload file");
                return "admin/creategood";
            }
        }

        goodsService.createGood(good);
        return "admin/goodcreated";

    }

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public String getPaginatorGoods(Model model, @RequestParam(required = false) Integer pageid,
                                    @RequestParam(required = false) String brand,
                                    @RequestParam(required = false) String colour,
                                    @RequestParam(required = false) String title,
                                    @RequestParam(required = false) Long minPrice,
                                    @RequestParam(required = false) Long maxPrice,
                                    @RequestParam(required = false) String category) {

        List<Good> goods;
        model.addAttribute("categories", categoriesService.getAllCategories());
        if (brand != null || colour != null || title != null || minPrice != null ||
                maxPrice != null || category != null) {

            if (minPrice == null)
                minPrice = 0L;

            if (maxPrice == null)
                maxPrice = Long.MAX_VALUE;

            goods = goodsService.search(brand, colour, title, minPrice, maxPrice, category);
            model.addAttribute("goods", goods);
            return "goods";
        }

        if (pageid == null) {
            prepareModel(model, 1);
            return "goods";
        }

        prepareModel(model, pageid);
        return "goods";
    }

    private void prepareModel(Model model, Integer pageId) {
        List<Good> goods;
        goods = goodsService.getGoodsPage(pageId, (int) GOODS_PER_PAGE);
        int numberOfPages = (int) Math.ceil(goodsService.goodsSize() / GOODS_PER_PAGE);

        model.addAttribute("goods", goods);
        model.addAttribute("numberOfPages", numberOfPages);
    }

}
