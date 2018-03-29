package com.nilo.wms.common.exception;

/**
 * Created by ronny on 2017/7/11.
 */
public enum ExceptionType {
    SYSTEM_ERROR,
    BIZ_VERIFY_ERROR,
    BIZ_ERROR;

    private ExceptionType() {
    }

    public String getCode() {
        return this.name();
    }
}