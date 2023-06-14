package com.jason.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jason
 * @date 2023/04/04
 */
@Data
public class Message<T> implements Serializable {
    private String Id;
    private T content;
}
