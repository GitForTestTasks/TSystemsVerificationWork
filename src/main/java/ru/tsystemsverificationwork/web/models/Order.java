package ru.tsystemsverificationwork.web.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order {
    private long orderId;
    private short paymentMethod;
    private short deliveryMethod;
    private boolean paymentStatus;
    private short orderStatusId;
    private Timestamp dateOfCreation;
    private Timestamp dateOfSale;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId")
    public long getOrderId() {
        return orderId;
    }


    private Client clientId;

    private ClientAddress clientAddressId;


    private List<OrderDetail> orderDetails;

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "PaymentMethod")
    public short getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(short paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Basic
    @Column(name = "DeliveryMethod")
    public short getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(short deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    @Basic
    @Column(name = "PaymentStatus")
    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Basic
    @Column(name = "OrderStatusId")
    public short getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(short orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    @Basic
    @Column(name = "DateOfCreation")
    public Timestamp getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Timestamp dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Basic
    @Column(name = "DateOfSale")
    public Timestamp getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(Timestamp dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    @ManyToOne()
    @JoinColumn(name = "ClientId")
    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    @ManyToOne()
    @JoinColumn(name = "ClientAddressId")
    public ClientAddress getClientAddressId() {
        return clientAddressId;
    }

    public void setClientAddressId(ClientAddress clientAddressId) {
        this.clientAddressId = clientAddressId;
    }

//    @OneToMany(mappedBy = "orderId")
    @OneToMany(mappedBy = "orderDetailPk.order")
//    @JoinColumn(name = "OrderId")
    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (paymentMethod != order.paymentMethod) return false;
        if (deliveryMethod != order.deliveryMethod) return false;
        if (paymentStatus != order.paymentStatus) return false;
        if (orderStatusId != order.orderStatusId) return false;
        if (dateOfCreation != null ? !dateOfCreation.equals(order.dateOfCreation) : order.dateOfCreation != null)
            return false;
        if (dateOfSale != null ? !dateOfSale.equals(order.dateOfSale) : order.dateOfSale != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (int) paymentMethod;
        result = 31 * result + (int) deliveryMethod;
        result = 31 * result + (paymentStatus ? 1 : 0);
        result = 31 * result + (int) orderStatusId;
        result = 31 * result + (dateOfCreation != null ? dateOfCreation.hashCode() : 0);
        result = 31 * result + (dateOfSale != null ? dateOfSale.hashCode() : 0);
        return result;
    }
}
