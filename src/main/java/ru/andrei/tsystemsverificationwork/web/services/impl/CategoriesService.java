package ru.andrei.tsystemsverificationwork.web.services.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.dao.impl.CategoriesDao;
import ru.andrei.tsystemsverificationwork.database.models.Category;
import ru.andrei.tsystemsverificationwork.web.exceptions.impl.ItemNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Business logic of category
 */
@Service
@Transactional
public class CategoriesService {

    /**
     * Slf4j logger
     */
    private static final Logger log = LoggerFactory.getLogger(CategoriesService.class);
    /**
     * Dao of categories
     */
    private CategoriesDao categoriesDao;

    @Autowired
    public CategoriesService(CategoriesDao categoriesDao) {
        this.categoriesDao = categoriesDao;
    }

    /**
     * Is used to return list of all categories
     *
     * @return list of categories
     */
    public List<Category> getAllCategories() {

        List<Category> categories = categoriesDao.getAll();
        if (categories == null)
            return new ArrayList<>();
        else return categoriesDao.getAll();
    }

    /**
     * Returns category by submitted id
     *
     * @param categoryId id is used to find category
     * @return category entity found
     */
    public Category getCategoryById(Long categoryId) {

        if (categoryId == null) {
            throw new IllegalArgumentException();
        }

        Category categoryCheck = categoriesDao.findOne(categoryId);
        if (categoryCheck == null) {
            throw new ItemNotFoundException("Category with " + categoryId + " id does not exist.");
        } else return categoryCheck;
    }

    /**
     * Add row in table categories
     *
     * @param category category is going to be added
     */
    @Secured("ROLE_ADMIN")
    public void createCategory(Category category) {

        if (category == null)
            throw new IllegalArgumentException();

        log.info("Changes for category " + category.getName() + " submitted.");
        categoriesDao.update(category);
    }

    /**
     * Tells if category exists by name
     *
     * @param name name of category to search
     * @return boolean
     */
    public boolean categoryExists(String name) {

        if (name == null || name.isEmpty())
            throw new IllegalArgumentException();

        return categoriesDao.categoryExists(name);
    }

    /**
     * Deleting row in table categories by id
     *
     * @param categoryId if of category is going to be deleted
     */
    public void categoryDelete(Long categoryId) {

        categoriesDao.delete(categoriesDao.findOne(categoryId));
        log.info("Categody with id {} has been deleted", categoryId);
    }
}
