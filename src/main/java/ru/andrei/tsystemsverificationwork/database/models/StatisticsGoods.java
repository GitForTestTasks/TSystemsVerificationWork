package ru.andrei.tsystemsverificationwork.database.models;

/**
 * Created by Andrei on 4/3/2017.
 */
public class StatisticsGoods {

    private String title;
    private Long quantitySum;
    private Integer count;

    @Override
    public String toString() {
        return "StatisticsGood{" +
                "title='" + title + '\'' +
                ", quantitySum=" + quantitySum +
                ", count=" + count +
                '}';
    }

    public StatisticsGoods() {
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
