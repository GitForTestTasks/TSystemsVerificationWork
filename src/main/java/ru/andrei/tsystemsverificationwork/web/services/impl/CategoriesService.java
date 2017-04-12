package ru.andrei.tsystemsverificationwork.web.services.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.models.Category;
import ru.andrei.tsystemsverificationwork.database.dao.impl.CategoriesDao;
import ru.andrei.tsystemsverificationwork.web.exceptions.impl.ItemNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoriesService {

    private static final Logger log = LoggerFactory.getLogger(CategoriesService.class);
    private CategoriesDao categoriesDao;

    @Autowired
    public CategoriesService(CategoriesDao categoriesDao) {
        this.categoriesDao = categoriesDao;
    }

    public List<Category> getAllCategories() {

        List<Category> categories = categoriesDao.getAll();
        if (categories == null)
            return new ArrayList<>();
        else return categoriesDao.getAll();
    }

    public Category getCategoryById(Long categoryId) {

        if (categoryId == null) {
            throw new IllegalArgumentException();
        }

        Category categoryCheck = categoriesDao.findOne(categoryId);
        if (categoryCheck == null) {
            throw new ItemNotFoundException("Category with " + categoryId + " id does not exist");
        } else return categoryCheck;
    }

    @Secured("ROLE_ADMIN")
    public void createCategory(Category category) {

        if (category == null)
            throw new IllegalArgumentException();

        log.info("Changes for category " + category.getName() + " submitted");
        categoriesDao.update(category);
    }

    public boolean categoryExists(String name) {

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException();

        return categoriesDao.categoryExists(name);
    }

    public void categoryDelete(Long categoryId) {

        categoriesDao.delete(categoriesDao.findOne(categoryId));
        log.info("Categody with id " + categoryId + " has been deleted");
    }
}
