package ru.andrei.tsystemsverificationwork.database.dao.impl;

import org.springframework.stereotype.Component;
import ru.andrei.tsystemsverificationwork.database.dao.GenericDao;
import ru.andrei.tsystemsverificationwork.database.models.Good;
import ru.andrei.tsystemsverificationwork.database.models.StatisticsGoods;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao object of Good entity
 */
@Component("goodsDao")
public class GoodsDao extends GenericDao<Good> {

    public GoodsDao() {
        setClazz(Good.class);
    }

    /**
     * Returns stricted list of non-deleted Goods to be paged on front-end
     *
     * @param startingValue   from what position we are going to start search
     * @param quantityResults returned list size
     * @return list of Good objects
     */
    public List<Good> getStrictedGoodsList(int startingValue, int quantityResults) {

        return transactionManager.createQuery("FROM Good AS r WHERE r.isGoodDeleted NOT LIKE '1'", Good.class)
                .setFirstResult(startingValue).setMaxResults(quantityResults).getResultList();
    }

    /**
     * Adds or updates Good object to database
     *
     * @param entity Good object to be updated or created
     */
    @Override
    public void create(Good entity) {
        if (transactionManager.contains(entity))
            transactionManager.persist(entity);
        else transactionManager.merge(entity);
    }

    /**
     * Returns number of non-deleted goods
     *
     * @return Long number of goods
     */
    public Long size() {
        return (Long) transactionManager.createQuery("select count(GoodId) from Good" +
                " AS r WHERE r.isGoodDeleted NOT LIKE '1'").getSingleResult();
    }

    /**
     * Returns list of Goods searched by presented parameters
     *
     * @param brand    brand of good
     * @param colour   colour of good
     * @param title    name of good
     * @param minPrice minimal price to search
     * @param maxPrice maximal price to search
     * @param category category to find
     * @return list of goods searched
     */
    public List<Good> searchByForm(String brand, String colour, String title,
                                   BigDecimal minPrice, BigDecimal maxPrice, String category) {

        return transactionManager.createQuery("FROM Good AS r " +
                " WHERE r.brand LIKE :brand " +
                " AND r.category.name LIKE :category" +
                " AND r.colour LIKE :colour " +
                " AND r.title LIKE :title " +
                " AND r.price >= :minPrice " +
                " AND r.price <= :maxPrice " +
                " AND r.isGoodDeleted NOT LIKE '1'", Good.class)
                .setParameter("category", category)
                .setParameter("brand", "%" + brand + "%")
                .setParameter("colour", "%" + colour + "%")
                .setParameter("title", "%" + title + "%")
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }

    /**
     * Returns list of DTO statisticsgoods represent top ten goods
     *
     * @return list of StatisticsGoods objects
     */
    @SuppressWarnings("unchecked")
    public List<StatisticsGoods> topTenGoods() {

        List<Object[]> results = transactionManager.createNativeQuery("SELECT " +
                "`goods`.`Title`, " +
                "SUM(`orderdetails`.`Quantity`) as `QuantitySum`, " +
                "`goods`.`count`, `goods`.`GoodId`, `goods`.`Price` " +
                "FROM `goods` " +
                "LEFT JOIN `orderdetails` ON `goods`.`GoodId` = `orderdetails`.`GoodId` " +
                "LEFT JOIN `orders` ON `orders`.`OrderId` = `orderdetails`.`OrderId` " +
                "WHERE `orders`.`OrderStatus` != 'NOT_PAID' " +
                "AND `goods`.`IsGoodDeleted` != '1' " +
                "GROUP BY `goods`.`Price` " +
                "ORDER BY `QuantitySum` DESC LIMIT 10;").getResultList();

        if (results == null)
            return new ArrayList<>();

        ArrayList<StatisticsGoods> statisticsGoods = new ArrayList<>();

        results.forEach(record -> {

            StatisticsGoods statisticsGood = new StatisticsGoods();
            statisticsGood.setTitle((String) record[0]);
            statisticsGood.setQuantitySum(((BigDecimal) record[1]).longValue());
            statisticsGood.setCount((Integer) record[2]);
            statisticsGood.setGoodId(((BigInteger) record[3]).longValue());
            statisticsGood.setPrice((BigDecimal) record[4]);

            statisticsGoods.add(statisticsGood);

        });

        return statisticsGoods;
    }

    /**
     * Returns income recently earned
     *
     * @param time time interval earning
     * @return bigdecimal value of income
     */
    public BigDecimal getIncome(String time) {

        return (BigDecimal) transactionManager.createNativeQuery("SELECT SUM(`goods`.`Price`) " +
                "FROM `orders` " +
                "LEFT JOIN `orderdetails` ON `orderdetails`.`OrderId` = `orders`.`OrderId` " +
                "LEFT JOIN `goods` ON `goods`.`GoodId` = `orderdetails`.`GoodId` " +
                "WHERE `orders`.`OrderStatus` != 'NOT_PAID' " +
                "AND `orders`.`DateOfSale`  >= NOW() - INTERVAL :days DAY")
                .setParameter("days", time).getSingleResult();
    }

    /**
     * Returns list of Goods searched by presented parameters
     *
     * @param brand    brand of good
     * @param colour   colour of good
     * @param title    name of good
     * @param minPrice minimal price to search
     * @param maxPrice maximal price to search
     * @return list of goods searched
     */
    public List<Good> searchByFormDefaultCategory(String brand, String colour, String title,
                                                  BigDecimal minPrice, BigDecimal maxPrice) {

        return transactionManager.createQuery("FROM Good AS r " +
                " WHERE r.brand LIKE :brand " +
                " AND r.colour LIKE :colour " +
                " AND r.title LIKE :title " +
                " AND r.price >= :minPrice " +
                " AND r.price <= :maxPrice " +
                " AND r.isGoodDeleted NOT LIKE '1'", Good.class)
                .setParameter("brand", "%" + brand + "%")
                .setParameter("colour", "%" + colour + "%")
                .setParameter("title", "%" + title + "%")
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .getResultList();
    }
}
