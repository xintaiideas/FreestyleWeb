package com.freestyle.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间处理工具
 * @author Leo Lien
 * 2016年10月7日 下午8:20:43 创建
 */
public class DateUtil {
    
    public static final String YYYY_MM_DD_24H_MM_SS = "yyyy-MM-dd HH:mm:ss";
    
    public static final String YYYY_MM_DD_12H_MM_SS = "yyyy-MM-dd hh:mm:ss";
    
    public static final String YYYY_MM_DD_24H_MM = "yyyy-MM-dd HH:mm";
    
    public static final String YYYY_MM_DD_12H_MM = "yyyy-MM-dd hh:mm";
    
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    
    public static final String TIME_24H_MM_SS = "HH:mm:ss";
    
    public static final String TIME_12H_MM_SS = "hh:mm:ss";
    
    public static final String TIME_24H_MM = "HH:mm";
    
    public static final String TIME_12H_MM = "hh:mm";

    /**
     * 格式化时间
     * @author Leo Lien
     * 2016年10月7日 下午8:21:28 创建
     * @param date  时间
     * @param format    格式
     * @return  格式化后的字符串
     */
    public static String format(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    
    /**
     * 按指定格式来格式化当前时间
     * @author Leo Lien
     * 2016年10月18日 下午11:36:49 创建
     * @param format
     * @return
     */
    public static String formatCurrentDate(String format) {
        return format(new Date(), format);
    }
    
    /**
     * 以YYYY_MM_DD_24H_MM_SS格式化当前时间
     * @author Leo Lien
     * 2016年10月18日 下午11:36:35 创建
     * @return
     */
    public static String formatCurrentDate() {
        return format(new Date(), YYYY_MM_DD_24H_MM_SS);
    }
    
    /**
     * 将时间字符串解析为时间对象
     * @author Leo Lien
     * 2016年10月7日 下午8:22:24 创建
     * @param dateStr   时间字符串
     * @param format    格式
     * @return  时间对象
     * @throws ParseException 
     */
    public static Date parse(String dateStr, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateStr);
    }
    
    /**
     * 减去指定天数
     * @author Leo Lien
     * 2016年11月17日 下午2:22:28 创建
     * @param date
     * @param days
     * @return
     */
    public static Date subDays(Date date, int days) {
        long time = date.getTime() - (days * 24 * 60 * 60 * 1000);
        return new Date(time);
    }
    
    /**
     * 添加指定天数
     * @author Leo Lien
     * 2016年11月17日 下午2:22:59 创建
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, int days) {
        long time = date.getTime() + (days * 24 * 60 * 60 * 1000);
        return new Date(time);
    }
    
    /**
     * 修改为凌晨
     * @author Leo Lien
     * 2016年11月17日 下午3:00:23 创建
     * @param date
     * @return
     */
    public static Date change0H0M0S(Date mDate) {
        Date date = (Date) mDate.clone(); 
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return date;
    }
    
    /**
     * 修改为午夜
     * @author Leo Lien
     * 2016年11月17日 下午3:01:37 创建
     * @param date
     * @return
     */
    public static Date change23H59M59S(Date mDate) {
        Date date = (Date) mDate.clone(); 
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
        return date;
    }
}
