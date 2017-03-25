package ru.tsystemsverificationwork.web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystemsverificationwork.web.models.Good;


@Transactional
@Component("goodsDao")
public class GoodsDao extends GenericDao<Good> {

    public GoodsDao() {
        setClazz(Good.class);
    }
}
