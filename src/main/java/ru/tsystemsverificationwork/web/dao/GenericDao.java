package ru.tsystemsverificationwork.web.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public abstract class GenericDao<E> {

    @PersistenceContext
    private EntityManager transactionManager;

    private Class< E > clazz;

    public void create(E entity) {
        transactionManager.persist(entity);
    }

    public final void setClazz( Class< E > clazzToSet ){
        this.clazz = clazzToSet;
    }

    public E findOne( long id ){
        return transactionManager.find( clazz, id );
    }

    public List< E > getAll(){
        return transactionManager.createQuery( "from " + clazz.getName() , clazz ).getResultList();
    }

    public E update( E entity ){
        return transactionManager.merge( entity );
    }

    public void delete( E entity ){
        transactionManager.remove( entity );
    }

    public void deleteById( long entityId ){
        E entity = findOne( entityId );
        delete( entity );
    }

}
