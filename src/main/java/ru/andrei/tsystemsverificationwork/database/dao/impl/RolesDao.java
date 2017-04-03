package ru.andrei.tsystemsverificationwork.database.dao.impl;

import org.springframework.stereotype.Component;
import ru.andrei.tsystemsverificationwork.database.dao.GenericDao;
import ru.andrei.tsystemsverificationwork.database.models.Role;

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
