package ru.andrei.tsystemsverificationwork.database.models;

import ru.andrei.tsystemsverificationwork.database.models.keys.OrderDetailPk;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "orderdetails")
@AssociationOverrides({
        @AssociationOverride(name = "orderDetailPk.order",
                joinColumns = @JoinColumn(name = "OrderId")),
        @AssociationOverride(name = "orderDetailPk.good",
                joinColumns = @JoinColumn(name = "GoodId"))})
public class OrderDetail implements Serializable {

    private int quantity;

    private OrderDetailPk orderDetailPk;

    public OrderDetail() {
        this.orderDetailPk = new OrderDetailPk();
    }

    @EmbeddedId
    public OrderDetailPk getOrderDetailPk() {
        return orderDetailPk;
    }

    public void setOrderDetailPk(OrderDetailPk orderDetailPk) {
        this.orderDetailPk = orderDetailPk;
    }


    @Transient
    public Order getOrder() {
        return getOrderDetailPk().getOrder();
    }

    public void setOrder(Order order) {
        getOrderDetailPk().setOrder(order);
    }

    @Transient
    public Good getGood() {
        return getOrderDetailPk().getGood();
    }

    public void setGood(Good good) {
        getOrderDetailPk().setGood(good);
    }

    @Basic
    @Column(name = "Quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
