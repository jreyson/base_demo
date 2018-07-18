package com.ms.base.domain;

/**
 * @author JamelHuang
 * @date 2018/3/6
 */
public class SendBackMsg {
    private String errorCode;
    private String errorMsg;

    public SendBackMsg(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public SendBackMsg() {
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
