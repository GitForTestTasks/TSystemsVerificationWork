package ru.andrei.tsystemsverificationwork.database.models.enums;

/**
 * Enum of payment method
 */
public enum PaymentMethod {
    CARD,
    CASH;

    @Override
    public String toString() {
        switch (this) {
            case CARD:
                return "card";
            case CASH:
                return "cash";
            default:
                throw new IllegalArgumentException();
        }
    }
}
