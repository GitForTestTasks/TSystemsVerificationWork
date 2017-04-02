package ru.tsystemsverificationwork.database.models.enums;

/**
 * Created by Andrei on 4/1/2017.
 */
public enum PaymentStatus {
    NOT_PAID,
    PAID;

    @Override
    public String toString() {
        switch(this) {
            case NOT_PAID: return "not paid";
            case PAID: return "paid";
            default: throw new IllegalArgumentException();
        }
    }
}
