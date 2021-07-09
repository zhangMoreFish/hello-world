package com.example.demo.feign;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 版      权 :  jariec.com
 * 包      名 :  com.example.demo.feign.ProviderServerFeignFallbackFactory
 * 描      述 :
 * 创 建 时 间 : 2021/7/5 15:42
 *
 * @author :  张伟
 */
@Component
public class ProviderServerFeignFallbackFactory implements FallbackFactory<ProviderServerFeignFallbackFactory.ProviderServerFeignImpl> {
    @Override
    public ProviderServerFeignImpl create(Throwable cause) {
        return new ProviderServerFeignImpl(cause);
    }
//    public ProviderServerFeign create(Throwable cause) {
//        String message = cause.getMessage();
//        return str -> str + ",fallback message:" + message;
////        return new ProviderServerFeign() {
////            @Override
////            public String hello(String str) {
////                return str + "," + message;
////            }
////        };
//    }

    class ProviderServerFeignImpl implements ProviderServerFeign{
        Throwable cause;
        public ProviderServerFeignImpl(Throwable cause){
            this.cause = cause;
        }
        @Override
        public String hello(String str) {
            return str + "," +cause.getMessage();
        }

//        @Override
//        public String hello2(String name) {
//            return null;
//        }
    }
}
