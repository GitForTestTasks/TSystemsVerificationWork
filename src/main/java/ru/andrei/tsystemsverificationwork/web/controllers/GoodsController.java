package ru.andrei.tsystemsverificationwork.web.controllers;


import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.andrei.tsystemsverificationwork.database.models.Category;
import ru.andrei.tsystemsverificationwork.database.models.Good;
import ru.andrei.tsystemsverificationwork.web.editors.CategoryEditor;
import ru.andrei.tsystemsverificationwork.web.services.impl.CategoriesService;
import ru.andrei.tsystemsverificationwork.web.services.impl.GoodsService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Controller responsible for good related views
 */
@Controller
public class GoodsController {

    /**
     * Name of goods view
     */
    private static final String GOODS = "goods";
    /**
     * Name of goods attribute
     */
    private static final String GOODS_ATTRIBUTE = "goods";
    /**
     * SLF4J logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);
    /**
     * Variable is used for returning view of creating good
     */
    private static final String CREATE_GOOD = "admin/creategood";
    /**
     * Variable is used for paging goods
     */
    private static final double GOODS_PER_PAGE = 8.0;
    /**
     * Business logic of goods
     */
    private GoodsService goodsService;
    /**
     * Business logic of good's categories
     */
    private CategoriesService categoriesService;

    @Autowired
    public GoodsController(GoodsService goodsService, CategoriesService categoriesService) {
        this.goodsService = goodsService;
        this.categoriesService = categoriesService;
    }

    /**
     * Initial binder string to category
     *
     * @param binder editor object with conversion methods
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, new CategoryEditor());
    }

    /**
     * Returns good's form
     *
     * @param model  view attributes
     * @param goodId good id to auto fill in the form
     * @return jsp view of form
     */
    @RequestMapping(value = "/admin/creategood", method = RequestMethod.GET)
    public String createGoodGet(Model model, @RequestParam(required = false) Long goodId) {

        if (goodId == null)
            model.addAttribute("good", new Good());
        else
            model.addAttribute("good", goodsService.getGoodById(goodId));

        return CREATE_GOOD;
    }

    /**
     * Method handles deleting soft-deleting good
     *
     * @param model  view attributes
     * @param goodId good to be deleted
     * @return view of all goods
     */
    @RequestMapping(value = "/admin/deletegood", method = RequestMethod.GET)
    public String deleteGood(Model model, @RequestParam(required = false) Long goodId) {

        if (goodId != null)
            goodsService.deleteGood(goodId);

        prepareModel(model, 1);
        return GOODS;
    }

    /**
     * Handles post submitting good's form
     *
     * @param session       current session of user
     * @param good          good entity to persist into database
     * @param bindingResult result of operation
     * @param file          multipart file (an image of good)
     * @return result of operation
     */
    @RequestMapping(value = "/admin/creategood", method = RequestMethod.POST)
    public String createGood(HttpSession session,
                             @Valid Good good, BindingResult bindingResult,
                             @RequestParam("file") MultipartFile file) {

        if (bindingResult.hasErrors()) {
            return CREATE_GOOD;
        }

        if (!file.isEmpty()) {
            String mimeType = session.getServletContext().getMimeType(file.getOriginalFilename());
            if (!mimeType.startsWith("image/")) {
                bindingResult.rejectValue("filePath", "FileIsNotImage.good.file");
                return CREATE_GOOD;
            }

            String rootPath = session.getServletContext().getRealPath("/");
            File dir = new File(rootPath + File.separator + "images");
            if (!dir.exists())
                dir.mkdirs();
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String fileName = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(20), extension);
            File serverFile = new File(dir.getAbsolutePath()
                    + File.separator + fileName);


            try (FileOutputStream fileOutputStream = new FileOutputStream(serverFile);
                 BufferedOutputStream stream = new BufferedOutputStream(fileOutputStream);) {
                byte[] bytes = file.getBytes();

                good.setFilePath(fileName);
                stream.write(bytes);

            } catch (Exception e) {
                bindingResult.rejectValue("filePath", "Failed to upload file");
                LOGGER.info(e.getMessage(), e);
                return CREATE_GOOD;
            }
        }

        goodsService.createGood(good);
        return "admin/goodcreated";

    }


    /**
     * Returns view of paged goods depending on whether
     * attributes from form are passed or not
     *
     * @param model    view attributes
     * @param pageid   number of page to return
     * @param brand    brand for searching good
     * @param colour   colour for searching
     * @param title    title for searching
     * @param minPrice minimal price for searching
     * @param maxPrice maximal price for searching
     * @param category category of good to search
     * @return goods view
     */
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
        Long localMinPrice = minPrice;
        Long localMaxPrice = maxPrice;

        if (brand != null || colour != null || title != null || localMinPrice != null ||
                localMaxPrice != null || category != null) {

            if (localMinPrice == null)
                localMinPrice = 0L;

            if (localMaxPrice == null)
                localMaxPrice = Long.MAX_VALUE;

            goods = goodsService.search(brand, colour, title, localMinPrice, localMaxPrice, category);
            model.addAttribute(GOODS_ATTRIBUTE, goods);
            return GOODS;
        }

        if (pageid == null) {
            prepareModel(model, 1);
            return GOODS;
        }

        prepareModel(model, pageid);
        return GOODS;
    }

    private void prepareModel(Model model, Integer pageId) {
        List<Good> goods;
        goods = goodsService.getGoodsPage(pageId, (int) GOODS_PER_PAGE);
        int numberOfPages = (int) Math.ceil(goodsService.goodsSize() / GOODS_PER_PAGE);

        model.addAttribute(GOODS_ATTRIBUTE, goods);
        model.addAttribute("numberOfPages", numberOfPages);
    }
}
