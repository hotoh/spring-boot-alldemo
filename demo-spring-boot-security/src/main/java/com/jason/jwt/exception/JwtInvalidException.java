package com.jason.jwt.exception;

/**
 * Created by jason on 2020/6/23.
 */
public class JwtInvalidException extends RuntimeException{
    public JwtInvalidException(String message) {
        super(message);
    }
}
