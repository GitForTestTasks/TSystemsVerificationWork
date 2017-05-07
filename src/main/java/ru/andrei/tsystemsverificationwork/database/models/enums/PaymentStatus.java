package ru.andrei.tsystemsverificationwork.database.models.enums;

/**
 * Enum of payment status
 */
public enum PaymentStatus {
    NOT_PAID,
    PAID;

    @Override
    public String toString() {
        switch (this) {
            case NOT_PAID:
                return "not paid";
            case PAID:
                return "paid";
            default:
                throw new IllegalArgumentException();
        }
    }
}
