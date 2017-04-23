package ru.andrei.tsystemsverificationwork.database.dao.impl;


import org.springframework.stereotype.Component;
import ru.andrei.tsystemsverificationwork.database.dao.GenericDao;
import ru.andrei.tsystemsverificationwork.database.models.Category;

/**
 * Dao object of Category entity
 */
@Component("categoriesDao")
public class CategoriesDao extends GenericDao<Category> {

    public CategoriesDao() {
        setClazz(Category.class);
    }

    /**
     * Returns boolean value if category exists
     * @param category name of category
     * @return true if category exists
     */
    public boolean categoryExists(String category) {

        return transactionManager.createQuery("FROM Category AS r WHERE r.name = :category", Category.class).
                setParameter("category", category).getResultList().size() > 0;
    }

    /**
     * Finds category by name column
     * @param name string name
     * @return Category object found by name
     */
    public Category findByName(String name) {
        return transactionManager.createQuery("FROM Category AS r WHERE r.name = :name", Category.class)
                .setParameter("name", name).getSingleResult();
    }

    /**
     * Deleting category
     * @param category category to delete
     */
    public void delete(Category category) {
        transactionManager.remove(transactionManager.contains(category) ? category :
                transactionManager.merge(category));
    }

    /**
     * Updates category with new attributes
     * @param category category to update
     */
    public void update(Category category) {

        if (transactionManager.contains(category))
            transactionManager.persist(category);
        else transactionManager.merge(category);
    }
}
