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


}
