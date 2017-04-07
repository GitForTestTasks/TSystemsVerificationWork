package ru.andrei.tsystemsverificationwork.web.exceptions.impl;


import ru.andrei.tsystemsverificationwork.web.exceptions.GenericException;

public class VerificationException extends GenericException {

    public VerificationException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
