package com.jason.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author :jason
 * @date : 2023/1/31
 */
@Configuration
@MapperScan({"com.jason.mapper"})
public class MyBatisConfig {
}
