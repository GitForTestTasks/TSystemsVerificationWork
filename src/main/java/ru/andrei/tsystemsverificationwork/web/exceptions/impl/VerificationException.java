package ru.andrei.tsystemsverificationwork.web.exceptions.impl;


import ru.andrei.tsystemsverificationwork.web.exceptions.GenericException;

/**
 * Verification exception is thrown when user is not allowed to access
 * requested information
 */
public class VerificationException extends GenericException {

    public VerificationException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
