package ru.tsystemsverificationwork.web.dao;


import org.springframework.stereotype.Component;
import ru.tsystemsverificationwork.web.models.Category;

import javax.transaction.Transactional;

@Transactional
@Component("categoriesDao")
public class CategoriesDao extends GenericDao<Category> {

    public CategoriesDao() {
        setClazz(Category.class);
    }

    public boolean categoryExists(String category) {

        return transactionManager.createQuery("FROM Category AS r WHERE r.name = :category", Category.class).
                setParameter("category", category).getResultList().size() > 0;
    }

    public Category findByName(String name) {
        return transactionManager.createQuery("FROM Category AS r WHERE r.name = :name", Category.class)
                .setParameter("name", name).getSingleResult();
    }

    public void delete(Category category) {
        transactionManager.remove(transactionManager.contains(category) ? category :
                transactionManager.merge(category));
    }

    public void update(Category category) {

        if (transactionManager.contains(category))
            transactionManager.persist(category);
        else transactionManager.merge(category);
    }
}
