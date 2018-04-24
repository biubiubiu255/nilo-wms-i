package com.nilo.wms.common.exception;

import java.text.MessageFormat;

/**
 * Created by ronny on 2017/8/23.
 */
public class IllegalTokenException extends RuntimeException {
    private static final long serialVersionUID = -8615103443717498766L;

    public IllegalTokenException() {
        super("Illegal token");
    }
    public String getMessage() {
        return super.getMessage();
    }
}
