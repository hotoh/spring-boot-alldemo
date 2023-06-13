package com.jason.annotation;

import com.jason.advice.EncryptRequestBodyAdvice;
import com.jason.advice.EncryptResponseBodyAdvice;
import com.jason.config.RSAUtilsConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Author:jason
 * DateTime:2019/4/9 16:44
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import({RSAUtilsConfig.class,
        EncryptResponseBodyAdvice.class,
        EncryptRequestBodyAdvice.class})
public @interface EnableSecurity{

}
