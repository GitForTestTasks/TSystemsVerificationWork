package ru.andrei.tsystemsverificationwork.web.exceptions.impl;

import ru.andrei.tsystemsverificationwork.web.exceptions.GenericException;

/**
 * Out of stock exception
 */
public class OutOfStockException extends GenericException {

    private static final String EXCEPTION_CODE = "Out of stock";

    public OutOfStockException(String errMsg) {
        super(EXCEPTION_CODE, errMsg);
    }
}
