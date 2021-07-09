package com.example.demo.feign;

import com.example.demo.config.RestTemplateConfig;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 版      权 :  jariec.com
 * 包      名 :  com.example.demo.feign.ProviderServerFeign
 * 描      述 :
 * 创 建 时 间 : 2021/7/5 15:42
 *  fallback         指定回退处理类
 *  fallbackFactory  指定回退处理类，包含回退原因
 * @author :  张伟
 */
//value:要调用的服务名，path:相当于类上的@RequestMapping
//@FeignClient(value = "provider-server", fallback = ProviderServerFeignFallBack.class)
@FeignClient(value = "provider-server", fallbackFactory = ProviderServerFeignFallbackFactory.class)
public interface ProviderServerFeign {

    /**
     * RequestParam注解必须写，包括value
     * @param str
     * @return
     */
    @RequestMapping(value = "/test/hello", method = RequestMethod.GET)
    String hello(@RequestParam("str") String str);

//    @RequestMapping(value = "/test/name")
//    String hello2(@RequestParam("name") String name);

//    @RequestLine("GET /test/hello")
//    String hello(@Param("str") String str);
}