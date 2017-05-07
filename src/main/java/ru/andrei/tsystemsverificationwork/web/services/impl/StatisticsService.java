package ru.andrei.tsystemsverificationwork.web.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrei.tsystemsverificationwork.database.dao.impl.ClientsDao;
import ru.andrei.tsystemsverificationwork.database.dao.impl.GoodsDao;
import ru.andrei.tsystemsverificationwork.database.models.StatisticsClients;
import ru.andrei.tsystemsverificationwork.database.models.StatisticsGoods;
import ru.andrei.tsystemsverificationwork.database.models.enums.TimeValues;
import ru.andrei.tsystemsverificationwork.web.services.GenericService;

import javax.jms.JMSException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Business logic of statistics
 */
@Service("statisticsService")
@Transactional
public class StatisticsService extends GenericService {

    /**
     * SLF4J logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsService.class);
    /**
     * Business logic of jms
     */
    @Autowired
    @Lazy
    private JmsService jmsService;
    /**
     * Good's Dao
     */
    @Autowired
    private GoodsDao goodsDao;
    /**
     * Users's Dao
     */
    @Autowired
    private ClientsDao clientsDao;
    /**
     * Cached top ten goods
     */
    private List<StatisticsGoods> statisticsGoods = null;

    /**
     * Forces update to top ten goods
     */
    public void forceUpdate() {

        try {
            jmsService.submit("update");
        } catch (JMSException e) {
            LOGGER.error(e.getMessage(), e);
        }

        statisticsGoods = goodsDao.topTenGoods();
    }

    /**
     * Checks if update needed when goods changes take place
     *
     * @param goodId id of good that is changed
     */
    public void checkIfUpdateNeeded(Long goodId) {

        if (statisticsGoods != null) {
            for (StatisticsGoods goods : statisticsGoods) {

                if (goods.getGoodId().equals(goodId)) {
                    forceUpdate();
                    break;
                }
            }
        } else forceUpdate();
    }

    /**
     * Statistics of top goods sold
     *
     * @return list of dto objects
     */
    public List<StatisticsGoods> topTenGoods() {

        if (statisticsGoods == null)
            forceUpdate();

        if (statisticsGoods == null)
            return new ArrayList<>();
        else
            return statisticsGoods;
    }

    /**
     * Statistics of top clients
     *
     * @return list of dto objects
     */
    public List<StatisticsClients> topTenClients() {

        List<StatisticsClients> statisticsClientss = clientsDao.topTenClients();
        if (statisticsClientss == null)
            return new ArrayList<>();
        else
            return statisticsClientss;
    }

    /**
     * Statistics of income
     *
     * @return income per month and week
     */
    public List<BigDecimal> getIncome() {

        List<BigDecimal> incomes = new ArrayList<>();
        incomes.add(goodsDao.getIncome(TimeValues.MONTH.toString()));
        incomes.add(goodsDao.getIncome(TimeValues.WEEK.toString()));

        return incomes;
    }
}
