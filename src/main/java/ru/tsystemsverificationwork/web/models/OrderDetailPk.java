package ru.tsystemsverificationwork.web.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Component("orderDetailPk")
public class OrderDetailPk implements Serializable {

    private Order order;

    @ManyToOne
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    private Good good;

    @ManyToOne
    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetailPk that = (OrderDetailPk) o;

        if (!order.equals(that.order)) return false;
        return good.equals(that.good);
    }

    @Override
    public int hashCode() {
        int result = order.hashCode();
        result = 31 * result + good.hashCode();
        return result;
    }
}
