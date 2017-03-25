package ru.tsystemsverificationwork.web.models;

import javax.persistence.*;


@Entity
@Table(name = "orderdetails")
@IdClass(OrderDetailPk.class)
public class OrderDetail {

    private int quantity;

    private Order order;

    private Good good;

    private long orderId;
    private long goodId;

    @Id
    @Column(name = "OrderId")
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Id
    @Column(name = "GoodId")
    public long getGoodId() {
        return goodId;
    }

    public void setGoodId(long goodId) {
        this.goodId = goodId;
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
