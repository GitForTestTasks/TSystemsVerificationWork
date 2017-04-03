package ru.andrei.tsystemsverificationwork.web.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.models.Category;
import ru.andrei.tsystemsverificationwork.database.dao.impl.CategoriesDao;

import java.util.List;

@Service
@Transactional
public class CategoriesService {

    private CategoriesDao categoriesDao;

    @Autowired
    public CategoriesService(CategoriesDao categoriesDao) {
        this.categoriesDao = categoriesDao;
    }

    public List<Category> getAllCategories() {

        return categoriesDao.getAll();
    }

    public Category getCategoryById(Long categoryId) {

        if (categoryId == null) {
            return new Category();
        }

        Category categoryCheck = categoriesDao.findOne(categoryId);
        if (categoryCheck == null) {
            return new Category();
        } else return categoryCheck;
    }

    public void createCategory(Category category) {

        categoriesDao.update(category);
    }

    public boolean categoryExists(String name) {

        return categoriesDao.categoryExists(name);
    }

    public void categoryDelete(Long categoryId) {

        categoriesDao.delete(categoriesDao.findOne(categoryId));
    }
}
