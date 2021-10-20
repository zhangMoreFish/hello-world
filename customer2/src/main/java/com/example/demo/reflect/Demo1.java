package com.example.demo.reflect;

/**
 * 版      权 :  jariec.com
 * 包      名 :  com.example.demo.reflect.Demo1
 * 描      述 :
 * 创 建 时 间 : 2021/10/16 10:50
 *
 * @author :  张伟
 */
public class Demo1 {

    public void m1() throws ClassNotFoundException {
        Class clazz = Class.forName("");
    }

    public void m2(){
        Class clazz = Object.class;
    }

    public void m3(){
        Object object = new Object();
        Class clazz = object.getClass();
    }
}