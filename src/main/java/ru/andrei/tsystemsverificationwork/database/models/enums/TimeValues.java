package ru.andrei.tsystemsverificationwork.database.models.enums;

/**
 * Enum of time period
 */
public enum TimeValues {
    MONTH,
    WEEK;

    @Override
    public String toString() {
        switch (this) {
            case MONTH:
                return "31";
            case WEEK:
                return "7";
            default:
                throw new IllegalArgumentException();
        }
    }
}

