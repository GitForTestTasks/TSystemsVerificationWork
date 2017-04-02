package ru.tsystemsverificationwork.database.models.enums;


public enum PaymentMethod {
        CARD,
        CASH;

        @Override
        public String toString() {
                switch(this) {
                        case CARD: return "card";
                        case CASH: return "cash";
                        default: throw new IllegalArgumentException();
                }
        }
}
