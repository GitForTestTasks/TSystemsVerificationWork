package ru.tsystemsverificationwork.web.models;

import ru.tsystemsverificationwork.web.models.enums.DeliveryMethod;
import ru.tsystemsverificationwork.web.models.enums.OrderStatus;
import ru.tsystemsverificationwork.web.models.enums.PaymentMethod;
import ru.tsystemsverificationwork.web.models.enums.PaymentStatus;

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
    @Column(name = "PaymentStatus")
    @Enumerated(EnumType.STRING)
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


    @Basic
    @Column(name = "OrderStatus")
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

        if (orderId != order.orderId) return false;
        if (paymentMethod != order.paymentMethod) return false;
        if (deliveryMethod != order.deliveryMethod) return false;
        if (paymentStatus != order.paymentStatus) return false;
        if (orderStatus != order.orderStatus) return false;
        if (!dateOfCreation.equals(order.dateOfCreation)) return false;
        return dateOfSale != null ? dateOfSale.equals(order.dateOfSale) : order.dateOfSale == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + paymentMethod.hashCode();
        result = 31 * result + deliveryMethod.hashCode();
        result = 31 * result + paymentStatus.hashCode();
        result = 31 * result + orderStatus.hashCode();
        result = 31 * result + dateOfCreation.hashCode();
        result = 31 * result + (dateOfSale != null ? dateOfSale.hashCode() : 0);
        return result;
    }
}
