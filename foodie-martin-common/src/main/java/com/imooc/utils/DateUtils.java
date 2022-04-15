package com.imooc.utils;

import com.imooc.exception.BizException;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by renfeng on 2020/8/5.
 */
public class DateUtils {

    public static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static String yyyy_MM_dd = "yyyy-MM-dd";
    public static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static String MMddHHmmss = "MMddHHmmss";

    public static String yyyyMMdd_ = "yyyy.MM.dd";

    public static String format(LocalDateTime localDateTime){
        if (localDateTime == null) {
            return null;
        }
        String format = localDateTime.format(DateTimeFormatter.ofPattern(yyyy_MM_dd_HH_mm_ss));
        return format;
    }

    public static String formatYyyyMmdd(LocalDateTime localDateTime){
        if (localDateTime == null) {
            return null;
        }
        String format = localDateTime.format(DateTimeFormatter.ofPattern(yyyy_MM_dd));
        return format;
    }

    public static String formatYyyyMmdd_(LocalDateTime localDateTime){
        if (localDateTime == null) {
            return null;
        }
        String format = localDateTime.format(DateTimeFormatter.ofPattern(yyyyMMdd_));
        return format;
    }

    public static String format(Date date){
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
        String format = simpleDateFormat.format(date);
        return format;
    }

    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        pattern = StringUtils.isEmpty(pattern) ? yyyy_MM_dd_HH_mm_ss : pattern;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String format = simpleDateFormat.format(date);
        return format;
    }

    /**
     * 字符串转date
     *
     * @param date
     * @param pattern 默认 "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Date str2Date(String date, String pattern) {
        SimpleDateFormat simpleDateFormat
                = new SimpleDateFormat(StringUtils.isNotEmpty(pattern) ? pattern : "yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            throw new BizException("字符串转date异常", e);
        }
        return parse;
    }

    /**
     * 字符串转date
     *
     * @param date "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Date str2Date(String date) {
        return str2Date(date, null);
    }


    /**
     * 获取当前时间 yyyy-MM-dd HH:mm:ss
     *
     * @return String
     */
    public static String getNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String getNowYmdhms() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(yyyyMMddHHmmss));
    }

    /**
     * 获取当前天 yyyyMMdd
     *
     * @return String
     */
    public static String getDay() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    /**
     * 获取当前天-7 yyyyMMdd
     *
     * @return String
     */
    public static String getDayBefore7() {
        return LocalDate.now().minusDays(7).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    /*
     * 负数表示加，正数表示减
     * */
    public static Long getDay(int day) {
        return System.currentTimeMillis() + day * 24 * 60 * 60 * 1000;
    }


    /**
     * 计算两个日期之间相差的天数 t2-t1
     *
     * @param t1 t1
     * @param t2 t2
     * @return int
     */
    public static int getDiffDays(LocalDateTime t1, LocalDateTime t2) {
        Calendar t1Calendar = Calendar.getInstance();
        Calendar t2Calendar = Calendar.getInstance();
        t1Calendar.setTime(org.apache.commons.lang3.time.DateUtils.truncate(Timestamp.valueOf(t1), 5));
        t2Calendar.setTime(org.apache.commons.lang3.time.DateUtils.truncate(Timestamp.valueOf(t2), 5));
        int days = 0;
        while (t1Calendar.before(t2Calendar)) {
            days++;
            t1Calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        while (t2Calendar.before(t1Calendar)) {
            days--;
            t2Calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return days;
    }

    /**
     * 计算两个日期之间相差的分钟数 t2-t1
     *
     * @param t1 t1
     * @param t2 t2
     * @return long
     */
    public static long getDiffMin(LocalDateTime t1, LocalDateTime t2) {
        return (Timestamp.valueOf(t2).getTime() - Timestamp.valueOf(t1).getTime()) / 60000;
    }

    /**
     * 计算两个日期之间相差的秒数 target- now
     */
    public static long getDiffSeconds(LocalDateTime now, LocalDateTime target) {
        Long seconds = Duration.between(now, target).toMillis() / 1000;
        return seconds;
    }


    /**
     * yyyy-MM-dd 00:00:00
     */
    public static LocalDateTime getStartOfDay() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime time = now.withHour(0).withMinute(0).withSecond(0).withNano(0);
        return time;
    }

    public static LocalDateTime getStartOfDay(LocalDateTime time) {
        if (time == null) {
            return null;
        }
        LocalDateTime time2 = time.withHour(0).withMinute(0).withSecond(0).withNano(0);
        return time2;
    }

    public static LocalDateTime getEndOfDay() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime time = now.withHour(23).withMinute(59).withSecond(59).withNano(0);
        return time;
    }

    public static LocalDateTime getEndOfDay(LocalDateTime time) {
        if (time == null) {
            return null;
        }
        LocalDateTime time2 = time.withHour(23).withMinute(59).withSecond(59).withNano(0);
        return time2;
    }

    /**
     * 获取月的第一天
     *
     * @param month 月份
     * @return 月的第一天
     */
    public static LocalDate getFirstDayOfMonth(String month) {
        LocalDate date = LocalDate.parse(month + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime dateTime = date.atStartOfDay();
        LocalDateTime firstday = dateTime.with(TemporalAdjusters.firstDayOfMonth());
        return firstday.toLocalDate();
    }

    public static LocalDateTime getFirstDayOfMonth() {
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime of = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), 1, 0, 0, 0, 0);
        return of;
    }


    public static LocalDateTime getLastDayOfMonth() {
        LocalDateTime firstDayOfMonth = getFirstDayOfMonth();
        LocalDateTime time = firstDayOfMonth.plusMonths(1).minusSeconds(1);
        return time;
    }

    public static LocalDateTime getFirstDayOfPreMonth() {
        LocalDateTime dateTime = LocalDateTime.now();
        dateTime = dateTime.minusMonths(1l);
        LocalDateTime of = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), 1, 0, 0, 0, 0);
        of.minusMonths(1);
        return of;
    }


    public static LocalDateTime getLastDayOfPreMonth() {
        LocalDateTime firstDayOfMonth = getFirstDayOfPreMonth();
        LocalDateTime time = firstDayOfMonth.plusMonths(1).minusSeconds(1);
        return time;
    }

    public static LocalDateTime getFirstDayOfNextMonth() {
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime of = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), 1, 0, 0, 0, 0);
        of.plusMonths(1);
        return of;
    }


    public static LocalDateTime getLastDayOfNextMonth() {
        LocalDateTime firstDayOfMonth = getFirstDayOfMonth();
        LocalDateTime time = firstDayOfMonth.plusMonths(1).minusSeconds(1);
        time.plusMonths(1);
        return time;
    }

    public static LocalDateTime getFirstDayOfMonthTime(String yyyy_MM_dd_HH_mm_ss) {
        LocalDateTime dateTime = LocalDateTime.parse(yyyy_MM_dd_HH_mm_ss, DateTimeFormatter.ofPattern(DateUtils.yyyy_MM_dd_HH_mm_ss));
        LocalDateTime of = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), 1, 0, 0, 0, 0);
        return of;
    }

    public static LocalDateTime getLastDayOfMonthTime(String yyyy_MM_dd_HH_mm_ss) {
        LocalDateTime firstDayOfMonthTime = getFirstDayOfMonthTime(yyyy_MM_dd_HH_mm_ss);
        LocalDateTime time = firstDayOfMonthTime.plusMonths(1).minusSeconds(1);
        return time;
    }

    public static LocalDateTime getFirstDayOfPreMonthTime(String yyyy_MM_dd_HH_mm_ss) {
        LocalDateTime dateTime = LocalDateTime.parse(yyyy_MM_dd_HH_mm_ss, DateTimeFormatter.ofPattern(DateUtils.yyyy_MM_dd_HH_mm_ss));
        LocalDateTime of = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), 1, 0, 0, 0, 0);
        of.minusMonths(1);
        return of;
    }

    public static LocalDateTime getLastDayOfPreMonthTime(String yyyy_MM_dd_HH_mm_ss) {
        LocalDateTime firstDayOfMonthTime = getFirstDayOfMonthTime(yyyy_MM_dd_HH_mm_ss);
        LocalDateTime time = firstDayOfMonthTime.plusMonths(1).minusSeconds(1);
        time.minusMonths(1);
        return time;
    }

    public static LocalDateTime getFirstDayOfNextMonthTime(String yyyy_MM_dd_HH_mm_ss) {
        LocalDateTime dateTime = LocalDateTime.parse(yyyy_MM_dd_HH_mm_ss, DateTimeFormatter.ofPattern(DateUtils.yyyy_MM_dd_HH_mm_ss));
        LocalDateTime of = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), 1, 0, 0, 0, 0);
        of.plusMonths(1);
        return of;
    }

    public static LocalDateTime getLastDayOfNextMonthTime(String yyyy_MM_dd_HH_mm_ss) {
        LocalDateTime firstDayOfMonthTime = getFirstDayOfMonthTime(yyyy_MM_dd_HH_mm_ss);
        LocalDateTime time = firstDayOfMonthTime.plusMonths(1).minusSeconds(1);
        time.plusMonths(1);
        return time;
    }


    public static LocalDateTime getFirstDayOfMonthTime(LocalDateTime dateTime) {
        LocalDateTime of = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), 1, 0, 0, 0, 0);
        return of;
    }

    public static LocalDateTime getLastDayOfMonthTime(LocalDateTime dateTime) {
        LocalDateTime firstDayOfMonthTime = getFirstDayOfMonthTime(dateTime);
        LocalDateTime time = firstDayOfMonthTime.plusMonths(1).minusSeconds(1);
        return time;
    }

    public static LocalDateTime getFirstDayOfPreMonthTime(LocalDateTime dateTime) {
        LocalDateTime of = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), 1, 0, 0, 0, 0);
        of.minusMonths(1);
        return of;
    }

    public static LocalDateTime getLastDayOfPreMonthTime(LocalDateTime dateTime) {
        LocalDateTime firstDayOfMonthTime = getFirstDayOfMonthTime(dateTime);
        LocalDateTime time = firstDayOfMonthTime.plusMonths(1).minusSeconds(1);
        time.minusMonths(1);
        return time;
    }

    public static LocalDateTime getFirstDayOfNextMonthTime(LocalDateTime dateTime) {
        LocalDateTime of = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), 1, 0, 0, 0, 0);
        of.plusMonths(1);
        return of;
    }

    public static LocalDateTime getLastDayOfNextMonthTime(LocalDateTime dateTime) {
        LocalDateTime firstDayOfMonthTime = getFirstDayOfMonthTime(dateTime);
        LocalDateTime time = firstDayOfMonthTime.plusMonths(1).minusSeconds(1);
        time.plusMonths(1);
        return time;
    }




    /**
     * 获取月的最后一天
     *
     * @param month 月份
     * @return 月的最后一天
     */
    public static LocalDate getLastDayOfMonth(String month) {
        LocalDate date = LocalDate.parse(month + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime dateTime = date.atStartOfDay();
        LocalDateTime lastDay = dateTime.with(TemporalAdjusters.lastDayOfMonth());
        return lastDay.toLocalDate();
    }



    /**
     * 获取月所有日期
     *
     * @param firstDayOfMonth 月第一天
     * @param lastDayOfMonth  月最后一天
     * @return 日期集合
     */
    public static List<String> getDayList(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth) {
        List<String> dayList = newArrayList();
        int index = 0;
        while (true) {
            LocalDate localDate = firstDayOfMonth.plusDays(index);
            if (localDate.compareTo(lastDayOfMonth) > 0) {
                break;
            }
            dayList.add(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            index++;
        }
        return dayList;
    }

    public static String getLastMonth(int n){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -n);
        Date m = c.getTime();
        String month = format.format(m);
        return month;
    }

    /**
     * 今天的开始时间
     * @return
     */
    public static LocalDateTime getFirstTimeOfDay(){
        return LocalDateTime.of(LocalDate.now() , LocalTime.MIN);
    }

    /**
     * 今天的结束时间
     * @return
     */
    public static LocalDateTime getLastTimeOfDay(){
        return LocalDateTime.of(LocalDate.now() , LocalTime.MAX);

    }

    /**
     * 本月的开始时间
     * @return
     *//*
    public static LocalDateTime getFirstDayOfMonth(){
        return LocalDateTime.of(LocalDate.from(LocalDateTime.now()
                .with(TemporalAdjusters.firstDayOfMonth())), LocalTime.MIN);
    }

    *//**
     * 本月的结束时间
     *//*
    public static LocalDateTime getLastDayOfMonth(){
        return LocalDateTime.of(LocalDate.from(LocalDateTime.now()
                .with(TemporalAdjusters.lastDayOfMonth())), LocalTime.MAX);
    }*/

    /**
     * 今年的开始时间
     * @return
     */
    public static LocalDateTime getFirstDayOfYear(){
        return LocalDateTime.of(LocalDate.from(LocalDateTime.now()
                .with(TemporalAdjusters.firstDayOfYear())), LocalTime.MIN);
    }

    /**
     * 今年的结束时间
     * @return
     */
    public static LocalDateTime getLastDayOfYear(){
        return LocalDateTime.of(LocalDate.from(LocalDateTime.now()
                .with(TemporalAdjusters.lastDayOfYear())), LocalTime.MAX);
    }

    /**
     * @Description: 获取当前年份的后两位
     * @Param:
     * @return:
     * @Author: Martin~
     * @Date: 2022/2/18 3:38 下午
     */
    public static String getLastYearOfTwoNum(){
        String year = new SimpleDateFormat("yy", Locale.CHINESE).format(new Date());
        return year;
    }

    public static String getNewEquipmentNo(String equipmentType, String equipmentNo){

        String newEquipmentNo = "00001";

        if(equipmentNo != null && !equipmentNo.isEmpty()){

            int newEquipment = Integer.parseInt(equipmentNo) + 1;

            newEquipmentNo = String.format(equipmentType + "%05d", newEquipment);

        }

        return newEquipmentNo;

    }

    public static void main(String[] args) {
//        LocalDateTime firstDayOfMonth = getFirstDayOfMonth();
//        System.out.println(firstDayOfMonth);
//        String lastDayOfMonth = getLastMonth(1);
        System.out.println(getLastYearOfTwoNum());

        String equipmentNo = getNewEquipmentNo(getLastYearOfTwoNum(), "00033");

        System.out.println("生成设备编号：" + equipmentNo);

        String maxCode = "2200013";
        String newCode = null;
        if (StringUtils.isEmpty(maxCode)) {
            newCode = DateUtils.getLastYearOfTwoNum()+"00001";
        } else {
            //切割字符串，取查到编号的最后两位
            newCode = String.valueOf(Integer.parseInt(maxCode)+1);
        }
        System.out.println("newCode：" + newCode);

    }



}
