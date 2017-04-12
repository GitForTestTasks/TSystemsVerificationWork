package ru.andrei.tsystemsverificationwork.database.dao.impl;

import org.springframework.stereotype.Component;
import ru.andrei.tsystemsverificationwork.database.dao.GenericDao;
import ru.andrei.tsystemsverificationwork.database.models.Good;
import ru.andrei.tsystemsverificationwork.database.models.StatisticsGoods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Component("goodsDao")
public class GoodsDao extends GenericDao<Good> {

    public GoodsDao() {
        setClazz(Good.class);
    }

    public List<Good> getStrictedGoodsList(int startingValue, int quantityResults) {

        return transactionManager.createQuery("FROM Good", Good.class)
                .setFirstResult(startingValue).setMaxResults(quantityResults).getResultList();
    }

    @Override
    public void create(Good entity) {
        if (transactionManager.contains(entity))
            transactionManager.persist(entity);
        else transactionManager.merge(entity);
    }

    public Long size() {
        return (Long) transactionManager.createQuery("select count(GoodId) from Good").getSingleResult();
    }

    public List<Good> searchByForm(String brand, String colour, String title,
                                             BigDecimal minPrice, BigDecimal maxPrice, String category) {

        return transactionManager.createQuery("FROM Good AS r " +
                " WHERE r.brand LIKE :brand " +
                " AND r.category.name LIKE :category" +
                " AND r.colour LIKE :colour " +
                " AND r.title LIKE :title " +
                " AND r.price >= :minPrice " +
                " AND r.price <= :maxPrice ", Good.class)
                .setParameter("category", category)
                .setParameter("brand", "%" + brand + "%")
                .setParameter("colour", "%" + colour + "%")
                .setParameter("title", "%" + title + "%")
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }


    @SuppressWarnings("unchecked")
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

        if (results == null)
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

        return (BigDecimal) transactionManager.createNativeQuery("SELECT SUM(`goods`.`Price`) AS `total` " +
                "FROM `orders` " +
                "LEFT JOIN `orderdetails` ON `orderdetails`.`OrderId` = `orders`.`OrderId` " +
                "LEFT JOIN `goods` ON `goods`.`GoodId` = `orderdetails`.`GoodId` " +
                "WHERE `orders`.`OrderStatus` != 'NOT_PAID' " +
                "AND `orders`.`DateOfSale`  >= NOW() - INTERVAL 1 " + time).getSingleResult();
    }

    public List<Good> searchByFormDefaultCategory(String brand, String colour, String title,
                                   BigDecimal minPrice, BigDecimal maxPrice) {

        return transactionManager.createQuery("FROM Good AS r " +
                " WHERE r.brand LIKE :brand " +
                " AND r.colour LIKE :colour " +
                " AND r.title LIKE :title " +
                " AND r.price >= :minPrice " +
                " AND r.price <= :maxPrice ", Good.class)
                .setParameter("brand", "%" + brand + "%")
                .setParameter("colour", "%" + colour + "%")
                .setParameter("title", "%" + title + "%")
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }
}
