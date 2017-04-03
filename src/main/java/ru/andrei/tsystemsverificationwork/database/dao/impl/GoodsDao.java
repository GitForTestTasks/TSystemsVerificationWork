package ru.andrei.tsystemsverificationwork.database.dao.impl;

import org.springframework.stereotype.Component;
import ru.andrei.tsystemsverificationwork.database.dao.GenericDao;
import ru.andrei.tsystemsverificationwork.database.models.Good;
import ru.andrei.tsystemsverificationwork.database.models.StatisticsGoods;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@Component("goodsDao")
/*@SqlResultSetMapping(
        name = "statisticsMapping",
        entities = {
                @EntityResult(
                        entityClass = StatisricsGood.class,
                        fields = {
                                @FieldResult(name = "id", column = "id"),
                                @FieldResult(name = "title", column = "title"),
                                @FieldResult(name = "version", column = "version")})})*/
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
                " AND r.colour LIKE :colour", Good.class)
                .setParameter("brand", "%" + brand + "%")
                .setParameter("colour", "%" + colour + "%").getResultList();
    }



    public List<StatisticsGoods> topTenGoods() {

        List<Object[]> results = transactionManager.createNativeQuery("SELECT " +
                "`goods`.`Title`, " +
                "SUM(`orderdetails`.`Quantity`) as `QuantitySum`, " +
                "`goods`.`count` " +
                "FROM `goods` " +
                "LEFT JOIN `orderdetails` ON `goods`.`GoodId` = `orderdetails`.`GoodId` " +
                "LEFT JOIN `orders` ON `orders`.`OrderId` = `orderdetails`.`OrderId` " +
                "WHERE `orders`.`OrderStatus` != 'NOT_PAID' " +
                "GROUP BY `orderdetails`.`GoodId` " +
                "ORDER BY `QuantitySum` DESC LIMIT 10;").getResultList();

        if(results == null)
            return new ArrayList<>();

        ArrayList<StatisticsGoods> statisticsGoods = new ArrayList<>();

        results.forEach((record) -> {

            StatisticsGoods statisticsGood = new StatisticsGoods();
            statisticsGood.setTitle((String) record[0]);
            statisticsGood.setQuantitySum(((BigDecimal) record[1]).longValue());
            statisticsGood.setCount((Integer) record[2]);

            statisticsGoods.add(statisticsGood);

        });

        return statisticsGoods;
    }

    public BigDecimal getIncome(String time) {

        return  (BigDecimal) transactionManager.createNativeQuery("SELECT SUM(`goods`.`Price`) AS `total` " +
                "FROM `orders` " +
                "LEFT JOIN `orderdetails` ON `orderdetails`.`OrderId` = `orders`.`OrderId` " +
                "LEFT JOIN `goods` ON `goods`.`GoodId` = `orderdetails`.`GoodId` " +
                "WHERE `orders`.`OrderStatus` != 'NOT_PAID' " +
                "AND `orders`.`DateOfSale`  >= NOW() - INTERVAL 1 " + time).getSingleResult();
    }
}
