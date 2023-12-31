package com.jason.config;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jason on 2023/8/29.
 */
@Configuration
public class RibbonConfig {

    @Bean
    @SentinelRestTemplate
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
