package com.jason.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * Created by macro on 2023/4/8.
 */
@Configuration
@MapperScan("com.jason.mbg.mapper")
public class MyBatisConfig {
}
