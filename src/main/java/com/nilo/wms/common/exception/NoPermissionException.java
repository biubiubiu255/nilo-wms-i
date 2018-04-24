package com.nilo.wms.common.exception;

/**
 * Created by ronny on 2017/8/23.
 */
public class NoPermissionException extends RuntimeException {
    private static final long serialVersionUID = -8615103443717498766L;

    public NoPermissionException() {
        super("No Permission");
    }

    public String getMessage() {
        return super.getMessage();
    }
}
