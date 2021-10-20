package com.example.demo.test;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * 版      权 :  jariec.com
 * 包      名 :  com.example.demo.test.OptinalTest
 * 描      述 :  Optional test
 * 创 建 时 间 : 2021/7/16 17:55
 *
 * @author :  张伟
 */
public class OptinalTest {

    public static void main(String[] args) {
//        t1();
//        m2();
        m22();
//        m3();
//        m4();
    }

    public static void t1(){
        User user = null;
        Optional<User> optionalUser = Optional.empty();
        System.out.println(Optional.ofNullable(user).orElseGet(() -> new User("tom",1)).getAge());
        //user为null抛异常
        try {
            optionalUser = Optional.of(user);
        }catch (NullPointerException e){

        }
        //user为null返回空optional
        optionalUser = Optional.ofNullable(user);
        //optionalUser中的user为null时执行orElse，不为null也会执行
        optionalUser.orElse(new User());
        //optionalUser中的user为null时执行orElseGet，不为null不会执行
        optionalUser.orElseGet(() -> new User());
        //isPresent 是否已经设置了值 ，user 为null返回false
        boolean bol1 = Optional.ofNullable(user).isPresent();
        //isPresent 是否已经设置了值 ，user 不为null返回true
        boolean bol2 = Optional.ofNullable(new User()).isPresent();
        System.out.println(bol1+ "," + bol2);
        //ifPresent不为null时执行ifPresent中的表达式
        System.out.println("*****************");
        Optional.ofNullable(user).ifPresent(u -> System.out.println("ifPresent:" + u.getAge()));
        Optional.ofNullable(new User("jack",10)).ifPresent(u -> System.out.println("ifPresent:" + u.getAge()));
        System.out.println("*****************");

        //过滤不符合条件返回空的optional  返回false
        System.out.println(Optional.ofNullable(new User("tom", 11)).filter(u->u.getAge()==10).isPresent());
        //过滤符合条件继续返回optional  返回false
        System.out.println(Optional.ofNullable(new User("tom", 10)).filter(u->u.getAge()==10).isPresent());
        //返回9
        System.out.println(Optional.ofNullable(new User("zhang", 10)).map(u->u.getAge()-1).get());
//        System.out.println(Optional.ofNullable(new User("zhang", 10)).flatMap(u->u.getAge()-1).get());
    }

    /**
     * of   为null时抛异常
     */
    public static void m2(){
        User user = null;
        try {
            Optional<User> optionalUser = Optional.of(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * ofNullable  为null时不会抛异常
     */
    public static void m22(){
        User user = null;
        try {
            Optional<User> optionalUser = Optional.ofNullable(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 输出两次 new    orElse无论optional是否为null都会执行
     */
    public static void m3(){
        User user = new User();
        Optional<User> optionalUser = Optional.ofNullable(user);
        try {
            optionalUser.orElse(new User());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 只输出一次 new
     */
    public static void m4(){
        User user = new User();
        Optional<User> optionalUser = Optional.ofNullable(user);
        optionalUser.orElseGet(() -> new User());
    }

}
class User{
    private String name;
    private int age;

    public User(){
        System.out.println("new");
    }
    public User(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}