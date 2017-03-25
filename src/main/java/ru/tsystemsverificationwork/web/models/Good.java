package ru.tsystemsverificationwork.web.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Andrei on 3/24/2017.
 */
@Entity
@Table(name = "goods")
public class Good {
    private long goodId;
    private String title;
    private BigDecimal price;
    private String category;
    private String сharacteristics;
    private BigDecimal weight;
    private String size;
    private int count;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GoodId")
    public long getGoodId() {
        return goodId;
    }

    private List<OrderDetail> orderDetail;

    public void setGoodId(long goodId) {
        this.goodId = goodId;
    }

    @Basic
    @Column(name = "Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "Price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "Category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "Сharacteristics")
    public String getСharacteristics() {
        return сharacteristics;
    }

    public void setСharacteristics(String сharacteristics) {
        this.сharacteristics = сharacteristics;
    }

    @Basic
    @Column(name = "Weight")
    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "Size")
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Basic
    @Column(name = "Count")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

//    @OneToMany(mappedBy = "goodId")
    @OneToMany
    @JoinColumn(name = "GoodId")
    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Good good = (Good) o;

        if (goodId != good.goodId) return false;
        if (count != good.count) return false;
        if (title != null ? !title.equals(good.title) : good.title != null) return false;
        if (price != null ? !price.equals(good.price) : good.price != null) return false;
        if (category != null ? !category.equals(good.category) : good.category != null) return false;
        if (сharacteristics != null ? !сharacteristics.equals(good.сharacteristics) : good.сharacteristics != null)
            return false;
        if (weight != null ? !weight.equals(good.weight) : good.weight != null) return false;
        if (size != null ? !size.equals(good.size) : good.size != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (goodId ^ (goodId >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (сharacteristics != null ? сharacteristics.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + count;
        return result;
    }
}
