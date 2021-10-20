package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 版      权 :  jariec.com
 * 包      名 :  com.example.demo.aspect.AspectComponent
 * 描      述 :  执行顺序 Around->Before->method->Around->After->AfterReturning/AfterThrowing
 * Around中执行proceed方法（执行方法获取结果）后开始阻塞，之后执行Before，再执行具体方法，后再回到Around，Around执行完执行After
 * 创 建 时 间 : 2021/10/14 16:25
 *
 * @author :  张伟
 */
@Aspect
@Component
//@Order(1)
public class AspectComponent {

    @Pointcut(value = "@annotation(com.example.demo.aspect.MyAnnotation)")
    public void pointcut(){}

//    @Around("pointcut()")
//    public void around(ProceedingJoinPoint joinPoint){
//        String methodName = joinPoint.getSignature().getName();
//        Object[] args = joinPoint.getArgs();
//        System.out.println("方法名称:" + methodName);
//        int i = 1;
//        for (Object arg : args) {
//            System.out.println("第" + i + "个参数：" + arg);
//        }
//        try {
//            Object result = joinPoint.proceed();
//            System.out.println("方法返回：" + result);
//            return result;
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        return null;
//    }

    /**
     * ProceedingJoinPoint 只能用在@Around
     * @param joinPoint
     * @param myAnnotation
     * @return
     */
    @Around("@annotation(myAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint, MyAnnotation myAnnotation){
        String annotationValue = myAnnotation.value();
        System.out.println(annotationValue);
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("方法名称:" + methodName);
        int i = 1;
        for (Object arg : args) {
            System.out.println("第" + i + "个参数：" + arg);
        }
        try {
            Object result = joinPoint.proceed();
            System.out.println("方法返回：" + result);
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    /**
     * argNames 可以将请求的参数传到切面增强方法中
     * 请求示例：http://localhost:8715/aspect/test?name=jnk&age=10
     * @param joinPoint
     * @param name
     * @param age
     */
    @Before(value = "pointcut() && args(name,age)", argNames = "joinPoint,name,age")
//    @Before("@annotation(MyAnnotation)")
    public void before(JoinPoint joinPoint, String name, Integer age){
        System.out.println("before start");
        String method = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("请求方法：" + method);
        for (Object arg : args) {
            System.out.println("请求参数：" + arg);
        }
        System.out.println("请求参数name：" + name);
        System.out.println("请求参数age：" + age);
        System.out.println("before end");
    }

    @After(value = "@annotation(MyAnnotation) && args(name,age)" , argNames = "joinPoint,name,age")
    public void after(JoinPoint joinPoint, String name, Integer age){
        System.out.println("after start");
        System.out.println("请求方法：" + joinPoint.getSignature().getName());
        System.out.println("入参name:" + name);
        System.out.println("入参age:" + age);
        System.out.println("after end");
    }

    /**
     * argNames 可以将请求的参数传到切面增强方法中
     * 请求示例：http://localhost:8715/aspect/test?name=jnk&age=10
     * @param joinPoint
     * @param object
     * @param name
     * @param age
     */
    @AfterReturning(returning = "object", pointcut = "pointcut() && args(name,age)", argNames = "joinPoint,object,name,age")
//    public void afterReturning(JoinPoint joinPoint){
    public void afterReturning(JoinPoint joinPoint, Object object, String name, Integer age){
        System.out.println("afterReturning start");
        System.out.println("请求方法：" + joinPoint.getSignature().getName());
        Object[] objects = joinPoint.getArgs();
        for (Object o : objects) {
            System.out.println("joinPoint-请求参数：" + o);
        }
        System.out.println("请求入参 name ：" + name);
        System.out.println("请求入参 age ：" + age);
        System.out.println("请求出参：" + object);
        System.out.println("afterReturning end");
    }

}