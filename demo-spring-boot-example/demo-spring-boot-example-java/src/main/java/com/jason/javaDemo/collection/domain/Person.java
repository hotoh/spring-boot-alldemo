package com.jason.javaDemo.collection.domain;

/**
 * @author :jason
 * @date : 2022/8/22
 */
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
    private int id;
    private String name;
    private int age;
}

