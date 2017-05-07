package ru.andrei.tsystemsverificationwork.database.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Generic Dao object
 *
 * @param <E>
 */
public abstract class GenericDao<E> {

    /**
     * Entity manager
     */
    @PersistenceContext
    protected EntityManager transactionManager;

    /**
     * Entity class passed in implementing constructor
     */
    private Class<E> clazz;

    /**
     * Creates row in database
     *
     * @param entity entity to be created
     */
    public void create(E entity) {
        transactionManager.persist(entity);
    }

    protected void setClazz(Class<E> clazzToSet) {
        this.clazz = clazzToSet;
    }

    /**
     * Finds row in database by id
     *
     * @param id submitted id
     * @return entity object
     */
    public E findOne(long id) {
        return transactionManager.find(clazz, id);
    }

    /**
     * Maps all rows in table to entities
     *
     * @return list of entities
     */
    public List<E> getAll() {
        return transactionManager.createQuery("from " + clazz.getName(), clazz).getResultList();
    }

    /**
     * Updates row in database
     *
     * @param entity entity to be updated
     */
    public void update(E entity) {
        transactionManager.merge(entity);
    }

    /**
     * Deleting row in database
     *
     * @param entity entity to be deleted
     */
    public void delete(E entity) {
        transactionManager.remove(entity);
    }

    /**
     * Deleting row from database by id
     *
     * @param entityId id of entity
     */
    public void deleteById(long entityId) {
        E entity = findOne(entityId);
        delete(entity);
    }
}
