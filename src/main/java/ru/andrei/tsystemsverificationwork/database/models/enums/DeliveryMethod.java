package ru.andrei.tsystemsverificationwork.database.models.enums;

/**
 * Created by Andrei on 4/1/2017.
 */
public enum DeliveryMethod {
    STANDART,
    NEXT_DAY_DELIVERY,
    NOMINATED_DAY,
    POINT_OF_DELIVERY;

    @Override
    public String toString() {
        switch (this) {
            case STANDART:
                return "standart";
            case NEXT_DAY_DELIVERY:
                return "next day delivery";
            case NOMINATED_DAY:
                return "nominated day";
            case POINT_OF_DELIVERY:
                return "point of delivery";
            default:
                throw new IllegalArgumentException();
        }
    }

}
