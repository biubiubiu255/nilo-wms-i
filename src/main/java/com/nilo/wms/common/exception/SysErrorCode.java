
package com.nilo.wms.common.exception;

/**
 * Created by ronny on 2017/8/23.
 */
public enum SysErrorCode implements ErrorCode {

    /**
     * ================================
     */
    DB_EXCEPTION("DataBase Exception.", "200001"),
    REQUEST_IS_NULL("Request Parameter is null.", "200002"),
    SYSTEM_ERROR("Network Error.Pls try again.", "200003"),;

    private final String description;
    private final String code;

    private SysErrorCode(String description, String code) {
        this.description = description;
        this.code = code;
    }

    @Override
    public ExceptionType getType() {
        return ExceptionType.SYSTEM_ERROR;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

}
