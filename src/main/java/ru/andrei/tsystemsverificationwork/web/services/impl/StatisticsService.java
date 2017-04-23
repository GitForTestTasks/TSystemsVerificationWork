package ru.andrei.tsystemsverificationwork.web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.dao.impl.ClientsDao;
import ru.andrei.tsystemsverificationwork.database.dao.impl.GoodsDao;
import ru.andrei.tsystemsverificationwork.database.models.Good;
import ru.andrei.tsystemsverificationwork.database.models.OrderDetail;
import ru.andrei.tsystemsverificationwork.database.models.StatisticsClients;
import ru.andrei.tsystemsverificationwork.database.models.StatisticsGoods;
import ru.andrei.tsystemsverificationwork.database.models.enums.TimeValues;
import ru.andrei.tsystemsverificationwork.web.services.GenericService;

import javax.jms.JMSException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("statisticsService")
@Transactional
public class StatisticsService extends GenericService {

    private JmsService jmsService;
    private GoodsDao goodsDao;
    private ClientsDao clientsDao;
    private static List<StatisticsGoods> statisticsGoods = null;

    @Autowired
    public StatisticsService(JmsService jmsService, GoodsDao goodsDao, ClientsDao clientsDao) {
        this.jmsService = jmsService;
        this.goodsDao = goodsDao;
        this.clientsDao = clientsDao;
    }

    public void forceUpdate() {

        try {
            jmsService.submit("update");
        } catch (Exception e) {
            e.printStackTrace();
        }

        statisticsGoods = goodsDao.topTenGoods();
    }

    public void checkIfUpdateNeeded(Long goodId) {

        for (StatisticsGoods goods : statisticsGoods) {

            if (goods.getGoodId().equals(goodId)) {
                forceUpdate();
                break;
            }
        }
    }

    public List<StatisticsGoods> topTenGoods() {

        if (statisticsGoods == null)
            forceUpdate();

        if (statisticsGoods == null)
            return new ArrayList<>();
        else
            return statisticsGoods;
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
