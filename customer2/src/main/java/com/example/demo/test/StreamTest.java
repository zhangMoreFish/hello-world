package com.example.demo.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Stream;

/**
 * 版      权 :  jariec.com
 * 包      名 :  com.example.demo.test.StreamTest
 * 描      述 :
 * 创 建 时 间 : 2021/7/17 14:27
 *
 * @author :  张伟
 */
public class StreamTest {

    public static void m1(){
        List<String> list = new ArrayList<>(0);
        //顺序流
        Stream<String> stream1 = list.stream();
        //并行流
        Stream<String> stream2 = list.parallelStream();

        Stream<String> stream3 = Arrays.stream(new String[]{});
    }

    public static void m2(){
        Stream<Integer> stream4 = Stream.of(1,2,3,4,5,6);
        Stream<Integer> stream5 = Stream.iterate(0, (x) -> x+2).limit(6);
    }

    public static void m3() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(""));
        Stream<String> stream = reader.lines();
        stream.forEach(System.out::println);
    }

    public static void m4(){
        Stream<Integer> stream = Stream.of(3,4,1,6,4,7,8,3);
        stream.filter(s -> s > 3)//过滤大于3
                .distinct()//去重 根据hashCode和equals
                .skip(2)//跳过n个元素
                .limit(3).forEach(System.out::println);//取前n个元素
    }

    public static void m5(){
        Map<String, Integer> map = new HashMap<>(2);
        map.put("a", 10);
        map.put("b", 20);

        List<Map<String, Integer>> list = new ArrayList<>(1);
        list.add(map);

        list.stream().peek(m -> m.put("a", 200)).forEach(m-> System.out.println(m.get("a")));
//        list.stream().forEach(m-> m.put("a", 200));
        System.out.println(list.get(0).get("a"));
    }

    public static void m6(){
        Stream<Integer> stream = Stream.of(5,3,8,1,4,9);
//        System.out.println(stream.max(Integer::compareTo).get());
//        System.out.println(stream.min(Integer::compareTo).get());

//        Stream<String> stream1 = Stream.of("5","3","8","1","4","9");
//        stream1.max(Integer::compareTo).get();
//        stream1.min(String::compareTo).get();
    }

    public static void main(String[] args) {
//        m6();


        TestIn testIn = () -> System.out.println(123);


    }


}