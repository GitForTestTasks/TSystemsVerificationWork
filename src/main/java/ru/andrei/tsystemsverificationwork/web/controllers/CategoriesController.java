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

@Controller
public class CategoriesController {

    private CategoriesService categoriesService;

    @Autowired
    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }


    @RequestMapping(value = "/admin/categories", method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public String getPaginatorGoods(Model model) {

        List<Category> categories = categoriesService.getAllCategories();
        model.addAttribute("category", categories);

        return "admin/categories";
    }


    @RequestMapping(value = "/admin/createcategory", method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public String createCategory(Model model, @RequestParam(required = false) Long categoryId,
                                 @RequestParam(required = false) Long delete) {

        if(delete != null) {

            categoriesService.categoryDelete(delete);
            List<Category> categories = categoriesService.getAllCategories();
            model.addAttribute("category", categories);
            return "admin/categories";
        }


        model.addAttribute("category", categoriesService.getCategoryById(categoryId));

        return "admin/createcategory";
    }

    @RequestMapping(value = "/admin/createcategory")
    @Secured("ROLE_ADMIN")
    public String deleteCategory() {

        return "admin/createcategory";
    }


    @RequestMapping(value = "/admin/createcategory", method = RequestMethod.POST)
    @Secured("ROLE_ADMIN")
    public String createCategory(Model model, @Valid Category category, BindingResult bindingResult) {

        if (categoriesService.categoryExists(category.getName())) {
            model.addAttribute("fail", true);
            return "admin/createcategory";
        }


        if (bindingResult.hasErrors()) {
            return "admin/createcategory";
        }
        categoriesService.createCategory(category);

        model.addAttribute("category", categoriesService.getAllCategories());
        return "admin/categories";
    }
}
