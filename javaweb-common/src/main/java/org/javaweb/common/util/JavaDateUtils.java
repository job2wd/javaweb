/*
 * $Id: JavaDateUtils.java 5096 2018-08-07 09:31:09Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaDateUtils.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.common.util;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;



/**
 * FBA 日期时间工具类。
 * 
 * @version $Revision: 5096 $, $Date: 2015-7-9 下午2:12:14$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-08-07 17:31:09#$
 */
public class JavaDateUtils extends DateUtils {

  /**
   * 解析日期字符串
   * 
   * @param dateStr 日期字符串
   * @param parsePatterns 字符串格式，可不提供，当不提供时，该方法会尝试匹配不同格式，最后返回解析后的日期对象。
   * @return
   */
  public static Date parseDate(String dateStr, String... parsePatterns) {
    if(org.apache.commons.lang3.StringUtils.isBlank(dateStr)) {
      throw new IllegalArgumentException("invalid argument 'dateStr'");
    }
    
    String[] ps = new String[]{
        "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH",
        "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH",
        "yyyyMMddHHmmss", "yyyyMMdd", "yyyyMMdd HH:mm:ss", "yyyyMM",
        "yyyy年MM月dd日 HH时mm分ss秒", "yyyy年MM月dd日", "yyyy年MM月dd日 HH时mm分", "yyyy年MM月dd日 HH时",
        "HH:mm:ss", "HH:mm", "yyyy", "MM", "dd", "HH", "mm", "ss",
        "yyyy-MM-dd+HH:mm:ss", "yyyy/MM/dd+HH:mm:ss"};
    
    if(parsePatterns != null && parsePatterns.length > 0) {
      ps = parsePatterns;
    }
    
    try {
      return DateUtils.parseDate(dateStr, ps);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }
  
  /**
   * yyyyMMddHHmmss 格式表示的日期，解析为 java.util.Date 类型日期。
   * @param date
   * @return
   */
  public static Date parseDate(Long date) {
    return JavaDateUtils.parseDate(date.toString());
  }
  
  /**
   * 获取指定日期所在的年份
   * @param date
   * @return
   */
  public static int getYear(Date date) {
    return getFragment(date, Calendar.YEAR);
  }
  
  /**
   * 获取指定日期所在的月份（1~12）
   * @param date
   * @return
   */
  public static int getMonth(Date date) {
    return getFragment(date, Calendar.MONTH) + 1;
  }
  
  /**
   * 获取指定日期在当月所在的天数（1~31）
   * @param date
   * @return
   */
  public static int getDayOfMonth(Date date) {
    return getFragment(date, Calendar.DAY_OF_MONTH);
  }
  
  /**
   * 获取指定日期在当年所在的天数（1~365/366）
   * @param date
   * @return
   */
  public static int getDayOfYear(Date date) {
    return getFragment(date, Calendar.DAY_OF_YEAR);
  }
  
  /**
   * 获取指定日期是本周的周几（注意：返回的数值为从周一开始，即周一为1，周二为2....周日为7）
   * @param date
   * @return
   */
  public static int getDayOfWeek(Date date) {
    final int week = getFragment(date, Calendar.DAY_OF_WEEK) - 1;
    return week == 0 ? 7 : week;
  }
  
  /**
   * 获取指定时间所在的小时数（0~23）
   * @param date
   * @return
   */
  public static int getHour(Date date) {
    return (int)getFragmentInHours(date, Calendar.DAY_OF_MONTH);
  }
  
  /**
   * 获取指定时间所在的分钟数（0~59）
   * @param date
   * @return
   */
  public static int getMinute(Date date) {
    return (int)getFragmentInMinutes(date, Calendar.HOUR_OF_DAY);
  }
  
  /**
   * 获取指定时间所在的秒数（0~59）
   * @param date
   * @return
   */
  public static int getSecond(Date date) {
    return (int)getFragmentInSeconds(date, Calendar.MINUTE);
  }
  
  private static int getFragment(Date date, int field) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    
    return calendar.get(field);
  }
  
  /**
   * 比较 date1 和 date2。lastField 指明了日期中最后需要比较的字段，如：<br>
   * <li>若 lastField 为 Calendar.YEAR，则比较 date1 和 date2 的年份是否相同</li>
   * <li>若 lastField 为 Calendar.MONTH，则首先比较 date1 和 date2 的年份是否相同，若相同，则继续比较月份是否相同</li>
   * <li>若 lastField 为 Calendar.DAY_OF_MONTH，则首先比较 date1 和 date2 的年份是否相同，若相同，则继续比较月份是否相同，若月份也相同，则继续比较日是否相同</li>
   * 
   * @param date1
   * @param date2
   * @param lastField 必须为
   * {Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND}
   * 中的任意一个值
   * @return 若 date1 > date2 return >0; date1 == date2 return =0; date1 < date2 return <0;
   */
  public static int compareTime(Date date1, Date date2, int lastField) {
    int[] a = {Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND};
    
    
    if(Arrays.binarySearch(a, lastField) < 0) {
      throw new IllegalArgumentException("illegal argument 'lastField':" + lastField);
    }
    
    int year1 = getYear(date1);
    int month1 = getMonth(date1);
    int day1 = getDayOfMonth(date1);
    int hour1 = getHour(date1);
    int minute1 = getMinute(date1);
    int second1 = getSecond(date1);
        
    int year2 = getYear(date2);
    int month2 = getMonth(date2);
    int day2 = getDayOfMonth(date2);
    int hour2 = getHour(date2);
    int minute2 = getMinute(date2);
    int second2 = getSecond(date2);
        
    if(lastField == Calendar.YEAR) {
      return year1 - year2;
    }
    
    if(lastField == Calendar.MONTH) {
      if(year1 - year2 == 0) {
        return month1 - month2;
      } else {
        return year1 - year2;
      }
    }
    
    if(lastField == Calendar.DAY_OF_MONTH) {
      if(year1 - year2 == 0) {
        if(month1 - month2 == 0) {
          return day1 - day2;
        } else {
          return month1 - month2;
        }
      } else {
        return year1 - year2;
      }
    }
    
    if(lastField == Calendar.HOUR_OF_DAY) {
      if(year1 - year2 == 0) {
        if(month1 - month2 == 0) {
          if(day1 - day2 == 0) {
            return hour1 - hour2;
          } else {
            return day1 - day2;
          }
        } else {
          return month1 - month2;
        }
      } else {
        return year1 - year2;
      }
    }
    
    if(lastField == Calendar.MINUTE) {
      if(year1 - year2 == 0) {
        if(month1 - month2 == 0) {
          if(day1 - day2 == 0) {
            if(hour1 - hour2 == 0) {
              return minute1 - minute2;
            } else {
              return hour1 - hour2;
            }
          } else {
            return day1 - day2;
          }
        } else {
          return month1 - month2;
        }
      } else {
        return year1 - year2;
      }
    }
    
    if(lastField == Calendar.SECOND) {
      if(year1 - year2 == 0) {
        if(month1 - month2 == 0) {
          if(day1 - day2 == 0) {
            if(hour1 - hour2 == 0) {
              if(minute1 - minute2 == 0) {
                return second1 - second2;
              } else {
                return minute1 - minute2;
              }
            } else {
              return hour1 - hour2;
            }
          } else {
            return day1 - day2;
          }
        } else {
          return month1 - month2;
        }
      } else {
        return year1 - year2;
      }
    }
    
    throw new IllegalArgumentException("illegal argument date1 and date2.");
  }
  
  public static int getActualMaximum(Date date, int field) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    return c.getActualMaximum(field);
  }
  
  /**
   * 判断 date2 是否为 date1 连续几天之前或连续几天之后的日期
   * @param date1
   * @param date2
   * @param days 指定了连续多少天
   * @return
   */
  public static boolean isContinuityDate(Date date1, Date date2, int days) {
    Date d = addDays(date1, days);
    return isSameDay(d, date2);
  }
  
  /**
   * 获取当天的起始时间
   * @return
   */
  public static Date getBeginOfToday() {
    return getBeginOfDay(new Date());
  }
  
  /**
   * 获取当天的结束时间
   * @return
   */
  public static Date getEndOfToday() {
    return getEndOfDay(new Date());
  }
  
  /**
   * 获取指定日期当天的起始时间。<br>
   * 如：2015-01-01 12:09:10:120 则返回 2015-01-01 00:00:00:000
   * @param date
   * @return
   */
  public static Date getBeginOfDay(Date date) {
    Date d = date;
    
    d = DateUtils.setHours(d, 0);
    d = DateUtils.setMinutes(d, 0);
    d = DateUtils.setSeconds(d, 0);
    d = DateUtils.setMilliseconds(d, 0);
    
    return d;
  }
  
  /**
   * 获取指定日期当天的结束时间。<br>
   * 如：2015-01-01 12:09:10:120 则返回 2015-01-01 23:59:59:000
   * @param date
   * @return
   */
  public static Date getEndOfDay(Date date) {
    Date d = date;
    
    d = DateUtils.setHours(d, 23);
    d = DateUtils.setMinutes(d, 59);
    d = DateUtils.setSeconds(d, 59);
    d = DateUtils.setMilliseconds(d, 0);
    
    return d;
  }
  
  /**
   * 获取指定时间的小时开始时间（即将分、秒、毫秒都置为0）<br>
   * 如：2015-01-01 12:09:10:120 则返回 2015-01-01 12:00:00:000
   * @param date
   * @return
   */
  public static Date getBeginOfHour(Date date) {
    Date d = date;
    
    d = DateUtils.setMinutes(d, 0);
    d = DateUtils.setSeconds(d, 0);
    d = DateUtils.setMilliseconds(d, 0);
    
    return d;
  }
  
  /**
   * 获取指定时间的小时结束时间（即将分、秒、毫秒都置为59:59:999）<br>
   * 如：2015-01-01 12:09:10:120 则返回 2015-01-01 12:59:59:999
   * @param date
   * @return
   */
  public static Date getEndOfHour(Date date) {
    Date d = date;
    
    d = DateUtils.setMinutes(d, 59);
    d = DateUtils.setSeconds(d, 59);
    d = DateUtils.setMilliseconds(d, 999);
    
    return d;
  }
  
  /**
   * 获取指定日期之间相差的年份数
   * @param begin
   * @param end
   * @return 不足一年按1年算；多于一年少于2年按2年算；end < begin 返回 -1；
   */
  public static int getYears(Date begin, Date end) {
    if(begin.getTime() > end.getTime()) {
      return -1;
    }
    
    return getYear(end) - getYear(begin);
  }
  
  /**
   * 获取指定日期之间相差的月份数
   * @param begin
   * @param end
   * @return 不足一月按1月算；多于一月少于2月按2月算；end < begin 返回 -1；
   */
  public static int getMonths(Date begin, Date end) {
    Date _begin = begin;
    int months = 0;
    
    if(begin.getTime() > end.getTime()) {
      return -1;
    }
    
    while(true) {
      _begin = addMonths(begin, months);
      
      if(getYear(_begin) == getYear(end)) {
        if(getMonth(_begin) >= getMonth(end)) {
          break;
        }
      }
      
      months++;
    }
    
    return months;
  }
  
  /**
   * 获取指定日期之间相差的天数
   * @param begin
   * @param end
   * @return 不足一天按1天算；多于一天少于2天按2天算；end < begin 返回 -1；
   */
  public static int getDays(Date begin, Date end) {
    return getCha(begin, end, 24 * 60 * 60 * 1000);
  }

  /**
   * 获取指定日期之间相差的小时数
   * @param begin
   * @param end
   * @return 不足一小时按1小时算；多于一小时少于2小时按2小时算；end < begin 返回 -1；
   */
  public static int getHours(Date begin, Date end) {
    return getCha(begin, end, 60 * 60 * 1000);
  }
  
  /**
   * 获取指定日期之间相差的分钟数
   * @param begin
   * @param end
   * @return 不足一分钟按1分钟算；多于一分钟少于2分钟按2分钟算；end < begin 返回 -1；
   */
  public static int getMinutes(Date begin, Date end) {
    return getCha(begin, end, 60 * 1000);
  }
  
  /**
   * 获取指定日期之间相差的秒数
   * @param begin
   * @param end
   * @return 不足一秒按1秒算；多于一秒少于2秒按2秒算；end < begin 返回 -1；
   */
  public static int getSeconds(Date begin, Date end) {
    return getCha(begin, end, 1000);
  }
  
  /**
   * 获取指定日期所在月份的第一天
   * @param date
   * @return
   */
  public static Date getFirstOfMonth(Date date) {
    return DateUtils.setDays(date, 1);
  }
  
  /**
   * 获取指定日期所在月份的最后一天
   * @param date
   * @return
   */
  public static Date getLastOfMonth(Date date) {
    return DateUtils.setDays(date, JavaDateUtils.getActualMaximum(date, Calendar.DAY_OF_MONTH));
  }
  
  /**
   * 时间截取 --- 将给定的起始时间到结束时间段按天进行分隔，分隔的起始时间和结束时间放到给定的 Map 中并返回。<br><br>
   * <b>注：返回的分隔后的日期时间段中：</b>
   * <li>1. 和起始时间（bt）同一天的时间，其起始时间（key）的时分秒和传入的起始时间参数（bt）的时分秒相同，结束时间（value）的时分秒为23:59:59</li>
   * <li>2. 和结束时间（et）同一天的时间，其起始时间（key）的时分秒为00:00:00，结束时间（value）和传入的结束时间参数（et）的时分秒相同</li>
   * <li>3. 非起始时间和结束时间同一天的时间，其起始时间（key）的时分秒都为00:00:00，结束时间（value）的时分秒为23:59:59</li>
   * 
   * @param bt 要分隔的时间段的开始时间
   * @param et 要分隔的时间段的结束时间
   * @return 分隔后的日期结果。key - 开始时间；value - 结束时间。
   */
  public static Map<Date, Date> splitDate(Date bt, Date et) {
    Map<Date, Date> dates = new LinkedHashMap<Date, Date>();
    
    splitDate(bt, et, dates);
    
    return dates;
  }
  
  /**
   * 获取给定日期所在周的周一的日期
   * @param date
   * @return
   */
  public static Date getMondayOfWeek(Date date) {
    return DateUtils.addDays(date, -(JavaDateUtils.getDayOfWeek(date) - 1));
  }
  
  /**
   * 获取给定日期所在周的周日的日期
   * @param date
   * @return
   */
  public static Date getSundayOfWeek(Date date) {
    return DateUtils.addDays(date, (7 - JavaDateUtils.getDayOfWeek(date)));
  }
    
  /**
   * 获取指定日期为汉语中的周几，如：周一、周日等
   * @param date 日期
   * @return
   */
  public static String getWeekOfChinese(Date date) {
    int week = JavaDateUtils.getDayOfWeek(date);
    switch(week) {
      case 1: return "周一";
      case 2: return "周二";
      case 3: return "周三";
      case 4: return "周四";
      case 5: return "周五";
      case 6: return "周六";
      case 7: return "周日";
    }
    return "";
  }
  
  /**
   * 获取指定日期为汉语中的星期几，如：星期一、星期日等
   * @param date 日期
   * @return
   */
  public static String getWeekOfChineseFormat(Date date) {
    return DateFormatUtils.format(date, "E");
  }
  
  private static int getCha(Date begin, Date end, long dayMill) {
    long beginTime = begin.getTime();
    long endTime = end.getTime();
    
    if(endTime < beginTime) {
      return -1;
    }
    
    long cha = endTime - beginTime;
    int day = (int)((cha) / dayMill);
    long mod =  cha % dayMill;
        
    while(mod >= dayMill) {
      mod = mod % dayMill;
      day++;
    }
    
    if(mod > 0 && mod <= dayMill) {
      day++;
    }
    
    return day;
  }
  
  private static void splitDate(Date bt, Date et, Map<Date, Date> dates) {
    if(JavaDateUtils.compareTime(bt, et, Calendar.DAY_OF_MONTH) == 0) {
      if(JavaDateUtils.getHour(bt) == 0 && JavaDateUtils.getMinute(bt) == 0 && JavaDateUtils.getSecond(bt) == 0) {
        dates.put(DateUtils.setSeconds(DateUtils.setMinutes(DateUtils.setHours(et, 0), 0), 0), et);
      } else {
        dates.put(bt, et);
      }
      return;
    } else {
      dates.put(bt, DateUtils.setSeconds(DateUtils.setMinutes(DateUtils.setHours(bt, 23), 59), 59));
      splitDate(DateUtils.addDays(DateUtils.setSeconds(DateUtils.setMinutes(DateUtils.setHours(bt, 0), 0), 0), 1), et, dates);
    }
  }

  /**
   * 获取汉语标识的时间长度，如：10 天 2 小时 50 分 25 秒 120 毫秒
   * @param begin
   * @param end
   * @return
   */
  public static String getDateChineseFormat(Date begin, Date end) {
    long milliseconds = end.getTime() - begin.getTime();
    
    if(milliseconds <= 0) {
      return "小于1毫秒";
    }
    
    if(milliseconds < 1000) {//小于1秒钟
      return milliseconds + " 毫秒";
    }
    
    long seconds = milliseconds / 1000;
    long milliseconds_mod = milliseconds % 1000;
    
    if(seconds < 60) {//小于1分钟
      return seconds + " 秒 " + milliseconds_mod + " 毫秒";
    }
    
    long minutes = milliseconds / (60 * 1000);
        
    if(minutes < 60) {//小于1小时
      milliseconds_mod = milliseconds % (60 * 1000);
      seconds = milliseconds_mod / 1000;
      milliseconds_mod = milliseconds_mod % 1000;
      
      return minutes + " 分 " + seconds + " 秒 " + milliseconds_mod + " 毫秒";
    }
    
    long hours = milliseconds / (60 * 60 * 1000);
    
    if(hours < 24) {//小于1天
      milliseconds_mod = milliseconds % (60 * 60 * 1000);
      minutes = milliseconds_mod / (60 * 1000);
      milliseconds_mod = milliseconds_mod % (60 * 1000);
      seconds = milliseconds_mod / 1000;
      milliseconds_mod = milliseconds_mod % 1000;
      
      return hours + " 小时 " + minutes + " 分 " + seconds + " 秒 " + milliseconds_mod + " 毫秒";
    }
        
    long days = milliseconds / (24 * 60 * 60 * 1000);
    
    milliseconds_mod = milliseconds % (24 * 60 * 60 * 1000);
    hours = milliseconds_mod / (60 * 60 * 1000);
    milliseconds_mod = milliseconds % (60 * 60 * 1000);
    minutes = milliseconds_mod / (60 * 1000);
    milliseconds_mod = milliseconds_mod % (60 * 1000);
    seconds = milliseconds_mod / 1000;
    milliseconds_mod = milliseconds_mod % 1000;
    
    return  days + " 天 " + hours + " 小时 " + minutes + " 分  " + seconds + " 秒 " + milliseconds_mod + " 毫秒";
  }
    
  public static void main(String[] args) {
    Date date1 = new Date();
    date1 = DateUtils.addDays(date1, -8);
    
    Date date2 = new Date();
    
    System.out.println(JavaDateUtils.getBeginOfToday());
    System.out.println(JavaDateUtils.getEndOfToday());
    
    System.out.println(JavaDateUtils.getBeginOfDay(date1));
    System.out.println(JavaDateUtils.getEndOfDay(date1));
    
    System.out.println(JavaDateUtils.compareTime(date1, date2, Calendar.DAY_OF_MONTH));
    
    int actualMaximum = JavaDateUtils.getActualMaximum(date1, Calendar.DAY_OF_MONTH);
    
    System.out.println(actualMaximum);
    
    Date now = new Date();
    
    System.out.println(DateFormatUtils.format(now, "yyyy-MM-dd HH:mm:ss"));
    now = DateUtils.setDays(now, actualMaximum);
    System.out.println(DateFormatUtils.format(DateUtils.addMonths(now, -1), "yyyy-MM-dd HH:mm:ss"));
    
 // 1431857467
    System.out.println(DateFormatUtils.format(new Date(1431858036000l), "yyyy-MM-dd HH:mm:ss"));
    
    System.out.println("2018-05-28 11:20:43:380 ~ 2018-05-28 11:22:13:336");
    String cdesc = getDateChineseFormat(JavaDateUtils.parseDate("20180528112043380", "yyyyMMddHHmmssSSS"),
        JavaDateUtils.parseDate("20180528112213336", "yyyyMMddHHmmssSSS"));
    System.out.println(cdesc);
    
  }
  
}
