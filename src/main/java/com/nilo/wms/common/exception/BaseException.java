package com.nilo.wms.common.exception;

import java.text.MessageFormat;

/**
 * Created by ronny on 2017/8/23.
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -8615103443717498766L;

    private final String code;
    private final String description;

    public BaseException(String code, String description) {
        super(description);
        this.code = code;
        this.description = description;
    }

    public BaseException(String code, String description, String... args) {
        super(MessageFormat.format(description, args));
        this.code = code;
        this.description = MessageFormat.format(description, args);
    }

    public BaseException(String code, String description, Throwable e) {
        super(description, e);
        this.code = code;
        this.description = description;
    }

    public String getMessage() {
        return null == super.getMessage() ? this.description : super.getMessage();
    }

    public String getCode() {
        return this.code;
    }

}
