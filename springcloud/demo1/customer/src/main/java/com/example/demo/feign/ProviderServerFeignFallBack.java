package com.example.demo.feign;

import org.springframework.stereotype.Component;

/**
 * 版      权 :  jariec.com
 * 包      名 :  com.example.demo.feign.ProviderServerFeignFallBack
 * 描      述 :
 * 创 建 时 间 : 2021/7/5 15:42
 * @author :  张伟
 */
@Component
public class ProviderServerFeignFallBack implements ProviderServerFeign {

    @Override
    public String hello(String str) {
        return "fallback," + str;
    }

//    @Override
//    public String hello2(String name) {
//        return null;
//    }
}