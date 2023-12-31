package com.jason.message;


import lombok.Data;

import java.io.Serializable;

/**
 * @author jason
 * @date 2023/04/04
 */
@Data
public class MessageObjectTransaction<T> extends MessageObject<T> implements Serializable  {
    private String aId;
    private String bId;
    private T content;
}
