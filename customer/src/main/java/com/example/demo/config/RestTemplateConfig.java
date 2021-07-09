package com.example.demo.config;

//import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import feign.Feign;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.support.DefaultServerCodecConfigurer;
import org.springframework.web.client.RestTemplate;

/**
 * 版      权 :  jariec.com
 * 包      名 :  com.example.demo.config.RestTemplateConfig
 * 描      述 :
 * 创 建 时 间 : 2021/7/5 15:16
 *
 * @author :  张伟
 */
@Configuration
public class RestTemplateConfig {

    /**
     * Spring Boot<=1.3 无需定义，Spring Boot自动为您定义了一个。
     * Spring Boot >= 1.4 Spring Boot不再自动定义一个RestTemplate，
     * 而是定义了一个RestTemplateBuilder允许您更好地控制所RestTemplate创建的对象
     * @param restTemplateBuilder
     * @return
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.build();
    }

    /**
     * 在openFiegn 中使用fallback
     * @return
     */
//    @Bean
//    @Scope("prototype")
//    public Feign.Builder feignBuilder() {
//        return Feign.builder();
//    }

//    @Bean
//    public ServletRegistrationBean hystrixStreamServlet(){
//        ServletRegistrationBean registration = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
//        registration.addUrlMappings("/hystrix.stream");
//        return registration;
//    }

    /**
     * 配置gateway
     * @return
     */
    @Bean
    public ServerCodecConfigurer serverCodecConfigurer(){
        return new DefaultServerCodecConfigurer();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}