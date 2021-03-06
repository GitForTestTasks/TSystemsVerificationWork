package ru.andrei.tsystemsverificationwork.database.models.keys;

import ru.andrei.tsystemsverificationwork.database.models.Good;
import ru.andrei.tsystemsverificationwork.database.models.Order;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Embedded primary key of OrderDetail entity
 */
@Embeddable
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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        OrderDetailPk that = (OrderDetailPk) o;

        return order.equals(that.order) && good.equals(that.good);
    }

    @Override
    public int hashCode() {
        int result = order.hashCode();
        result = 31 * result + good.hashCode();
        return result;
    }
}
