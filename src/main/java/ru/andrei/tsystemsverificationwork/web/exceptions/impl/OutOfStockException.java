package ru.andrei.tsystemsverificationwork.web.exceptions.impl;

import ru.andrei.tsystemsverificationwork.web.exceptions.GenericException;


public class OutOfStockException extends GenericException {

    private static final String exceptionCode = "Out of stock";

    public OutOfStockException(String errMsg) {
        super(exceptionCode, errMsg);
    }
}
