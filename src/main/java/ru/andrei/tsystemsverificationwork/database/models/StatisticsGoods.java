package ru.andrei.tsystemsverificationwork.database.models;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO of goods statistics
 */
public class StatisticsGoods implements Serializable {

    private String title;
    private Long quantitySum;
    private Integer count;
    private Long goodId;
    private BigDecimal price;

    @Override
    public String toString() {
        return "StatisticsGood{" +
                "title='" + title + '\'' +
                ", quantitySum=" + quantitySum +
                ", count=" + count +
                '}';
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getQuantitySum() {
        return quantitySum;
    }

    public void setQuantitySum(Long quantitySum) {
        this.quantitySum = quantitySum;
    }
}
