package com.shengsiyuan.imis.exception;

import com.shengsiyuan.imis.util.MessageHelper;

public class ServiceException extends Exception {

    private int errCode;

    public ServiceException(int errCode) {
        super(MessageHelper.getExceptionMessagebyErrCode(errCode));
        this.errCode = errCode;
    }
    
    public ServiceException(int errCode, Throwable cause) {
        super(MessageHelper.getExceptionMessagebyErrCode(errCode), cause);
        this.errCode = errCode;
    }
    
    public ServiceException(String message) {
        super(message);
    }
    
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ServiceException(Throwable cause) {
        super(cause);
    }
    
    /**
     * 其实通过Exception的getMessage也可以获得异常信息
     */
    public String getErrorMessage() {
        return MessageHelper.getExceptionMessagebyErrCode(errCode);
    }
    
    public int getErrCode() {
        return errCode;
    }

}
