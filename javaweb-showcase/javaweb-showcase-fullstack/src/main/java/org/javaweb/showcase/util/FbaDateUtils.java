/*
 * $Id: FbaDateUtils.java 137 2017-02-06 06:17:06Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/main/java/org/javaweb/showcase/util/FbaDateUtils.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.util;

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
 * @version $Revision: 137 $, $Date: 2015-7-9 下午2:12:14$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:17:06#$
 */
public class FbaDateUtils extends DateUtils {

  public static int getYear(Date date) {
    return getFragment(date, Calendar.YEAR);
  }
  
  public static int getMonth(Date date) {
    return getFragment(date, Calendar.MONTH) + 1;
  }
  
  public static int getDayOfMonth(Date date) {
    return getFragment(date, Calendar.DAY_OF_MONTH);
  }
  
  public static int getDayOfYear(Date date) {
    return getFragment(date, Calendar.DAY_OF_YEAR);
  }
  
  public static int getDayOfWeek(Date date) {
    return getFragment(date, Calendar.DAY_OF_WEEK);
  }
  
  public static int getHour(Date date) {
    return (int)getFragmentInHours(date, Calendar.DAY_OF_MONTH);
  }
  
  public static int getMinute(Date date) {
    return (int)getFragmentInMinutes(date, Calendar.HOUR_OF_DAY);
  }
  
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
  public static Date getTodayStart() {
    return getDayBegin(new Date());
  }
  
  /**
   * 获取当天的结束时间
   * @return
   */
  public static Date getTodayEnd() {
    return getDayEnd(new Date());
  }
  
  /**
   * 获取指定日期当天的起始时间。<br>
   * 如：2015-01-01 12:09:10:120 则返回 2015-01-01 00:00:00:000
   * @param date
   * @return
   */
  public static Date getDayBegin(Date date) {
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
  public static Date getDayEnd(Date date) {
    Date d = date;
    
    d = DateUtils.setHours(d, 23);
    d = DateUtils.setMinutes(d, 59);
    d = DateUtils.setSeconds(d, 59);
    d = DateUtils.setMilliseconds(d, 0);
    
    return d;
  }
  
  /**
   * 获取指定日期时间的小时开始时间<br>
   * 如：2015-01-01 12:09:10:120 则返回 2015-01-01 12:00:00:000
   * @param date
   * @return
   */
  public static Date getHourBegin(Date date) {
    Date d = date;
    
    d = DateUtils.setMinutes(d, 0);
    d = DateUtils.setSeconds(d, 0);
    d = DateUtils.setMilliseconds(d, 0);
    
    return d;
  }
  
  /**
   * 获取指定日期时间的小时结束时间<br>
   * 如：2015-01-01 12:09:10:120 则返回 2015-01-01 12:59:59:999
   * @param date
   * @return
   */
  public static Date getHourEnd(Date date) {
    Date d = date;
    
    d = DateUtils.setMinutes(d, 59);
    d = DateUtils.setSeconds(d, 59);
    d = DateUtils.setMilliseconds(d, 999);
    
    return d;
  }
  
  /**
   * 获取指定日期之间相差的天数
   * @param begin
   * @param end
   * @return 不足一天按1天算；多于一天少于2天按2天算；end < begin 返回 -1；
   */
  public static int getDays(Date begin, Date end) {
    long dayMill = 24 * 60 * 60 * 1000;
    
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
  
  private static void splitDate(Date bt, Date et, Map<Date, Date> dates) {
    if(FbaDateUtils.compareTime(bt, et, Calendar.DAY_OF_MONTH) == 0) {
      if(FbaDateUtils.getHour(bt) == 0 && FbaDateUtils.getMinute(bt) == 0 && FbaDateUtils.getSecond(bt) == 0) {
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

  public static void main(String[] args) {
    Date date1 = new Date();
    date1 = DateUtils.addDays(date1, -8);
    
    Date date2 = new Date();
    
    System.out.println(FbaDateUtils.getTodayStart());
    System.out.println(FbaDateUtils.getTodayEnd());
    
    System.out.println(FbaDateUtils.getDayBegin(date1));
    System.out.println(FbaDateUtils.getDayEnd(date1));
    
    System.out.println(FbaDateUtils.compareTime(date1, date2, Calendar.DAY_OF_MONTH));
    
    int actualMaximum = FbaDateUtils.getActualMaximum(date1, Calendar.DAY_OF_MONTH);
    
    System.out.println(actualMaximum);
    
    Date now = new Date();
    
    System.out.println(DateFormatUtils.format(now, "yyyy-MM-dd HH:mm:ss"));
    now = DateUtils.setDays(now, actualMaximum);
    System.out.println(DateFormatUtils.format(DateUtils.addMonths(now, -1), "yyyy-MM-dd HH:mm:ss"));
    
 // 1431857467
    System.out.println(DateFormatUtils.format(new Date(1431858036000l), "yyyy-MM-dd HH:mm:ss"));
  }
  
}
