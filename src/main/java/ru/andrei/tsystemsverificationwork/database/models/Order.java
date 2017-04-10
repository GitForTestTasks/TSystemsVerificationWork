package ru.andrei.tsystemsverificationwork.database.models;

import ru.andrei.tsystemsverificationwork.database.models.enums.DeliveryMethod;
import ru.andrei.tsystemsverificationwork.database.models.enums.PaymentStatus;
import ru.andrei.tsystemsverificationwork.database.models.enums.OrderStatus;
import ru.andrei.tsystemsverificationwork.database.models.enums.PaymentMethod;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order implements Serializable {
    private long orderId;
    private PaymentMethod paymentMethod;
    private DeliveryMethod deliveryMethod;
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
    private Timestamp dateOfCreation;
    private Timestamp dateOfSale;
    private Client clientId;
    private ClientAddress clientAddressId;
    private List<OrderDetail> orderDetails;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId")
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }


    @Basic
    @Column(name = "PaymentMethod")
    @Enumerated(EnumType.STRING)
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    @Basic
    @Column(name = "DeliveryMethod")
    @Enumerated(EnumType.STRING)
    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }


    @Basic
    @Column(name = "PaymentStatus", length = 20, columnDefinition = "varchar(20) default 'NOT_PAID'", nullable = false)
    @Enumerated(EnumType.STRING)
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


    @Basic
    @Column(name = "OrderStatus", length = 20, columnDefinition = "varchar(20) default 'NOT_PAID'", nullable = false)
    @Enumerated(EnumType.STRING)
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
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

    @ManyToOne
    @JoinColumn(name = "ClientId")
    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    @ManyToOne
    @JoinColumn(name = "ClientAddressId")
    public ClientAddress getClientAddressId() {
        return clientAddressId;
    }

    public void setClientAddressId(ClientAddress clientAddressId) {
        this.clientAddressId = clientAddressId;
    }

    @OneToMany(mappedBy = "orderDetailPk.order")
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

        return orderId == order.orderId;
    }

    @Override
    public int hashCode() {
        return (int) (orderId ^ (orderId >>> 32));
    }

    @Override
    public String toString() {
        return " Payment method = " + paymentMethod +
                " Delivery method = " + deliveryMethod +
                " Payment status = " + paymentStatus +
                " Order status = " + orderStatus +
                " Date = " + dateOfCreation;
    }
}
