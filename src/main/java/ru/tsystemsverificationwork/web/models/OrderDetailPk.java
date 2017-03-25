package ru.tsystemsverificationwork.web.models;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by Andrei on 3/25/2017.
 */
//@MappedSuperclass
public class OrderDetailPk implements Serializable {
    private long orderId;
    private long goodId;

    @Column(name = "OrderId")
    @Id
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Column(name = "GoodId")
    @Id
    public long getGoodId() {
        return goodId;
    }

    public void setGoodId(long goodId) {
        this.goodId = goodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetailPk that = (OrderDetailPk) o;

        if (orderId != that.orderId) return false;
        if (goodId != that.goodId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (int) (goodId ^ (goodId >>> 32));
        return result;
    }
}
