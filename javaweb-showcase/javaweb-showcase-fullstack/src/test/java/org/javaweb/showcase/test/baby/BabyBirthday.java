package org.javaweb.showcase.test.baby;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * Baby 生日计算
 * 
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2014-6-26 下午1:10:26$
 * @LastChanged $Author: job2wd $, $Date:: 2017-01-20 12:21:11#$
 */
public class BabyBirthday
{
    public static void main( String[] args )
    {
      Date date = new Date();

      date = DateUtils.setYears(date, 2014);
      date = DateUtils.setMonths(date, 3);
      date = DateUtils.setDays(date, 8);
      date = DateUtils.setHours(date, 3);
      date = DateUtils.setMinutes(date, 55);
      date = DateUtils.setSeconds(date, 0);

      Date now = new Date();
      
      //DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");//DateFormatUtils.ISO_DATE_FORMAT.getPattern() + " " + DateFormatUtils.ISO_TIME_NO_T_FORMAT.getPattern());
      
      System.out.println("出生日期：" + DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss") + "(农历三月初九)");
      System.out.println("当前日期：" + DateFormatUtils.format(now, "yyyy-MM-dd HH:mm:ss"));
      System.out.println("出生天数：" + getBetweenTimes(date, now));//"第 " + (((now.getTime() - date.getTime()) / (24 * 60 * 60 * 1000)) + 1) + " 天");
      
      date = DateUtils.addDays(date, 100);

      //date = DateUtils.addDays(date, -1);
      
      System.out.println("百岁日期：" + DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
    }
    
    private static String getBetweenTimes(Date startTime, Date now) {
      long startupTime = startTime.getTime();
      long nowTime = now.getTime();

      long days = 0;
      long hours = 0;
      long minutes = 0;
      long seconds = 0;

      long interval = nowTime - startupTime;

      days = interval / (24 * 60 * 60 * 1000);
      hours = (interval % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000);
      minutes = (((interval % (24 * 60 * 60 * 1000)) % (60 * 60 * 1000))) / (60 * 1000);
      seconds = ((((interval % (24 * 60 * 60 * 1000)) % (60 * 60 * 1000))) % (60 * 1000)) / 1000;
      
      long month = (days / 30);
      long day_mod = (days % 30);
      
      long years = month > 12 ? month / 12 : 0;
      long year_month = month % 12;
      long year_day = day_mod % 30;
      
      return days + " 天  " + hours + " 小时  " + minutes + " 分  " + seconds + " 秒，（约合 " + month + " 个月 " + day_mod + " 天；[" + years + " 年 " + year_month + " 个月 " + year_day + " 天]）";
    }

}
