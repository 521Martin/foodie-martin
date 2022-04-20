package com.test;


import com.imooc.enums.FrontOrderStatus;
import com.imooc.utils.DateUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeatilTest {


    public static void main(String[] args) {

        // 支付剩余分钟
        Integer payRemainMins = 15;

        //确认收货剩余天数
        Integer confirmRemainDays= 7;

        LocalDateTime orderTime = LocalDateTime.parse("2022-04-18 11:30:00", DateTimeFormatter.ofPattern(DateUtils.yyyy_MM_dd_HH_mm_ss));

        FrontOrderStatus frontOrderStatus = FrontOrderStatus.PENDING_PAYMENT;

        //填充倒计时时间
//        long diffMins = payRemainMins - DateUtils.getDiffMin(orderTime, LocalDateTime.now());
        long diffMins = 28;
        System.out.println("倒计时时间："+diffMins+"分钟");
        System.out.println(diffMins < 0 ? "0" : diffMins / 60+"小时");
        System.out.println(diffMins < 0 ? "5" : diffMins % 60+"分钟");
        System.out.println(diffMins / 60);
        System.out.println(diffMins % 60);

        String text = String.format(frontOrderStatus.getText(), diffMins < 0 ? "0" : diffMins / 60, diffMins < 0 ? "0" : diffMins % 60);

        System.out.println("展示时间："+text);

    }


}
