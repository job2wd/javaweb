/*
 * $Id: JavaIpTools.java 4405 2018-06-26 07:13:59Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmc-cloud/src/main/java/com/hapr/cmc/util/JavaIpTools.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.common.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 与ip相关的工具类
 *  1, IpLong2Str 把长整型的IP转化为字符串形式
 *  2, IpInt2Str 把整型的IP转化为字符串形式
 *  3, getIpNumber 把IP字符串转化为长整型
 *  4, IpStr2Int 把IP字符串转化为整型
 *  5, getIpRange(String[] includeIp, String[] excludeIp)
 *  6, getIpRange(String includeIp, String excludeIp) 把包含IP串和排除IP串合并成一个不重复的IP段
 *  7, ipRanges2IpString(List<IpRange>) 将ipRanges 转化为 10.10.10.10-10.10.10.11,...
 *  8, chuChong(List<IpRange> listIpRange)
 *  9, getIpNumber 将字符串IP转换成整数IP
 *  10, getIpv4(Long) 将一个整数转换成其相对应的使用‘点分法’表示的IP，如127.0.0.1
 *  11, isIpv4(String s) IP格式验证

 * @version $Revision: 4405 $, $Date: 2015-7-9 下午2:12:14$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-06-26 15:13:59#$
 */
public class JavaIpTools {

  /**
   * IPV4 格式字符串 IP 转换为数值型 IP
   * 
   * @param ipv4 IPV4 格式字符串 IP
   * @return 数值型 IP<br>
   * <b style="color:#FF0000;">注意：IP 数值型类型只能为 Long 类型，Integer 类型不足以涵盖所有 IP 范围，Integer 范围为 0~2147483647（Integer.MIN_VALUE~Integer.MAX_VALUE），而 IP 范围为0~4294967295（0.0.0.0~255.255.255.255）</b>
   */
  public static Long getIpNumber(String ipv4) {
    if(StringUtils.isBlank(ipv4)) {
      return null;
    }
    
    if(!isIpv4(ipv4)) {
      throw new IllegalArgumentException("invalid ip, ip(ipv4) range: 0.0.0.0~255.255.255.255");
    }
    
    String[] ips = ipv4.split("[.]");
    
    return (Long.valueOf(ips[0]) << 24) + (Long.valueOf(ips[1]) << 16) + (Long.valueOf(ips[2]) << 8) + Long.valueOf(ips[3]);
  }
  
  /**
   * Long 类型 IP 转换为 IPV4 格式字符串IP
   * 
   * @param ip 数值型 IP<br>
   * <b style="color:#FF0000;">注意：IP 数值型类型只能为 Long 类型，Integer 类型不足以涵盖所有 IP 范围，Integer 范围为 0~2147483647（Integer.MIN_VALUE~Integer.MAX_VALUE），而 IP 范围为0~4294967295（0.0.0.0~255.255.255.255）</b>
   * @return IPV4 格式字符串IP
   */
  public static String getIpv4(Long ip) {
    if(ip == null) {
      return null;
    }
    
    if(!isIpv4(ip)) {
      throw new IllegalArgumentException("invalid ip, number ip(ipv4) range: 0~4294967295");
    }
    
    return (ip >> 24) + "." + ((ip & 0x00FF0000) >> 16) + "." + ((ip & 0x0000FF00) >> 8) + "." + (ip & 0x000000FF);
    //return String.format("%d.%d.%d.%d", (ip >> (3 << 3)) & 0xFF, (ip >> (2 << 3)) & 0xFF, (ip >> (1 << 3)) & 0xFF, (ip >> (0 << 3)) & 0xFF);
  }
  
  /**
   * 把包含IP串和排除IP串合并成一个不重复的IP段
   * 
   * @param includeIp
   *            包含IP串。允许以逗号分隔的多个CIDR形式的子网，减号分隔的IP范围（大的IP可以放在减号的前面）和单个IP
   * @param excludeIp
   *            排除IP串。其他同includeIp
   * @return 按开始IP由小到大排序的IpRange列表。IpRange对象中startIp<=endIp，如果startIp==endIp，表示单个IP
   *         说明
   *         1.把参数中的子网解析为“0-255”，例如把“192.168.3.0/24”解析为“192.168.3.0-192.168.3.255”形式
   *         2.当子网的IP部分为0时，解析为“0.0.0.0-255.255.255.255”，例如“0.0.0.0/24”和“0.0.0.0/32”都符合此情况
   *         3.当单个IP为0.0.0.0时表示“0.0.0.0-255.255.255.255”
   *         4.在下列情况下，返回null：
   *             a.当includeIp为null或空串时 b.excludeIp包含includeIp时
   * @throws IllegalArgumentException -
   *             IP字符串不是有效IP，该IP存储在异常类的message中
   */
  public static List<JavaIpRange> getIpRange(String includeIp, String excludeIp) {
    if (StringUtils.isBlank(includeIp)) {
      return new ArrayList<JavaIpRange>();//
    }
    List<JavaIpRange> includeList = string2SortedIpRange(includeIp);
    List<JavaIpRange> excludeList = string2SortedIpRange(excludeIp);

    if (null == includeList) {
      return new ArrayList<JavaIpRange>();
    }

    // 处理排除串
    if (null != excludeList && excludeList.size() > 0) {
      for (int i = 0; i < excludeList.size(); i++) {
        JavaIpRange exclude = excludeList.get(i);
        for (int j = 0; j < includeList.size(); j++) {
          JavaIpRange include = includeList.get(j);

          // 忽略exclude和include没有交集的情况
          if ((exclude.getEndIp() < include.getStartIp())) {
            break;
          } else if ((exclude.getStartIp() > include.getEndIp())) {
            continue;
          }

          if ((exclude.getStartIp() > include.getStartIp())
              && (exclude.getEndIp() < include.getEndIp())) {
            // include包含exclude
            includeList.add(j + 1, new JavaIpRange(
                exclude.getEndIp() + 1, include.getEndIp()));
            includeList.get(j).setEndIp(exclude.getStartIp() - 1);
            j++;
          } else if ((exclude.getStartIp() <= include.getStartIp())
              && (exclude.getEndIp() >= include.getEndIp())) {
            // exclude包含include
            includeList.remove(j);
            j--;
          } else if ((exclude.getStartIp() <= include.getStartIp())) {
            // exclude与include前半段重合
            include.setStartIp(exclude.getEndIp() + 1);
          } else {
            // exclude与include后半段重合
            include.setEndIp(exclude.getStartIp() - 1);
          }
        }
      }
    }

    return (null == includeList) ? new ArrayList<JavaIpRange>() : includeList;
  }

  /**
   * 功能描述：将ipRanges 转化为 10.10.10.10-10.10.10.11,...
   * @param ipRanges
   * @return
   */
  public static String getIpv4(List<JavaIpRange> ipRanges){
    if(ipRanges == null) {
      return null;
    }
    
    String ips = "";
    int size = ipRanges.size();
        
    for(int i = 0; i < size; i++){
      ips += getIpv4(ipRanges.get(i).getStartIp()) + "-" + getIpv4(ipRanges.get(i).getEndIp());
          
      if(i != size - 1) {
        ips += ",";
      }
    }
    
    return ips;
  }

  /**
   * 功能描述：将ipRanges 转化为2008810193-2008810198,2008810200-2008810207,...
   * @param ipRanges
   * @return
   * String
   */
  public static String getIpNumber(List<JavaIpRange> ipRanges){
    if(ipRanges == null) {
      return null;
    }
    
    String ips = "";
    int size = ipRanges.size();
        
    for(int i = 0; i < size; i++){
      ips += ipRanges.get(i).getStartIp() + "-" + ipRanges.get(i).getEndIp();
          
      if(i != size - 1) {
        ips += ",";
      }
    }
    
    return ips;
  }

  /**
   * 2000
   */
  public static long maxIpCount = 2000l;
  
  /**
   * 功能描述：将ipRanges 转化为ip 字符串的集合
   * @param ipRanges
   * @return
   * String
   */
  public static List<String> ipRanges2Ips(List<JavaIpRange> ipRanges){
    List<String> ips =  new ArrayList<String>();
    if(ipRanges == null || ipRanges.size() == 0) {
      return ips;
    }
    if(getIpSize(ipRanges) > maxIpCount)
     {
      return ips;//最多解析2000个ip出来
    }

    for(JavaIpRange ipRange : ipRanges){
      rangeLong2Ip(ipRange.getStartIp(), ipRange.getEndIp(), ips);
    }

    return ips;
  }
  
  public static long getIpSize(List<JavaIpRange> ipRanges){
    if(ipRanges == null) {
      return 0;
    }
    
    long size = 0l;
    
    for(JavaIpRange ipRange : ipRanges){
      size += ipRange.getRangeSize();
    }
    
    return size;
  }

  public static List<String> rangeLong2Ip(long begIp, long endIp, List<String> ips) {
    ips = ips == null ? new ArrayList<String>() : ips;
    
    int f[] = { 0, 0, 0, 0 };

    for (; begIp <= endIp; begIp++) {
      for (int i = 0; i < f.length; i++) {
        f[i] = (int) (begIp >> (i << 3)) & 0xFF;
      }
      if((f[0] > 0) && (f[0] < 255)){
        ips.add(String.format("%d.%d.%d.%d", f[3], f[2], f[1], f[0]));
      }
    }
    return ips;
  }



  public static List<JavaIpRange> chuChong(List<JavaIpRange> listIpRange){
    if(listIpRange == null || listIpRange.size() < 1) {
      return listIpRange;
    }
    
    if (listIpRange.size() > 0) {
      // 按开始IP由小到大排序
      Collections.sort(listIpRange, new Comparator<JavaIpRange>() {
        @Override
        public int compare(JavaIpRange left, JavaIpRange right) {
          long result = left.getStartIp() - right.getStartIp();
          return (result < 0) ? -1 : (result == 0) ? 0 : 1;
        }
      });

      // 合并重合的相邻IP段
      for (int i = 1; i < listIpRange.size(); i++) {
        JavaIpRange range1 = listIpRange.get(i - 1);
        JavaIpRange range2 = listIpRange.get(i);

        if (range2.getStartIp() < range1.getEndIp() + 2) {
          // 包含两个IP段紧密相邻的情况，例如“192.168.3.0-192.168.3.255”和“192.168.4.0-192.168.4.255”。
          // 如果不是“...<...+2”,则不能处理此情况; “...<...+2”相当于“...<=...+1”
          range1.setEndIp(Math.max(range1.getEndIp(), range2
              .getEndIp()));
          listIpRange.remove(i);
          i--;
        }
      }
      
    }
    
    return listIpRange;
  }
  
  /**
   * 把逗号分隔的多个子网、范围和IP字符串，转化为IpRange列表，并且将最后的数据进行合并返回。
   * 
   * @param ipRange
   *            逗号分隔的多个子网、范围和IP字符串
   * @return IpRange列表
   */
  public static List<JavaIpRange> string2SortedIpRange(String ipRange) {
    if (null == ipRange || ipRange.trim().equals("")) {
      return new ArrayList<JavaIpRange>();
    }
    ipRange = ipRange.trim();
    
    List<JavaIpRange> listIpRange = new ArrayList<JavaIpRange>();
    String[] ipRangeArray = StringUtils.split(ipRange, ",");
    for (int i = 0; i < ipRangeArray.length; i++) {
      JavaIpRange range = singIpRange2IpRange(ipRangeArray[i]);
      if (null != range) {
        listIpRange.add(range);
      }
    }

    if (listIpRange.size() > 0) {
      // 按开始IP由小到大排序
      Collections.sort(listIpRange, new Comparator<JavaIpRange>() {
        @Override
        public int compare(JavaIpRange left, JavaIpRange right) {
          long result = left.getStartIp() - right.getStartIp();
          return (result < 0) ? -1 : (result == 0) ? 0 : 1;
        }
      });

      // 合并重合的相邻IP段
      for (int i = 1; i < listIpRange.size(); i++) {
        JavaIpRange range1 = listIpRange.get(i - 1);
        JavaIpRange range2 = listIpRange.get(i);

        if (range2.getStartIp() < range1.getEndIp() + 2) {
          // 包含两个IP段紧密相邻的情况，例如“192.168.3.0-192.168.3.255”和“192.168.4.0-192.168.4.255”。
          // 如果不是“...<...+2”,则不能处理此情况; “...<...+2”相当于“...<=...+1”
          range1.setEndIp(Math.max(range1.getEndIp(), range2
              .getEndIp()));
          listIpRange.remove(i);
          i--;
        }
      }
    }

    return (0 == listIpRange.size()) ? null : listIpRange;
  }

  /**
   * 把逗号分隔的多个子网、范围和IP字符串，转化为IpRange列表
   * 
   * @param ips
   * @return
   */
  public static List<JavaIpRange> ips2IpRanges(String ips) {
    if (null == ips || ips.trim().equals("")) {
      return new ArrayList<JavaIpRange>();
    }
    
    ips = ips.trim();
    
    List<JavaIpRange> ipRanges = new ArrayList<JavaIpRange>();
    
    String[] ipsArray = StringUtils.split(ips, ",");
    
    for (int i = 0; i < ipsArray.length; i++) {
      ipRanges.add(singIpRange2IpRange(ipsArray[i]));
    }
    
    return ipRanges;
  }
  
  /**
   * 把单个子网，范围和单个IP转换为IpRange对象
   * 
   * @param ipRange
   *            单个子网、范围或IP字符串。0.0.0.0和0.0.0.0/XX表示0.0.0.0-255.255.255.255
   * @return IpRange对象。保证IpRange中的startIp <= endIp。
   */
  public static JavaIpRange singIpRange2IpRange(String ipRange) {
    long startIp = 0;
    long endIp = 0;

    if (0 <= ipRange.indexOf('/', 0)) {// 形如192.168.29.0/24
      String[] range = ipRange.split("/");
      startIp = getIpNumber(range[0]);

      if (2 != range.length) {
        throw new IllegalArgumentException(
            "IP's Format error. Arg:ipRange=" + ipRange);
      }

      int maskLen = Integer.valueOf(range[1]);
      int mask = 0x80000000 >> (maskLen - 1);
      if ((maskLen < 0) || (maskLen > 32)) {
        throw new IllegalArgumentException(
            "Mask len error. Arg:ipRange=" + ipRange);
      }

      startIp &= mask;
      endIp = startIp | (~mask);
      if (0 == startIp) {// ip为0时，开始IP为0,结束IP为255.255.255.255
        startIp = 0;
        endIp = (1L << 32) - 1;
      }
    } else if (0 <= ipRange.indexOf('-', 0)) {// 形如192.168.29.0-192.168.29.255
      String[] range = ipRange.split("-");
      if (2 != range.length) {
        throw new IllegalArgumentException(
            "IP's Format error. Arg:ipRange=" + ipRange);
      }
      startIp = getIpNumber(range[0]);
      endIp = getIpNumber(range[1]);
    } else {// 单个IP
      startIp = endIp = getIpNumber(ipRange);
      if (0 == endIp) {
        endIp = (1L << 32) - 1;
      }
    }

    return new JavaIpRange(Math.min(startIp, endIp), Math.max(startIp, endIp));
  }
      
  /**
   * 验证提供的字符串是为点分十进制表示的符合 IPV4 格式的字符串（IPV4 格式范围： <b>0.0.0.0~255.255.255.255</b>，数值范围：<b>0~4294967295</b>）
   * 
   * @param ipv4 点分十进制表示的符合 IPV4 格式的字符串
   * @return true -  是； false - 否；
   */
  public static boolean isIpv4(String ipv4) {
    if(StringUtils.isBlank(ipv4)) {
      return false;
    }
    
    String[] ips = ipv4.trim().split("[.]");
    
    if(ips.length != 4) {
      return false;
    }
    
    Integer i = null;
    
    for(String ip : ips) {
      i = Integer.valueOf(ip);
      
      if(i < 0 || i > 255) {
        return false;
      }
    }
    
    return true;
  }

  /**
   * 验证提供的数值是否为点分十进制表示的符合 IPV4 格式的字符串范围（IPV4 格式范围： <b>0.0.0.0~255.255.255.255</b>，数值范围：<b>0~4294967295</b>）
   * @param ipv4 数值表示的 IPV4
   * @return true -  是； false - 否；
   */
  public static boolean isIpv4(Long ipv4) {
    if(ipv4 == null) {
      return false;
    }
    
    if(ipv4 < 0 || ipv4 > 4294967295l) {
      return false;
    }
    
    return true;
  }
  
  /**
   * 判断ip是否在ipRange集合中
   * @param ip
   * @param ipRangeList
   * @return    (参数说明)
   * @return boolean    返回类型
   * @throws
   */
  public static boolean isIpInIpRange(long ip, List<JavaIpRange> ipRangeList) {
    boolean result = false;
    if(ipRangeList != null) {
      for(JavaIpRange ir : ipRangeList) {
        if(ip >= ir.getStartIp() && ip <= ir.getEndIp()) {
          result = true;
          break;
        }
      }
    }
    
    return result;
  }

  /**
   * 判断ip是否在ipRange集合中
   * @param ip
   * @param ipRangeList
   * @return    (参数说明)
   * @return boolean    返回类型
   * @throws
   */
  public static boolean isIpStrInIpRange(String ipStr, List<JavaIpRange> ipRangeList) {
    long ip = 0;
    try {
      ip = getIpNumber(ipStr);
    } catch (Exception e) {
      return false;
    }
    
    return isIpInIpRange(ip, ipRangeList);
  }
  
  /**
   * 判断 range1 是否在 range2 的范围内。
   * 
   * @param range1
   * @param range2
   * @return
   */
  public static boolean isIpRange1InIpRange2(List<JavaIpRange> range1, List<JavaIpRange> range2) {
    for(JavaIpRange r1 : range1) {
      for(JavaIpRange r2 : range2) {
        if(r1.getStartIp() >= r2.getStartIp() && r1.getEndIp() <= r2.getEndIp()) {
          return true;
        }
      }
    }
    
    return false;
  }
  
  /**
   * 判断 ips1 是否在 ips2 的范围内。
   * 
   * @param ips1 包含单IP、子网、范围的IP列表字符串（Ip列表之间以“,”隔开）
   * @param ips2 包含单IP、子网、范围的IP列表字符串（Ip列表之间以“,”隔开）
   * @return
   */
  public static boolean isIps1InIps2(String ips1, String ips2) {
    if(ips1.equals(ips2)) {
      return true;
    }
    return isIpRange1InIpRange2(ips2IpRanges(ips1), ips2IpRanges(ips2));
  }
  
  /**
   * (将包含IP串和排除IP串转成List<String ip>)
   * @param ip 包含IP串 例：192.168.1.119,192.168.2.119,192.168.2.200-192.168.2.220,192.168.3.1/31
   * @param excludeIp 排除IP串 例：192.168.2.201,192.168.1.1/31
   * @return    (返回String类型的List，其中每个String为一个ip)
   * @return List<String>    返回类型
   * @throws
   */
  public static List<String> ip2StringList(String ip,String excludeIp){
    List<JavaIpRange> list1 = getIpRange(ip, excludeIp);
    List<String> list2 = ipRanges2Ips(list1);
    return list2;
  }
  
  /**
   * 检查单个IP是否为内网IP<br>
   * 内网IP范围：
   * <li>10.0.0.0--10.255.255.255</li>
   * <li>172.16.0.0--172.31.255.255</li>
   * <li>192.168.0.0--192.168.255.255</li>
   * @param ip
   * @return
   */
  public static boolean isLANIP(String ipStr) {
    long ip = 0;
    try {
      ip = getIpNumber(ipStr);
    } catch (Exception e) {
      return false;
    }
    return isLANIP(ip);
  }
  
  /**
   * 检查单个IP是否为内网IP<br>
   * 内网IP范围：
   * <li>10.0.0.0--10.255.255.255</li>
   * <li>172.16.0.0--172.31.255.255</li>
   * <li>192.168.0.0--192.168.255.255</li>
   * @param ip
   * @return
   */
  public static boolean isLANIP(long ip) {
    /*
    List<IpRange> ipRangeList = new ArrayList<IpRange>();
    
    IpRange r_10 = new IpRange(FbaIpTools.getIpNumber("10.0.0.0"), FbaIpTools.getIpNumber("10.255.255.255"));
    IpRange r_172 = new IpRange(FbaIpTools.getIpNumber("172.16.0.0"), FbaIpTools.getIpNumber("172.31.255.255"));
    IpRange r_192 = new IpRange(FbaIpTools.getIpNumber("192.168.0.0"), FbaIpTools.getIpNumber("192.168.255.255"));
        
    ipRangeList.add(r_10);
    ipRangeList.add(r_172);
    ipRangeList.add(r_192);
    */
    List<JavaIpRange> ipRangeList = ips2IpRanges(getLANIP());
    
    return isIpInIpRange(ip, ipRangeList);
  }

  /**
   * 指定的 ip 是否为 指定的 ip范围 ipRange 中的 ip C 网段内
   * @param ip 单个 ip
   * @param ipRange 包含IP串 例：192.168.1.119,192.168.2.119,192.168.2.200-192.168.2.220,192.168.3.1/31
   * @return
   */
  public static boolean isCLANIP(long ip, String ipRange) {
    List<JavaIpRange> ipRangeList = ips2IpRanges(ipRange);
    
    String ipStr = JavaIpTools.getIpv4(ip);
    String[] ipStrArray = ipStr.split("[.]");
    
    for(JavaIpRange r : ipRangeList) {
      String[] startIpArray = JavaIpTools.getIpv4(r.getStartIp()).split("[.]");
      String[] endIpArray = JavaIpTools.getIpv4(r.getEndIp()).split("[.]");
      
      if(Integer.valueOf(ipStrArray[0]).intValue() == Integer.valueOf(startIpArray[0]).intValue()
          && Integer.valueOf(ipStrArray[0]).intValue() == Integer.valueOf(endIpArray[0]).intValue()
          && Integer.valueOf(ipStrArray[1]).intValue() == Integer.valueOf(startIpArray[1]).intValue()
          && Integer.valueOf(ipStrArray[1]).intValue() == Integer.valueOf(endIpArray[1]).intValue()
          && Integer.valueOf(ipStrArray[2]).intValue() == Integer.valueOf(startIpArray[2]).intValue()
          && Integer.valueOf(ipStrArray[2]).intValue() == Integer.valueOf(endIpArray[2]).intValue()
          && Integer.valueOf(ipStrArray[3]).intValue() >= Integer.valueOf(startIpArray[3]).intValue()
          && Integer.valueOf(ipStrArray[3]).intValue() <= Integer.valueOf(endIpArray[3]).intValue()) {
        return true;
      }
    }
    
    return false;
  }
  
  /**
   * 获得默认内网 IP 地址。
   * @return 10.0.0.0-10.255.255.255,172.16.0.0-172.31.255.255,192.168.0.0-192.168.255.255
   */
  public static String getLANIP() {
    return "10.0.0.0-10.255.255.255,172.16.0.0-172.31.255.255,192.168.0.0-192.168.255.255";
  }
  
  /**
   * 返回提供的 IP 是否为保留地址<br>
   * 目前保留地址范围为：
   * <li>0.0.0.0</li>
   * <li>255.255.255.255</li>
   * <li>224.0.0.0-239.255.255.255</li>
   * <li>xxx.xxx.xxx.0</li>
   * <li>xxx.xxx.xxx.255</li>
   * <li>0.xxx.xxx.xxx</li>
   * <li>255.xxx.xxx.xxx</li><br>
   * @return
   */
  public static boolean isReservedIP(String ip) {
    if("0".equals(ip) || "0.0.0.0".equals(ip) || "255.255.255.255".equals(ip)) {
      return true;
    }
    if(isStartReservedIP(ip) || isEndReservedIP(ip)) {
      return true;
    }
    return isReservedIP(getIpNumber(ip));
  }
  
  /**
   * 返回提供的 IP 是否为保留地址<br>
   * 目前保留地址范围为：
   * <li>0.0.0.0</li>
   * <li>255.255.255.255</li>
   * <li>224.0.0.0-239.255.255.255</li>
   * <li>xxx.xxx.xxx.0</li>
   * <li>xxx.xxx.xxx.255</li>
   * <li>0.xxx.xxx.xxx</li>
   * <li>255.xxx.xxx.xxx</li><br>
   * @return
   */
  public static boolean isReservedIP(long ip) {
    if(ip == 0) {
      return true;
    }
    
    String ipStr = getIpv4(ip);
    if(isStartReservedIP(ipStr) || isEndReservedIP(ipStr)) {
      return true;
    }
    
    List<JavaIpRange> ipRangeList = ips2IpRanges(getReservedIp());
    return isIpInIpRange(ip, ipRangeList);
  }
  
  private static boolean isStartReservedIP(String ip) {
    return ip.startsWith("0.") || ip.startsWith("255.");//0.xxx.xxx.xxx, 255.xxx.xxx.xxx
  }
  
  private static boolean isEndReservedIP(String ip) {
    return ip.endsWith(".0") || ip.endsWith(".255");//xxx.xxx.xxx.0, xxx.xxx.xxx.255
  }

  /**
   * 返回部分保留地址
   * @return
   */
  private static String getReservedIp() {
    return "255.255.255.255,224.0.0.0-239.255.255.255";//0.0.0.0?
  }
  
  /**
   * 根据提供的数据库表中 IP 列名称（columns）构造针对整型 IP 过滤掉保留地址的 WHERE 表达式。<br>
   * 目前保留地址范围为：
   * <li>0.0.0.0</li>
   * <li>255.255.255.255</li>
   * <li>224.0.0.0-239.255.255.255</li>
   * <li>xxx.xxx.xxx.0</li>
   * <li>xxx.xxx.xxx.255</li>
   * <li>0.xxx.xxx.xxx</li>
   * <li>255.xxx.xxx.xxx</li><br>
   * 若数据库表列名称为“ip”，则构造后的 WHERE 表达式为：<br>
   * <pre><b> (ip <> 0 AND ip <> 4294967295 AND (ip < 3758096384 OR ip > 4026531839)) </b></pre>
   * <b style="color:red;">注：该方法目前返回的表达中不包括如下范围 IP 的过滤：<br>xxx.xxx.xxx.0、xxx.xxx.xxx.255、0.xxx.xxx.xxx、255.xxx.xxx.xxx</b>
   * 
   * @param column 要查询的数据库表中 IP 列的名称
   * @return 构造后的针对整型 IP 过滤掉保留地址的 WHERE 表达式
   */
  public static String getExcludeReservedIpWhereExpression4LongIp(String column) {
    return " (" +
           column + " <> 0 AND " + column + " <> 4294967295 AND (" +//0.0.0.0, 255.255.255.255
           column + " < 3758096384 OR " + column + " > 4026531839)) ";//224.0.0.0-239.255.255.255
           //xxx.xxx.xxx.0, xxx.xxx.xxx.255???
           //0.xxx.xxx.xxx, 255.xxx.xxx.xxx??
  }
  /**
   * 根据提供的数据库表中 IP 列名称（columns）构造针对字符串 IP（符合 IPV4 格式的 IP 地址） 过滤掉保留地址的 WHERE 表达式。<br>
   * 目前保留地址范围为：
   * <li>0.0.0.0</li>
   * <li>255.255.255.255</li>
   * <li>224.0.0.0-239.255.255.255</li>
   * <li>xxx.xxx.xxx.0</li>
   * <li>xxx.xxx.xxx.255</li>
   * <li>0.xxx.xxx.xxx</li>
   * <li>255.xxx.xxx.xxx</li><br>
   * 若数据库表列名称为“ip”，则构造后的 WHERE 表达式为：<br>
   * <pre><b> (ip NOT LIKE '224.%' AND ip NOT LIKE '239.%' AND ip NOT LIKE '%.0' AND ip NOT LIKE '%.255') </b></pre>
   * <b style="color:red;">注：该方法目前返回的表达中不包括如下范围 IP 的过滤：<br>0.xxx.xxx.xxx、255.xxx.xxx.xxx</b>
   * 
   * @param column 要查询的数据库表中 IP 列的名称
   * @return 构造后的针对整型 IP 过滤掉保留地址的 WHERE 表达式
   */
  public static String getExcludeReservedIpWhereExpression4StringIp(String column) {
    return " " +
           //column + " <> 0.0.0.0 AND " + column + " <> 255.255.255.255 AND " +//0.0.0.0, 255.255.255.255 已经在 .0 和 .255 中包含了
           column + " NOT LIKE '224.%' AND " + column + " NOT LIKE '239.%' AND " +//224.0.0.0-239.255.255.255
           //column + " NOT LIKE '0.%' AND " + column + " NOT LIKE '255.%' AND " +//0.xxx.xxx.xxx, 255.xxx.xxx.xxx
           column + " NOT LIKE '%.0' AND " + column + " NOT LIKE '%.255' ";//xxx.xxx.xxx.0, xxx.xxx.xxx.255
  }
  
  /**
   * 构造 SQL 查询中针对 IP 范围过滤的 WHERE 查询条件。<br>
   * 返回数据格式如下：<br>
   *  <pre><b>((src_ip >= 167772160 AND src_ip <= 184549375) OR (src_ip >= 2886729728 AND src_ip <= 2887778303) OR (src_ip >= 3232235520 AND src_ip <= 3232301055))</b></pre>
   * 
   * @param ips 以逗号 “,” 分隔的多个 IP、IP 范围、子网。
   *  若该参数为 null 或 为空值，则返回默认内网 IP（FbaIpTools.getLANIP()） 构造的 WHERE 条件表达式。
   * @param column
   * @return 包含指定IP范围的条件表达式。排除了保留地址，参见：{@link #getExcludeReservedIpWhereExpression4LongIp(String)}
   */
  public static String getWhereIncludeExpression4Ips(String ips, String column) {
    if(StringUtils.isBlank(column)) {
      return "";
    }
    
    String wlanIp = ips;
    
    if(StringUtils.isBlank(ips)) {
      wlanIp = getLANIP();
    }
    
    return getWhereIncludeExpression4IpRange(ips2IpRanges(wlanIp), column);
  }
  
  /**
   * 构造 SQL 查询中针对 IP 范围过滤的 WHERE 查询条件。<br>
   * 返回数据格式如下：<br>
   *  <pre><b>((src_ip >= 167772160 AND src_ip <= 184549375) OR (src_ip >= 2886729728 AND src_ip <= 2887778303) OR (src_ip >= 3232235520 AND src_ip <= 3232301055))</b></pre>
   * 
   * @param wlanIpRange 若该参数为 null 或 size 为 0，则返回默认内网 IP（FbaIpTools.getLANIP()） 构造的 WHERE 条件表达式。
   * @param column
   * @return 包含指定IP范围的条件表达式。排除了保留地址，参见：{@link #getExcludeReservedIpWhereExpression4LongIp(String)}
   */
  public static String getWhereIncludeExpression4IpRange(List<JavaIpRange> wlanIpRange, String column) {
    if(StringUtils.isBlank(column)) {
      return "";
    }
    
    List<JavaIpRange> ipRange = wlanIpRange;
    
    if(wlanIpRange == null || wlanIpRange.size() == 0) {
      ipRange = ips2IpRanges(getLANIP());
    }
    
    long startIp = 0;
    long endIp = 0;
    
    String where = "";
    
    for(JavaIpRange r : ipRange) {
      startIp = r.getStartIp();
      endIp = r.getEndIp();
      
      if(StringUtils.isNotBlank(where)) {
        where += " OR ";
      }
      
      if(startIp == endIp) {
        where += "(" + column + " = " + startIp + ")";
      } else {
        where += "(" + column + " >= " + startIp + " AND " + column + " <= " + endIp + ")";
      }
    }
    
    where = " (" + where + ") ";
    where += " AND " + getExcludeReservedIpWhereExpression4LongIp(column);//添加上针对保留地址的过滤
        
    return where;
  }
  
  /**
   * 构造 SQL 查询中针对 IP 范围过滤的 WHERE 查询条件。<br>
   * 返回数据格式如下：<br>
   *  <pre><b>((src_ip >= 167772160 AND src_ip <= 184549375) OR (src_ip >= 2886729728 AND src_ip <= 2887778303) OR (src_ip >= 3232235520 AND src_ip <= 3232301055))</b></pre>
   * 
   * @param ips 以逗号 “,” 分隔的多个 IP、IP 范围、子网。
   *  若该参数为 null 或 为空值，则返回默认内网 IP（FbaIpTools.getLANIP()） 构造的 WHERE 条件表达式。
   * @param column
   * @return 排除指定IP范围的条件表达式
   */
  public static String getWhereExcludeExpression4Ips(String ips, String column) {
    if(StringUtils.isBlank(column)) {
      return "";
    }
    
    String wlanIp = ips;
    
    if(StringUtils.isBlank(ips)) {
      wlanIp = getLANIP();
    }
    
    return getWhereExcludeExpression4IpRange(ips2IpRanges(wlanIp), column);
  }
  
  /**
   * 构造 SQL 查询中针对 IP 范围过滤的 WHERE 查询条件。<br>
   * 返回数据格式如下：<br>
   *  <pre><b>((src_ip >= 167772160 AND src_ip <= 184549375) OR (src_ip >= 2886729728 AND src_ip <= 2887778303) OR (src_ip >= 3232235520 AND src_ip <= 3232301055))</b></pre>
   * 
   * @param wlanIpRange 若该参数为 null 或 size 为 0，则返回默认内网 IP（FbaIpTools.getLANIP()） 构造的 WHERE 条件表达式。
   * @param column
   * @return 排除指定IP范围的条件表达式
   */
  public static String getWhereExcludeExpression4IpRange(List<JavaIpRange> wlanIpRange, String column) {
    if(StringUtils.isBlank(column)) {
      return "";
    }
    
    List<JavaIpRange> ipRange = wlanIpRange;
    
    if(wlanIpRange == null || wlanIpRange.size() == 0) {
      ipRange = ips2IpRanges(getLANIP());
    }
    
    long startIp = 0;
    long endIp = 0;
    
    String where = "";
    
    for(JavaIpRange r : ipRange) {
      startIp = r.getStartIp();
      endIp = r.getEndIp();
      
      if(StringUtils.isNotBlank(where)) {
        where += " AND ";
      }
      
      if(startIp == endIp) {
        where += "(" + column + " <> " + startIp + ")";
      } else {
        where += "(" + column + " <= " + startIp + " OR " + column + " >= " + endIp + ")";
      }
    }
    
    where = " (" + where + ") ";
    where += " AND " + getExcludeReservedIpWhereExpression4LongIp(column);//添加上针对保留地址的过滤
    
    return where;
  }
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    List<String> list = rangeLong2Ip(3232235777L, 3232236079L, null);
    System.out.println(list);
    
    System.out.println("====================================");
    
    System.out.println("1.2.3.4=" + getIpNumber("1.2.3.4"));
    System.out.println("172.16.100.30=" + getIpNumber("172.16.100.30"));
    
    printLongIpRange4Ipv4Rang("0.0.0.0-255.255.255.255");//172.16.100.1-172.16.100.255
    printLongIpRange4Ipv4Rang("172.16.100.1-172.16.100.255");//172.16.100.1-172.16.100.255
    
    System.out.println("====================================");
    
    System.out.println("2886755457=" + getIpv4(2886755457l));
    System.out.println("2886755358=" + getIpv4(2886755358l));
    
    printIpv4Range4LongIpRang("2886755329-2886755459");//2886755329-2886755583
    
    System.out.println("====================================");
    
    System.out.println(isLANIP("192.168.254.90"));
    System.out.println(isLANIP("172.16.3.1"));
    
    System.out.println(getIpv4(getIpNumber("192.168.10.1") + 255));
    
    System.out.println(ips2IpRanges("255.255.255.255"));
    System.out.println((738177535 + 255));
    
    System.out.println(getExcludeReservedIpWhereExpression4LongIp("ip"));
    System.out.println(getExcludeReservedIpWhereExpression4StringIp("ip"));
    
    System.out.println(ips2IpRanges(getLANIP()));
    
    Long l1 = 123l;
    Long l2 = 123l;
    System.out.println(l1 + "," + l2);
        
    System.out.println(getWhereIncludeExpression4Ips("192.168.0.0-192.168.0.200,192.168.1.200,172.16.1.2-172.16.1.20", "src_ip"));
    System.out.println(getWhereIncludeExpression4Ips(null, "src_ip"));
    System.out.println(getWhereIncludeExpression4IpRange(null, "src_ip"));
    
    System.out.println(getWhereExcludeExpression4Ips("192.168.0.0-192.168.0.200,192.168.1.200,172.16.1.2-172.16.1.20", "src_ip"));
    System.out.println(getWhereExcludeExpression4Ips(null, "src_ip"));
    System.out.println(getWhereExcludeExpression4IpRange(null, "src_ip"));
  }

  private static void printIpv4Range4LongIpRang(String longIpRange) {
    String[] ips = longIpRange.split("[-]");
    System.out.println(longIpRange + "=" + getIpv4(Long.valueOf(ips[0])) + "-" + getIpv4(Long.valueOf(ips[1])));
  }
  
  private static void printLongIpRange4Ipv4Rang(String ipv4Range) {
    String[] ips = ipv4Range.split("[-]");
    System.out.println(ipv4Range + "=" + getIpNumber(ips[0]) + "-" + getIpNumber(ips[1]));
  }
  
}
