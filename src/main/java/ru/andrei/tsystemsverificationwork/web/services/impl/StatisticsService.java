package ru.andrei.tsystemsverificationwork.web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.andrei.tsystemsverificationwork.database.dao.impl.ClientsDao;
import ru.andrei.tsystemsverificationwork.database.dao.impl.GoodsDao;
import ru.andrei.tsystemsverificationwork.database.models.Good;
import ru.andrei.tsystemsverificationwork.database.models.OrderDetail;
import ru.andrei.tsystemsverificationwork.database.models.StatisticsClients;
import ru.andrei.tsystemsverificationwork.database.models.StatisticsGoods;
import ru.andrei.tsystemsverificationwork.database.models.enums.TimeValues;
import ru.andrei.tsystemsverificationwork.web.services.GenericService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("statisticsService")
public class StatisticsService extends GenericService {

    private GoodsDao goodsDao;
    private ClientsDao clientsDao;

    @Autowired
    public StatisticsService(GoodsDao goodsDao, ClientsDao clientsDao) {
        this.goodsDao = goodsDao;
        this.clientsDao = clientsDao;
    }


    public List<StatisticsGoods> topTenGoods() {

        List<StatisticsGoods> goods = goodsDao.topTenGoods();
        if (goods == null)
            return new ArrayList<>();
        else
            return goods;
    }

    public List<StatisticsClients> topTenClients() {

        List<StatisticsClients> statisticsClientss = clientsDao.topTenClients();
        if (statisticsClientss == null)
            return new ArrayList<>();
        else
            return statisticsClientss;
    }

    public List<BigDecimal> getIncome() {

        List<BigDecimal> incomes = new ArrayList<>();
        incomes.add(goodsDao.getIncome(TimeValues.MONTH.toString()));
        incomes.add(goodsDao.getIncome(TimeValues.WEEK.toString()));

        return incomes;
    }
}
