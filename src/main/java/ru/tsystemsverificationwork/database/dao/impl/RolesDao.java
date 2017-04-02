package ru.tsystemsverificationwork.database.dao.impl;

import org.springframework.stereotype.Component;
import ru.tsystemsverificationwork.database.dao.GenericDao;
import ru.tsystemsverificationwork.database.models.Role;

import javax.transaction.Transactional;

@Component("rolesDao")
public class RolesDao extends GenericDao<Role> {

    public RolesDao() {
        setClazz(Role.class);
    }

    public Role findByName(String name) {
        return transactionManager.createQuery("FROM Role AS r WHERE r.name = :name", Role.class)
                .setParameter("name", name).getSingleResult();
    }
}
