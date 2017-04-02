package ru.tsystemsverificationwork.database.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public abstract class GenericDao<E> {

    @PersistenceContext
    protected EntityManager transactionManager;

    private Class<E> clazz;

    public void create(E entity) {
        transactionManager.persist(entity);
    }

    protected void setClazz(Class<E> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public E findOne(long id) {
        return transactionManager.find(clazz, id);
    }

    public List<E> getAll() {
        return transactionManager.createQuery("from " + clazz.getName(), clazz).getResultList();
    }

    public void update(E entity) {
        transactionManager.merge(entity);
    }

    public void delete(E entity) {
        transactionManager.remove(entity);
    }

    public void deleteById(long entityId) {
        E entity = findOne(entityId);
        delete(entity);
    }
}
