package com.ray.jvm.common.exception;

import com.ray.jvm.common.enums.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述：自定义异常类
 */
public class RuntimeException extends java.lang.RuntimeException {
    protected Logger LOGGER = LoggerFactory.getLogger(getClass());

    private String code;

    private String message;

    public RuntimeException() {}
    public RuntimeException(Throwable throwable) {
        super(throwable);
    }
    public RuntimeException(String message) {
        super(message);
        this.message = message;
    }
    public RuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }
    public RuntimeException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public RuntimeException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
