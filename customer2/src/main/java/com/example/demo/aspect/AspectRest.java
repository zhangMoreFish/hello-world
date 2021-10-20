package com.example.demo.aspect;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 版      权 :  jariec.com
 * 包      名 :  com.example.demo.aspect.AspectRest
 * 描      述 :
 * 创 建 时 间 : 2021/10/14 16:30
 *
 * @author :  张伟
 */
@RestController
@RequestMapping("/aspect/")
public class AspectRest {

    public static final String PREFIX = "ASPECT: HELLO,";

    /**
     * 测试获取接口的入参和出参
     * @param name
     * @return
     */
    @MyAnnotation("这是测试注解")
    @GetMapping("test")
    public String test(String name, Integer age){
        return PREFIX + name + "," + age;
    }
}