package com.nilo.wms.common.exception;

/**
 * Created by ronny on 2017/7/11.
 */
public interface ErrorCode {
    ExceptionType getType();

    String getCode();

    String getDescription();
}