package ru.tsystemsverificationwork.web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystemsverificationwork.web.models.Role;

@Transactional
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
