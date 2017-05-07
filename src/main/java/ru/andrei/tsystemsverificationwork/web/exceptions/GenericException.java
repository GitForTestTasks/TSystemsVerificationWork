package ru.andrei.tsystemsverificationwork.web.exceptions;


/**
 * Generic exception extends RuntimeException is used
 * for all custom exceptions thrown at the application.
 * Creates level of abstraction that easier to catch at
 * ControllerAdvice.
 */
public abstract class GenericException extends RuntimeException {

    private final String errCode;
    private final String errMsg;

    public GenericException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

}
