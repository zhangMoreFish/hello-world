package com.example.demo.test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

/**
 * 版      权 :  jariec.com
 * 包      名 :  com.example.demo.test.LocalDateDemo
 * 描      述 :
 * 创 建 时 间 : 2021/8/19 15:13
 *
 * @author :  张伟
 */
public class LocalDateDemo {
    public static void main(String[] args) {
        m1();
        m2();
        m3();
        m4();
    }
    public static void m1(){
        LocalDate localDate = LocalDate.now();
//        System.out.println(localDate.toString());
//        LocalDate localDate2 = LocalDate.of(2021,8,18);
//        System.out.println(localDate2.toString());
//        System.out.println("getYear:" + localDate.getYear());
//        System.out.println("YEAR:" + localDate.get(ChronoField.YEAR));
//        System.out.println("getMonth:" + localDate.getMonth());
//        System.out.println("getMonthValue:" + localDate.getMonthValue());
//        System.out.println("getMonth.getValue:" + localDate.getMonth().getValue());
//        System.out.println("MONTH_OF_YEAR:" + localDate.get(ChronoField.MONTH_OF_YEAR));
//        System.out.println("getDayOfMonth:" + localDate.getDayOfMonth());
//        System.out.println("DAY_OF_MONTH:" + localDate.get(ChronoField.DAY_OF_MONTH));
//        System.out.println("getDayOfWeek:" + localDate.getDayOfWeek());
//        System.out.println("DAY_OF_WEEK:" + localDate.get(ChronoField.DAY_OF_WEEK));

        //格式化
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println(df.format(localDate));
        System.out.println(LocalDate.parse("2021/08/14", df));
        System.out.println(localDate.isLeapYear());//是否闰年
        System.out.println("original:" + localDate);
        LocalDate localDatePlus = localDate.plusDays(1);//加一天
        System.out.println("plus:" + localDatePlus);
        LocalDate localDateMinus = localDate.minusDays(1);//减一天
        System.out.println("minus:" + localDateMinus);

        System.out.println("******");
//        System.out.println(Instant.from(LocalDateTime.now()).toEpochMilli());
        System.out.println(System.currentTimeMillis());//print 1629366728065
        System.out.println(Instant.now().toEpochMilli());//print 1629366728065 = System.currentTimeMillis()
        System.out.println(Instant.now().getEpochSecond());// print 1629366728
        System.out.println("******");
    }
    public static void m2(){
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime.toString());
    }
    public static void m3(){
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.plusDays(1);
        localDateTime.minusDays(1);
        System.out.println(localDateTime.toString());
    }
    public static void m4(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println(calendar.toString());
    }
    public static Date convert(LocalDateTime localDateTime){
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }
    public static LocalDateTime convert(Date date){
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime;
    }
}