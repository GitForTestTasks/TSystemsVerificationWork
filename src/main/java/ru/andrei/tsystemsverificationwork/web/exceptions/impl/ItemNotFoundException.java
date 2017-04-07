package ru.andrei.tsystemsverificationwork.web.exceptions.impl;

import ru.andrei.tsystemsverificationwork.web.exceptions.GenericException;

public class ItemNotFoundException extends GenericException {

    private static final String errCode = "Item not found";

    public ItemNotFoundException(String errMsg) {
        super(errCode, errMsg);
    }
}
