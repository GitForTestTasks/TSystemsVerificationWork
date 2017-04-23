package ru.andrei.tsystemsverificationwork.web.services.impl;

import ru.andrei.tsystemsverificationwork.database.models.StatisticsGoods;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class TopGoodsWs{

    private StatisticsService statisticsService;

    @WebMethod(exclude=true)
    public void setStatisticsService(StatisticsService helloWorldBo) {

        this.statisticsService = helloWorldBo;
    }

    @WebMethod(operationName="getTopTenGoods")
    public List<StatisticsGoods> getTopTenGoods() {

        return statisticsService.topTenGoods();
    }
}