package com.nilo.wms.common.exception;

/**
 * Created by ronny on 2017/8/23.
 */
public class WMSException extends BaseException {
    private static final long serialVersionUID = -8615103443717498766L;

    private final ErrorCode resultCode;

    public WMSException(ErrorCode resultCode) {
        super(resultCode.getCode(), resultCode.getDescription());
        this.resultCode = resultCode;
    }

    public WMSException(ErrorCode resultCode, String ... args) {
        super(resultCode.getCode(), resultCode.getDescription(),args);
        this.resultCode = resultCode;
    }

    @Override
    public String toString() {
        StringBuilder retValue = new StringBuilder("WMSException[");
        if (resultCode != null) {
            retValue.append("type=").append(resultCode.getType()).append(',');
            retValue.append("code=").append(resultCode.getCode()).append(',');
            retValue.append("description=").append(resultCode.getDescription()).append(',');
        }
        retValue.append(']');
        return retValue.toString();
    }
}
