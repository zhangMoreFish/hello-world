package com.example.demo.test;

import com.example.demo.feign.ProviderServerFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 版      权 :  jariec.com
 * 包      名 :  com.example.demo.test.controller
 * 描      述 :
 * 创 建 时 间 : 2021/7/5 14:52
 *
 * @author :  张伟
 */
@RestController
@RequestMapping("/test")
public class controller {

    Logger logger = LoggerFactory.getLogger(controller.class);

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProviderServerFeign providerServerFeign;//编译器提示错误 不影响启动

    //要调用的服务名
//    final String serverId = "provider-server";
    //当前服务名
    final String currentServerId = "customer-server";

    /**
     * restTemplate 方式
     * @param name
     * @return
     */
    @RequestMapping("/get/info/{name}")
    public String getInfo(@PathVariable("name") String name){
        if(logger.isInfoEnabled()) logger.info("logger info {}", name);
        if(logger.isDebugEnabled()) logger.debug("logger debugger {}", name);
        if(logger.isErrorEnabled()) logger.error("logger error {}", name);
        List<String> list = discoveryClient.getServices();
        String serviceId = list.stream().filter(s -> !s.equalsIgnoreCase(currentServerId)).findFirst().get();
//        String serviceId = list.get(0);
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceId);
        ServiceInstance serviceInstance = serviceInstances.get(0);
        String url = String.format("http://%s:%s/test/hello?str=%s", serviceInstance.getHost(), serviceInstance.getPort(), name);
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(String.format("url:%s,result:%s",url, result));
        return result;
    }

    /**
     * openFeign 方式
     * @param name
     * @return
     */
    @RequestMapping("/feign/get/info/{name}")
    public String getInfo2(@PathVariable("name") String name){
        return providerServerFeign.hello(name);
    }

    /**
     * 熔断测试 需开启@EnableHystrix
     * fallbackMethod = "hystrixFaild" 触发熔断后执行的兜底方法
     * @return
     */
    @GetMapping("/hystrix")
    @HystrixCommand(fallbackMethod = "hystrixFaild", commandProperties = {
            //这个服务熔断的意思是10s内请求10次失败率达到60%就熔断
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")// 失败率达到多少后跳闸
    })
    public String hystrix(){
        if(1 == 1){
            throw new RuntimeException("auth exception");
        }
        return "success";
    }

    /**
     * 触发熔断后执行的兜底方法
     * @return
     */
    public String hystrixFaild(){
        return "hystrix fallbackMethod return";
    }

}