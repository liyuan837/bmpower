package com.liyuan.bmpower.domain.exception;

public class bmpowerException extends RuntimeException {

    private static final long serialVersionUID = -6202759931628287239L;
    private static final int DEFAULT_ERROR_CODE = 201;

    /* 错误码,用于返回接口code */
    private int errCode;

    public bmpowerException() {
        super();
    }

    public bmpowerException(String msg) {
        super(msg);
        this.errCode = DEFAULT_ERROR_CODE;
    }

    public bmpowerException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
    }

    public bmpowerException(String msg, Throwable e) {
        super(msg, e);
        this.errCode = DEFAULT_ERROR_CODE;
    }

    public bmpowerException(int errCode, String msg, Throwable e) {
        super(msg, e);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }
}