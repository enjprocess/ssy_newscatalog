package com.shengsiyuan.imis.exception;

import com.shengsiyuan.imis.util.MessageHelper;

public class DaoException extends Exception {

    private int errCode;

    public DaoException(int errCode) {
        super(MessageHelper.getExceptionMessagebyErrCode(errCode));
        this.errCode = errCode;
    }
    
    public DaoException(int errCode, Throwable cause) {
        super(MessageHelper.getExceptionMessagebyErrCode(errCode), cause);
        this.errCode = errCode;
    }
    
    public DaoException(String message) {
        super(message);
    }
    
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public DaoException(Throwable cause) {
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
