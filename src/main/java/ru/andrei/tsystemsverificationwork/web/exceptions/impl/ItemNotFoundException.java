package ru.andrei.tsystemsverificationwork.web.exceptions.impl;

import ru.andrei.tsystemsverificationwork.web.exceptions.GenericException;

/**
 * Exception is thrown when item is not found
 */
public class ItemNotFoundException extends GenericException {

    private static final String ERR_CODE = "Item not found";

    public ItemNotFoundException(String errMsg) {
        super(ERR_CODE, errMsg);
    }
}
