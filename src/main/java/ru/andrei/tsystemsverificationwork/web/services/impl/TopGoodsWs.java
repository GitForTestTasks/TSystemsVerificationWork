package ru.andrei.tsystemsverificationwork.web.services.impl;

import ru.andrei.tsystemsverificationwork.database.models.StatisticsGoods;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * Web service
 */
@WebService
public class TopGoodsWs {

    /**
     * Statistics business logic
     */
    private StatisticsService statisticsService;

    @WebMethod(exclude = true)
    public void setStatisticsService(StatisticsService helloWorldBo) {

        this.statisticsService = helloWorldBo;
    }

    /**
     * Returns list of top ten goods
     *
     * @return list of dto objects
     */
    @WebMethod(operationName = "getTopTenGoods")
    public List<StatisticsGoods> getTopTenGoods() {

        return statisticsService.topTenGoods();
    }
}