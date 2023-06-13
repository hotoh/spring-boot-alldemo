package com.jason.message;


import lombok.Data;

import java.io.Serializable;

/**
 * @author jason
 * @date 2019/04/04
 */
@Data
public class MessageTransaction<T> extends Message<T>  implements Serializable  {
    private String aId;
    private String bId;
    private T content;
}
