package com.jason.jwt.exception;

/**
 * Created by jason on 2020/6/23.
 */
public class JwtExpiredException extends RuntimeException{
    public JwtExpiredException(String message) {
        super(message);
    }
}
