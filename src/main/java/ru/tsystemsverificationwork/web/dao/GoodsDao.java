package ru.tsystemsverificationwork.web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystemsverificationwork.web.models.Good;

import java.util.List;


@Transactional
@Component("goodsDao")
public class GoodsDao extends GenericDao<Good> {

    public GoodsDao() {
        setClazz(Good.class);
    }

    public List<Good> getStrictedGoodsList(int startingValue, int quantityResults) {

        return transactionManager.createQuery("FROM Good", Good.class)
                .setFirstResult(startingValue).setMaxResults(quantityResults).getResultList();
    }

    public Long size() {
        return (Long) transactionManager.createQuery("select count(GoodId) from Good").getSingleResult();
    }

    public List<Good> searchByBrandAndColour(String brand, String colour) {

        return transactionManager.createQuery("FROM Good AS r WHERE r.brand LIKE :brand" +
                " AND r.colour LIKE :colour", Good.class).
                setParameter("brand", "%" + brand + "%").setParameter("colour", "%" + colour + "%").getResultList();
    }

//
//    SELECT *  FROM `goods`
//    WHERE `name` LIKE '%go%'
//    AND `color` LIKE '%re%'
//    AND `brand` LIKE '%i%'
}
