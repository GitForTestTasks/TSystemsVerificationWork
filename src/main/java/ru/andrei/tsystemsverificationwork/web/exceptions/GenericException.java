package ru.andrei.tsystemsverificationwork.web.exceptions;


public abstract class GenericException extends RuntimeException {

    private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public GenericException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

}