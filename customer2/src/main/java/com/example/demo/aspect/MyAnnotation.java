package com.example.demo.aspect;

import java.lang.annotation.*;

/**
 * 版      权 :  jariec.com
 * 包      名 :  com.example.demo.aspect.MyAnnotation
 * 描      述 :  自定义注解
 * 创 建 时 间 : 2021/10/14 16:28
 *
 * @author :  张伟
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
//@Inherited
//@Documented
public @interface MyAnnotation {
    String value() default "";
}
