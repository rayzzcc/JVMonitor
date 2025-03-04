package com.ray.jvm.common.enums;

import java.io.Serializable;

/**
 * 描述：异常code定义
 */
public class ErrorCode implements Serializable {
    public static final ErrorCode NOT_FOUND = new ErrorCode("404", "资源未找到！");

    public static final ErrorCode SERVER_ERROR = new ErrorCode("500", "服务器内部错误！");

    public static final ErrorCode BAD_REQUEST = new ErrorCode("400", "非法请求！");

    public static final ErrorCode ILLEGAL_ARGUMENTS = new ErrorCode("4000001", "参数非法");

    public static final ErrorCode DELETE_FORBIDDEN = new ErrorCode("4000002", "禁止删除资源");

    public static final ErrorCode SOURCE_EXISTS = new ErrorCode("4000003", "资源已存在");

    private String code;

    private String message;

    public ErrorCode(String code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }
}
