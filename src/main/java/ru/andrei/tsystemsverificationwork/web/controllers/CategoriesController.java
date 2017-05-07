package ru.andrei.tsystemsverificationwork.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.andrei.tsystemsverificationwork.database.models.Category;
import ru.andrei.tsystemsverificationwork.web.services.impl.CategoriesService;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller responsible for categories
 */
@Controller
public class CategoriesController {

    /**
     * Name of view with list of categories
     */
    private static final String ADMIN_CATEGORY = "admin/categories";

    /**
     * Name of creating category form view
     */
    private static final String CREATE_CATEGORY = "admin/createcategory";

    /**
     * Immutable string of category attribute
     */
    private static final String CATEGORY = "category";

    /**
     * Category service
     */
    private CategoriesService categoriesService;

    /**
     * Autowiring category service
     *
     * @param categoriesService category service
     */
    @Autowired
    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    /**
     * Returns view of categories by get method
     *
     * @param model view attributes
     * @return jsp view
     */
    @RequestMapping(value = "/admin/categories", method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public String getPaginatorGoods(Model model) {

        List<Category> categories = categoriesService.getAllCategories();
        model.addAttribute(CATEGORY, categories);

        return ADMIN_CATEGORY;
    }

    /**
     * Returns view of creating or changing or deleting category
     *
     * @param model      view attributes
     * @param categoryId id of category to be changed
     * @param delete     id of category to be deleted
     * @return jsp view
     */
    @RequestMapping(value = "/admin/createcategory", method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public String createCategory(Model model, @RequestParam(required = false) Long categoryId,
                                 @RequestParam(required = false) Long delete) {

        if (delete != null) {

            categoriesService.categoryDelete(delete);
            List<Category> categories = categoriesService.getAllCategories();
            model.addAttribute(CATEGORY, categories);
            return ADMIN_CATEGORY;
        }

        if (categoryId == null)
            model.addAttribute(CATEGORY, new Category());
        else
            model.addAttribute(CATEGORY, categoriesService.getCategoryById(categoryId));

        return CREATE_CATEGORY;
    }

    /**
     * Handles post method
     *
     * @param model         view attributes
     * @param category      object passed through form
     * @param bindingResult result attributes
     * @return jsp view
     */
    @RequestMapping(value = "/admin/createcategory", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String createCategory(Model model, @Valid Category category, BindingResult bindingResult) {

        if (categoriesService.categoryExists(category.getName())) {
            model.addAttribute("fail", true);
            return CREATE_CATEGORY;
        }

        if (bindingResult.hasErrors()) {
            return CREATE_CATEGORY;
        }
        categoriesService.createCategory(category);

        model.addAttribute(CATEGORY, categoriesService.getAllCategories());
        return ADMIN_CATEGORY;
    }
}
