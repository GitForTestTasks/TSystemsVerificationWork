package ru.tsystemsverificationwork.database.models.enums;


public enum OrderStatus {
    NOT_PAID,
    AWAITING_SHIPMENT,
    SHIPPING,
    DELIVERED;

    @Override
    public String toString() {
        switch(this) {
            case NOT_PAID: return "not paid";
            case AWAITING_SHIPMENT: return "awaiting shipment";
            case DELIVERED: return "delivered";
            case SHIPPING: return "shipping";
            default: throw new IllegalArgumentException();
        }
    }
}
