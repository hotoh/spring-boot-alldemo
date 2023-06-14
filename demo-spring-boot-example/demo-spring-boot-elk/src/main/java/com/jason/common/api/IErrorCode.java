package com.jason.common.api;

/**
 * 封装API的错误码
 * Created by macro on 2023/4/19.
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
