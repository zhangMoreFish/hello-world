package com.example.demo.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 版      权 :  jariec.com
 * 包      名 :  com.example.demo.test.controller
 * 描      述 :
 * 创 建 时 间 : 2021/7/5 14:51
 *
 * @author :  张伟
 */
@RestController
@RequestMapping("/test")
public class controller {

    @RequestMapping("/hello")
    public String hello(@RequestParam(required = false) String str){
        return "hello : " + str;
    }
}