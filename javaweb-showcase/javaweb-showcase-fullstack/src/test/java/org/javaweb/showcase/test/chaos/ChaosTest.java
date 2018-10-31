/*
 * $Id: ChaosTest.java 140 2017-02-06 06:18:19Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/test/java/org/javaweb/showcase/test/chaos/ChaosTest.java $
 * Copyright (c) 2014 Company, LJ. All Rights Reserved.
 */
package org.javaweb.showcase.test.chaos;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.javaweb.showcase.test.BaseJunitTest;
import org.javaweb.showcase.util.FbaDateFormatUtils;
import org.javaweb.showcase.util.FbaDateUtils;
import org.javaweb.showcase.util.FbaFileUtils;
import org.javaweb.showcase.util.FbaFileUtils.FileLineReadCallback;
import org.javaweb.showcase.util.FbaIpTools;
import org.javaweb.showcase.util.FbaNumberUtils;
import org.javaweb.showcase.util.FileZipUtils;
import org.javaweb.showcase.util.IdFactory;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;



/**
 * Chaos - n.混乱，紊乱； （天地未出现的）浑沌世界； 〈古〉无底深渊； 一团糟
                                       网 络
                                      混沌；混乱；骚乱；紊乱
 * @author wangwd
 * @version $Revision: 140 $, $Date: 2014-9-22 下午12:05:08$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:18:19#$
 */
public class ChaosTest extends BaseJunitTest {
  
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    //PRINT_STATISTICS_LOG = false;
    BaseJunitTest.setUpBeforeClass();
  }
  
  @Ignore("Data too large.")
  @Test(timeout=10 * 1000L, expected=java.lang.OutOfMemoryError.class)//10 秒
  public void testPerformance() throws Exception {
    int count = 100 * 10000;//测试 100 万次循环耗时
    count = count * 100;//测试 1亿次循环耗时
    
    int c = 0;
    List<Integer> list = new ArrayList<Integer>();
    
    for(int i = 1; i <= count; i++) {
      //System.out.println(i);
      c++;
      list.add(c);
    }
    //System.out.println(Integer.MAX_VALUE);
    System.out.println("c=" + c);
  }
  
  @Test(timeout=10 * 1000L)
  public void testDoubleAndFloat() throws Exception {
    float f = -123.9999f;
    double d = 123.999999d;
    
    System.out.println("f=" + f);
    System.out.println("d=" + d);
  }
  
  @Test(timeout=10 * 1000L)
  public void testMathRandom() throws Exception {
    for(int i=0;i<10;i++) {
      int c_index = (int)(Math.random() * 100 * 2.5);
      System.out.println(c_index);
    }
  }
    
  @Test(timeout=10 * 1000L)
  public void testGenerateConnectStatisticsSQL() throws Exception {
    String sql = "INSERT INTO `fba_connect_statistic` (`id`, `statistic_date`, `level`, `connect_count`, `create_time`) VALUES (14108832000004, 20140919, 0, 965, '2014-09-18 00:00:00');";
    
    int[] datas = {20140919, 20140920, 20140921, 20140922, 20140923, 20140924, 20140925, 20140926, 20140927, 20140928, 20140929, 20140930};
    
    long id = 14108832000004l;
    int day = 20;
    
    for(int i=0;i<datas.length;i++) {
      int num1 = (int)(Math.random() * 1000 * (i + 1));
      int num2 = (int)(Math.random() * 1000 * (i + 1));
      int num3 = (int)(Math.random() * 1000 * (i + 1));
      
      sql = "INSERT INTO `fba_connect_statistic` (`id`, `statistic_date`, `level`, `connect_count`, `create_time`) " +
      		"VALUES (" + (id + (i + 1)) + ", " + datas[i] + ", 0, " + num1 + ", '2014-09-" + day + " 00:00:00');";
      System.out.println(sql);
      
      id += 1;
      sql = "INSERT INTO `fba_connect_statistic` (`id`, `statistic_date`, `level`, `connect_count`, `create_time`) " +
          "VALUES (" + (id + (i + 1)) + ", " + datas[i] + ", 1, " + num2 + ", '2014-09-" + day + " 00:00:00');";
      System.out.println(sql);
      
      id += 1;
      sql = "INSERT INTO `fba_connect_statistic` (`id`, `statistic_date`, `level`, `connect_count`, `create_time`) " +
          "VALUES (" + (id + (i + 1)) + ", " + datas[i] + ", 2, " + num3 + ", '2014-09-" + day + " 00:00:00');";
      System.out.println(sql);
      
      day +=1;
    }
  }
  
  @Test(timeout=10 * 1000L)
  public void testChineseAndEngnish() throws Exception {
    String s1 = "好";
    String s2 = "h";
    
    System.out.println(s1.length());
    System.out.println(s2.length());
    
    System.out.println(s1.getBytes(Charset.forName("GB2312")).length);
    System.out.println(s2.getBytes(Charset.forName("UTF-8")).length);
    
    System.out.println(ChineseDistinguisher.isChinese(s1.charAt(0)));
    System.out.println(ChineseDistinguisher.isChinese(s2.charAt(0)));
  }
  
  @Test(timeout=10 * 1000L)
  public void testChineseDistinguisher() throws Exception {
    List<String> lines = FileUtils.readLines(new File("D:\\Work\\IP地址库和经纬度\\国家与首都中英文对照表.txt"), Charset.forName("GB2312"));
    
    int size = lines.size();
    for(int i=0;i<size;i++) {
      String line = lines.get(i);
      
      if(StringUtils.isBlank(line)) {
        continue;
      }
      
      String[] v = line.trim().split("[ ]");
      
      //System.out.println("[" + i + "].length=" + v.length);
      
      int len = v.length;
      String res = "";
      
      for(int j=0;j<len-1;j++) {
        if(j == len-2) {
          res += v[len-1];
        }
        if(ChineseDistinguisher.isChinese(v[j].charAt(v[j].length()-1))) {//是中文
          res += "[" + v[j] + "]";
        } else {//是英文
          if(!ChineseDistinguisher.isChinese(v[j+1].charAt(0))) {//下一个的第一个是否也是英文
            res += v[j] + " " + v[j+1];
          } else {//下一个的第一个是中文
            res += v[j];
          }
        }
      }
      System.out.println(res);
    }
    
  }
  
  @Test(timeout=10 * 1000L)
  public void testInteger() {
    System.out.println("Integer.MAX_VALUE=" + Integer.MAX_VALUE);
    System.out.println("Integer.MIN_VALUE=" + Integer.MIN_VALUE);
    
    System.out.println("Long.MAX_VALUE=" + Long.MAX_VALUE);
    System.out.println("Long.MIN_VALUE=" + Long.MIN_VALUE);
    
    System.out.println((int)14062794600002l);
  }
  
  @Test(timeout=10 * 1000L)
  public void testMessageFormat() {
    System.out.println(MessageFormat.format("hello {0}!", "wwd"));
    
    MessageFormat mf = new MessageFormat("hello {name} {date}!");
    
    //mf.format(arguments, result, pos);
  }
    
  @Test
  public void testAESCrypto() throws Exception {
   // byte[] enc = aesEncrypt("abc", "123");
    
    //System.out.println(new String(aesDecrypt(enc, "123")));
    
   byte[] b = FileUtils.readFileToByteArray(new File("D:\\encrypted.aes"));
   byte[] plain = aesDecrypt(b, "aabc12351231abcdef67890bc1235123");
    
   System.out.println(new String(plain));
    
    byte[] enc = aesEncrypt(FileUtils.readFileToString(new File("D:\\test.txt")), "aabc12351231abcdef67890bc1235123");
    System.out.println(new String(aesDecrypt(enc, "aabc12351231abcdef67890bc1235123")));
  }
  
  /**
   * 加密
   * 
   * @param content 需要加密的内容
   * @param password  加密密码
   * @return
   */
  public byte[] aesEncrypt(String content, String password) {
    try {
      KeyGenerator kgen = KeyGenerator.getInstance("AES");
      kgen.init(128, new SecureRandom(password.getBytes()));
      
      SecretKey secretKey = kgen.generateKey();
      byte[] enCodeFormat = secretKey.getEncoded();
      
      SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
      
      Cipher cipher = Cipher.getInstance("AES");// 创建密码器
      byte[] byteContent = content.getBytes("utf-8");
      
      cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
      byte[] result = cipher.doFinal(byteContent);
      return result; // 加密
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  
  /**
   * 解密
   * 
   * @param content
   *          待解密内容
   * @param password
   *          解密密钥
   * @return
   */
  public static byte[] aesDecrypt(byte[] content, String password) {
    try {
      KeyGenerator kgen = KeyGenerator.getInstance("AES");
      kgen.init(128, new SecureRandom(password.getBytes()));
      
      SecretKey secretKey = kgen.generateKey();
      byte[] enCodeFormat = secretKey.getEncoded();
      
      SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
      cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
      
      byte[] result = cipher.doFinal(content);
      return result; // 加密
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  
  @Test
  public void testApachLang3Date() {
    Date date = new Date();
    date = DateUtils.setYears(date, 2014);
    date = DateUtils.setMonths(date, 11);// 12 month
    date = DateUtils.setDays(date, 10);
    date = DateUtils.setHours(date, 0);
    date = DateUtils.setMinutes(date, 0);
    date = DateUtils.setSeconds(date, 0);
    
    System.out.println(DateFormatUtils.ISO_DATETIME_FORMAT.format(date));
    
  }

  @Test
  public void testFileOverwrite() {
    System.out.println("file overwrite starting....");
    try {
      //org.springframework.beans.propertyeditors.FileEditor fe = new FileEditor();
      
      FbaFileUtils.readLines(new File("D:/cyberaudit_data-fba_netflow_baseinfo_20140801.sql"), new FbaFileUtils.FileLineReadCallback() {

        File file = new File("D:/cyberaudit_data-fba_netflow_baseinfo_20150319.sql");
        
        @Override
        public void beforeProcessLine() throws IOException {
        }

        @Override
        public void processLine(long lineIndex, String line) {
          List<String> lines = new ArrayList<String>();
          
          line = line.replaceAll("20140801", "20150319");
          
          lines.add(line);
          
          try {
            FileUtils.writeLines(file, lines, true);
          } catch (IOException e) {
            e.printStackTrace();
          }
        }

        @Override
        public void afterProcessLine() throws IOException {
        }
        
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("file overwrite end!");
  }
  
  @Test
  public void testFileLineRead() {
    try {
      FbaFileUtils.readLines(new File("D:/tmp/xml_allpolicy.xml"), new FbaFileUtils.FileLineReadCallback() {

        @Override
        public void beforeProcessLine() throws IOException {
        }

        @Override
        public void processLine(long lineIndex, String line) throws IOException {
          System.out.println(line);
          
          if(lineIndex == 10) {
            throw new IOException("end");
          }
        }

        @Override
        public void afterProcessLine() throws IOException {
        }
        
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  @Test
  public void testApacheDateUtils() {
    Date date1 = new Date();
    Date date2 = new Date();
    
    String date1_str = FbaDateFormatUtils.ISO_DATETIME_NO_T_FORMAT.format(date1);
    String date2_str = FbaDateFormatUtils.ISO_DATETIME_NO_T_FORMAT.format(date2);
    
    System.out.println("date1: " + date1_str);
    System.out.println("date2: " + date2_str);
    
    System.out.println("is same day: " + DateUtils.isSameDay(date1, date2));
    
    System.out.println(date2_str + ": year=" + FbaDateUtils.getYear(date2));
    System.out.println(date2_str + ": month=" + FbaDateUtils.getMonth(date2));
    System.out.println(date2_str + ": day=" + FbaDateUtils.getDayOfMonth(date2));
    
    System.out.println(date2_str + ": hour=" + DateUtils.getFragmentInHours(date2, Calendar.DAY_OF_MONTH));
    System.out.println(date2_str + ": minute=" + DateUtils.getFragmentInMinutes(date2, Calendar.HOUR_OF_DAY));
    System.out.println(date2_str + ": second=" + DateUtils.getFragmentInSeconds(date2, Calendar.MINUTE));
    
    date2 = DateUtils.addHours(date2, 2);
    
    date2_str = FbaDateFormatUtils.ISO_DATETIME_NO_T_FORMAT.format(date2);
    System.out.println("date2: " + date2_str);
    
    System.out.println("year truncated compare to: " + DateUtils.truncatedCompareTo(date1, date2, Calendar.YEAR));
    System.out.println("month truncated compare to: :" + DateUtils.truncatedCompareTo(date1, date2, Calendar.MONTH));
    System.out.println("day truncated compare to: :" + DateUtils.truncatedCompareTo(date1, date2, Calendar.DAY_OF_MONTH));
    
    System.out.println("hour truncated compare to: :" + DateUtils.truncatedCompareTo(date1, date2, Calendar.HOUR_OF_DAY));
    System.out.println("minute truncated compare to: :" + DateUtils.truncatedCompareTo(date1, date2, Calendar.MINUTE));
    System.out.println("second truncated compare to: :" + DateUtils.truncatedCompareTo(date1, date2, Calendar.SECOND));
    
    System.out.println("compare:" + FbaDateUtils.compareTime(date1, date2, Calendar.HOUR_OF_DAY));
  }
    
  @Test
  public void testArrays() {
    int[] a = {Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND};
    int key = Calendar.SECOND;
    
    int res = Arrays.binarySearch(a, key);
    
    System.out.println(res);
    
    System.out.println("abcd,".substring(0, "abcd,".length()-1));
  }
  
  @Test
  public void testReadLinuxServices() throws Exception {
    System.out.println(StringUtils.rightPad("lineIndex", 10) + StringUtils.rightPad("serviceName", 15) + StringUtils.rightPad("port", 15) +
        StringUtils.rightPad("protocol", 15) + StringUtils.rightPad("aliases", 30) + StringUtils.rightPad("comment", 30));
    
    System.out.println(StringUtils.rightPad("-", 10, "-") + StringUtils.rightPad("-", 15, "-") + StringUtils.rightPad("-", 15, "-") +
        StringUtils.rightPad("-", 15, "-") + StringUtils.rightPad("-", 30, "-") + StringUtils.rightPad("-", 30, "-"));
        
    FbaFileUtils.readLines(new File("D:\\services"), new FileLineReadCallback(){
      
      Map<String, ProtoleBean> protocols = new HashMap<String, ProtoleBean>();
      Map<String, ProtoleBean> hasProtocols = new HashMap<String, ProtoleBean>();
      
      List<String> nameLines = FileUtils.readLines(new File("D:\\protocol-name.sql"));

      int protocolIdStart = 63;
      int protocolSeqStart = 800;
            
      @Override
      public void beforeProcessLine() throws IOException {
      }


      @Override
      public void processLine(long lineIndex, String line) throws IOException {
        if(lineIndex == 250) {
          //throw new IOException("exit");
        }
                
        if(StringUtils.isNotBlank(line) && !line.startsWith("#")) {
          String serviceName = line.substring(0, 15).trim();
          
          String remain = line.substring(15, line.length()).trim();
          
          if(!StringUtils.contains(remain, " ")) {
            String port = remain.split("[/]")[0];
            String protocol = remain.split("[/]")[1];
                        
            processProtocols(serviceName, port, protocol, "", "");
            
            System.out.println(StringUtils.rightPad(lineIndex + "", 10) + StringUtils.rightPad(serviceName, 15) + StringUtils.rightPad(port, 15) + StringUtils.rightPad(protocol, 15));
            return;
          }
          
          if(!StringUtils.contains(remain, "#")) {
            String port_protocol = remain.substring(0, 10).trim();
            
            String port = port_protocol.split("[/]")[0];
            String protocol = port_protocol.split("[/]")[1];
            
            String aliases = remain.substring(10, remain.length()).trim();
            
            processProtocols(serviceName, port, protocol, aliases, "");
            
            System.out.println(StringUtils.rightPad(lineIndex + "", 10) + StringUtils.rightPad(serviceName, 15) + StringUtils.rightPad(port, 15) + StringUtils.rightPad(protocol, 15) + StringUtils.rightPad(aliases, 30));
            return;
          }
          String port_protocol = remain.substring(0, 10).trim();
          
          String port = port_protocol.split("[/]")[0];
          String protocol = port_protocol.split("[/]")[1];
          
          String aliases_comment = remain.substring(10, remain.length()).trim();
          
          String[] aliases_comments = aliases_comment.split("[#]");
                    
          String aliases = "";
          String comment = "";
          
          if(aliases_comments.length > 0) {
            aliases = aliases_comments[0].trim();
            comment = aliases_comments[1].trim();
          }
          
          
          processProtocols(serviceName, port, protocol, aliases, comment);
          
          System.out.println(StringUtils.rightPad(lineIndex + "", 10) + StringUtils.rightPad(serviceName, 15) + StringUtils.rightPad(port, 15) + StringUtils.rightPad(protocol, 15) +  StringUtils.rightPad(aliases, 30) + StringUtils.rightPad(comment, 30));
        }
        
      }
      
      
      @Override
      public void afterProcessLine() throws IOException {
        List<String> lines = getProtocolLines(protocols);
        
        FileUtils.writeLines(new File("D:\\protocol.sql"), lines, false);
        
        lines = getProtocolLines(hasProtocols);
        
        FileUtils.writeLines(new File("D:\\protocol-exist.sql"), lines, false);
      }
      
      private List<String> getProtocolLines(Map<String, ProtoleBean> protocols) throws IOException {
        Iterator<String> keyIter = protocols.keySet().iterator();
        ProtoleBean pb = null;
        List<String> lines = new ArrayList<String>();
        
        while(keyIter.hasNext()) {
          pb = protocols.get(keyIter.next());
        
          String protocolSql = "INSERT INTO lj_protocol (id, seq, name, description, port, realtime_monitor, playback, private_name, " +
              "default_transport_protocol, allow_transport_protocol, allow_log_up_data, allow_log_down_data, allow_encodings, " +
              "param, serial, category, used, allow_session_policy) " +
              
                  "VALUES (" + (protocolIdStart++) +", " + (protocolSeqStart++) + ", '" + pb.getName() + "', '" +
                  pb.getComment() + "', '" + pb.getPort() +
                  "', 0, 0, '" + pb.getName() + "', '" + pb.getProtocol() + "', '" + pb.getProtocol() + "', 0, 0, " +
                      "'Unicode,GB2312,UTF8,UTF16', '', 0, '其他', 1, 1);";
          
          lines.add(protocolSql);
        }
        return lines;
      }
      
      
      private void processProtocols(String serviceName, String port, String protocol, String aliases, String comment) {
        ProtoleBean pb = null;
        
        serviceName = StringUtils.replace(serviceName, "'", "\'");
        if(StringUtils.isNotBlank(aliases)) {
          aliases = StringUtils.replace(serviceName, "'", "\'");
        }
        if(StringUtils.isNotBlank(comment)) {
          comment = StringUtils.replace(serviceName, "'", "\'");
        }
        
        String ptol = "tcp".equals(protocol) ? "6" : "17";
        
        if(protocols.containsKey(serviceName.toLowerCase())) {
          pb = protocols.get(serviceName.toLowerCase());
          
          if(StringUtils.isBlank(pb.getPort())) {
            pb.setPort(port);
          } else {
            if(!pb.getPort().contains(port)) {
              pb.setPort(pb.getPort() + "," + port);
            }
          }
          
          if(StringUtils.isBlank(pb.getProtocol())) {
            pb.setProtocol(ptol);
          } else {
            if(!pb.getProtocol().contains(ptol)) {
              pb.setProtocol(pb.getProtocol() + "," + ptol);
            }
          }
         
          if(StringUtils.isBlank(pb.getAliases())) {
            pb.setAliases(aliases);
          }
          
          if(StringUtils.isBlank(pb.getComment())) {
            pb.setComment(comment);
          }
          
        } else {
          pb = new ProtoleBean();
          pb.setName(serviceName);
          pb.setPort(port);
          pb.setProtocol(ptol);
          pb.setAliases(aliases);
          pb.setComment(comment);
          
          if(contains(nameLines, serviceName)) {
            hasProtocols.put(serviceName, pb);
          } else {
            protocols.put(serviceName.toLowerCase(), pb);
          }
        }
      }
            
      private boolean contains(List<String> nameLines, String name) {
        for(String n : nameLines) {
          if(n.equalsIgnoreCase(name)) {
            return true;
          }
        }
        return false;
      }
            
      class ProtoleBean {
        private String name;
        private String port;
        private String protocol;
        private String aliases;
        private String comment;
        public String getName() {
          return name;
        }

        
        public void setName(String name) {
          this.name = name;
        }

        
        public String getPort() {
          return port;
        }

        
        public void setPort(String port) {
          this.port = port;
        }

        
        public String getProtocol() {
          return protocol;
        }

        
        public void setProtocol(String protocol) {
          this.protocol = protocol;
        }

        
        public String getAliases() {
          return aliases;
        }

        
        public void setAliases(String aliases) {
          this.aliases = aliases;
        }

        
        public String getComment() {
          return comment;
        }

        
        public void setComment(String comment) {
          this.comment = comment;
        }
      }

      
    });
  }

  @Test
  public void testChos() {
    StringBuffer s = new StringBuffer("[{\"65535\":\"未知服务\"}");
    int len = s.length();
    int count = 0;
    
    while(len < 510) {
      s.append(",{\"65535\":\"未知服务\"}");
      len = s.length();
      count++;
    }
    
    s.append("]");
    
    System.out.println(s);
    System.out.println(count);
    
    System.out.println("Integer.MAX_VALUE=" + Integer.MAX_VALUE);
    System.out.println("Long.MAX_VALUE=" + Long.MAX_VALUE);
    
    for(int i=1;i<=10;i++) {
      System.out.println(i + "%3=" + (i%3));
    }
    
    Set<String> set = new java.util.concurrent.ConcurrentSkipListSet<String>();
    AtomicInteger ai = new java.util.concurrent.atomic.AtomicInteger();
    
  }
  
  @Test
  public void testListMem() {
    List<Integer> list = new ArrayList<Integer>();
    
    for(int i=1;i<=10000;i++) {
      list.add(i);
    }
   
    Runtime r1 = Runtime.getRuntime();
    System.out.println("JVM可以使用的总内存:    " + r1.totalMemory());
    System.out.println("JVM可以使用的剩余内存:    " + r1.freeMemory());
    System.out.println("JVM可以使用的处理器个数:    " + r1.availableProcessors());
    
    //list.clear();
    list = null;
    
    Runtime r2 = Runtime.getRuntime();
    System.out.println("JVM可以使用的总内存:    " + r2.totalMemory());
    System.out.println("JVM可以使用的剩余内存:    " + r2.freeMemory());
    System.out.println("JVM可以使用的处理器个数:    " + r2.availableProcessors());
    
  }
  
  @Test
  public void testReadBlackList() throws Exception {
    String filePath1 = "D:/llvtlsjyoyiczbkjsxpf/llvtlsjyoyiczbkjsxpf.txt";
    String filePath2 = "D:/ydxerpxkpcfqjaybcssw/ydxerpxkpcfqjaybcssw.txt";
    
    Map<String, FbaBlacklistExternalIp> blacklists1 = getBlacklists(filePath1);
    Map<String, FbaBlacklistExternalIp> blacklists2 = getBlacklists(filePath2);
    
    List<FbaBlacklistExternalIp> blacklists = new ArrayList<FbaBlacklistExternalIp>();
        
    Iterator<String> keys = blacklists1.keySet().iterator();
    
    while(keys.hasNext()) {
      String key = keys.next();
      blacklists.add(blacklists1.get(key));
    }
    
    keys = blacklists2.keySet().iterator();
    
    while(keys.hasNext()) {
      String key = keys.next();
      if(!blacklists1.containsKey(key)) {
        blacklists.add(blacklists2.get(key));
      }
    }
    
    blacklists1.clear();
    blacklists2.clear();
    
    List<String> lines = new ArrayList<String>();
    
    for(FbaBlacklistExternalIp b : blacklists) {
      lines.add("insert into fba_blacklist_ip(id, ip_start, ip_end, remark, create_time) " +
      		"values(" + b.getId() + ", " + b.getIp_start() + ", " + b.getIp_end() + ", '" + b.getRemark() + "', '" + b.getCreateTime() + "');");
    }
    
    blacklists.clear();
       
    int maxIndex = lines.size() - 1;
    
    FileUtils.writeLines(new File("D:/balcklist.1.sql"), lines.subList(0, 50000), false);
    FileUtils.writeLines(new File("D:/balcklist.2.sql"), lines.subList(50001, 100000), false);
    FileUtils.writeLines(new File("D:/balcklist.3.sql"), lines.subList(100000, 150000), false);
    FileUtils.writeLines(new File("D:/balcklist.4.sql"), lines.subList(150001, 200000), false);
    FileUtils.writeLines(new File("D:/balcklist.5.sql"), lines.subList(200001, maxIndex), false);
    
    System.out.println("successfully! Congratulations! good lucky! size:" + lines.size());
  }
  
  private Map<String, FbaBlacklistExternalIp> getBlacklists(String filePath) throws Exception {
    final Map<String, FbaBlacklistExternalIp> blacklists = new HashMap<String, FbaBlacklistExternalIp>();
    final Date date = new Date();
    
    FbaFileUtils.readLines(new File(filePath), new FbaFileUtils.FileLineReadCallback(){

      @Override
      public void beforeProcessLine() throws IOException {
      }

      @Override
      public void processLine(long lineIndex, String line) throws IOException {
        if(StringUtils.isBlank(line) || line.startsWith("#")) {
          return;
        }
        
        String remark = line.substring(0, line.lastIndexOf(":"));
        String _ips = line.substring(line.lastIndexOf(":") + 1, line.length());
                
        FbaBlacklistExternalIp b = new FbaBlacklistExternalIp();
        
        b.setId(IdFactory.getLongId());
        //b.setCreate_time(new Date());
        b.setRemark(remark.replace("'", "").replace("\"", " ").replace("\\", " "));
        
        String[] ips = _ips.split("[-]");
        
        b.setIp_start(FbaIpTools.getIpNumber(ips[0]));
        b.setIp_end(FbaIpTools.getIpNumber(ips[1]));
        b.setCreateTime(FbaDateFormatUtils.ISO_DATETIME_NO_T_FORMAT.format(date));
        //b.setUpdateTime(updateTime);
        
        blacklists.put(_ips, b);
      }

      @Override
      public void afterProcessLine() throws IOException {
      }
      
    });
    
    return blacklists;
  }
  @Test
  public void testInvalidIp() throws Exception {
    List<String> lines = FileUtils.readLines(new File("D:/tmp/invalid_ip.txt"));
    Map<Long, String> m = new HashMap<Long, String>();
    
    for(String line : lines) {
      if(StringUtils.isNotBlank(line)) {
        Long ip_long = Long.valueOf(line);
        m.put(ip_long, FbaIpTools.getIpv4(ip_long));
      }
    }
    
    Iterator<Long> keys = m.keySet().iterator();
    while(keys.hasNext()) {
      long ip_long = keys.next();
      String ip_string = m.get(ip_long);
      System.out.println(ip_long + ", " + ip_string);
    }
  }

  class FbaBlacklistExternalIp {
    private Long id;
    private Long ip_start;
    private Long ip_end;
    private String remark;
    private Date create_time;
    private Date update_time;
    
    private String createTime;
    private String updateTime;
    
    public Long getId() {
      return id;
    }
    
    public void setId(Long id) {
      this.id = id;
    }
    
    public Long getIp_start() {
      return ip_start;
    }
    
    public void setIp_start(Long ip_start) {
      this.ip_start = ip_start;
    }
    
    public Long getIp_end() {
      return ip_end;
    }
    
    public void setIp_end(Long ip_end) {
      this.ip_end = ip_end;
    }
    
    public String getRemark() {
      return remark;
    }
    
    public void setRemark(String remark) {
      this.remark = remark;
    }
    
    public Date getCreate_time() {
      return create_time;
    }
    
    public void setCreate_time(Date create_time) {
      this.create_time = create_time;
    }
    
    public Date getUpdate_time() {
      return update_time;
    }
    
    public void setUpdate_time(Date update_time) {
      this.update_time = update_time;
    }

    public String getCreateTime() {
      return createTime;
    }

    public void setCreateTime(String createTime) {
      this.createTime = createTime;
    }

    public String getUpdateTime() {
      return updateTime;
    }

    public void setUpdateTime(String updateTime) {
      this.updateTime = updateTime;
    }

  }
  
  @Test
  public void testFilePath() throws Exception {
    File file = new File("/data/tmp/blacklist.cvs");
    System.out.println(file.getPath());
    System.out.println(file.getAbsolutePath());
    System.out.println(file.getCanonicalPath());
    
    String srcFilePath = "D:/Work/恶意 IP 地址库/blacklist.all.txt";
    String dstFilePath = srcFilePath.substring(0, srcFilePath.lastIndexOf(".")) + ".bin";
    
    System.out.println(dstFilePath);
  }
  
  @Test
  public void testCopyOnWrite() throws Exception {
    //File file = null;
    //file = new File("D:/Workspace/Worked/guoyatech/prismtech-dba/src/auth/java/com/prismtech/dba/auth/service/FavoriteService.java");
    //file = new File("D:/Workspace/Worked/guoyatech/prismtech-dba/src/auth/java/com/prismtech/dba/auth/service/MenuSvervice.java");
        
    File directory = null;
    //directory = new File("D:/Workspace/Worked/guoyatech/prismtech-dba");
    directory = new File("D:/Workspace/Worked/guoyatech/javaweb-showcase");
    
    Iterator<File> fileIter = FileUtils.iterateFiles(directory, new String[]{"java"}, true);
    
    int size = 0;
    
    while(fileIter.hasNext()) {
      File f = fileIter.next();
      System.out.println(f);
      
      FbaFileUtils.readLines(f, new FileLineReadCallbackCustom(f));
      
      size += 1;
    }
    
    System.out.println("共修复 [" + size + "]个文件");
  }
    
  class FileLineReadCallbackCustom implements FileLineReadCallback {

    private File file;
    private long addNextIndex = 0;
    private List<String> newLines = new ArrayList<String>();
    
    public FileLineReadCallbackCustom(File file) {
      this.file = file;
    }
    
    @Override
    public void beforeProcessLine() throws IOException {
      if(file.isDirectory()) {
        throw new IOException("file [" + file + "] is directory.");
      }
    }

    @Override
    public void processLine(long lineIndex, String line) throws IOException {
      if(StringUtils.isNotBlank(line)) {
        if(line.contains("package")) {
          newLines.add("/*");
          newLines.add(" * $Id: ChaosTest.java 140 2017-02-06 06:18:19Z job2wd $");
          newLines.add(" * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/test/java/org/javaweb/showcase/test/chaos/ChaosTest.java $");
          newLines.add(" * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.");
          newLines.add(" */");
          newLines.add(line);
          
          addNextIndex = lineIndex + 1;
        } else {
          if(addNextIndex > 0 && lineIndex >= addNextIndex) {
            if(line.contains("@author")) {
              if(!line.contains("wangwd")) {
                newLines.add(" * @author ");
              }
            } else if(line.contains("@ClassName")) {
               // 丢弃该行
            } else if(line.contains("@Title")) {
              // 丢弃该行
            } else if(line.contains("@Description")) {
              if(line.contains("TODO")) {
                // 丢弃该行
              } else {
                String _line = line.substring(0, line.indexOf("@Description:") + "@Description:".length());
                if(line.length() == _line.length()) {
                   // 丢弃该行
                } else {
                  newLines.add(line.substring(0, line.indexOf("@Description:")) +
                      line.substring(line.indexOf("@Description:") + 1 + "@Description:".length(), line.length()));
                }
              }
            } else if(line.contains("@date") || line.contains("@version")) {
              newLines.add(" * @version $Revision: 140 $, $Date: 2015-7-9 下午2:12:14$");
            } else if(line.contains(" class ") || line.contains(" interface ") || line.contains(" enum ")) {
              int s = newLines.size() - 1;
              String cl = "";
              
              for(int i = s; i >= 0; i--) {
                String _line = newLines.get(i);
                if(_line.contains("*/")) {
                  cl = newLines.get(i - 1);
                  s = i;
                  break;
                }
              }
              
              if(!cl.contains("@LastChanged")) {
                newLines.add(s, " * @LastChanged $Author: job2wd $, $Date:: 2017-02-06 14:18:19#$");
              }
              newLines.add(line);
            } else {
              newLines.add(line);
            }
          }
        }
      } else {
        newLines.add(line);
      }
    }

    @Override
    public void afterProcessLine() throws IOException {
      /*
      for(String newLine : newLines) {
        System.out.println(newLine);
      }
      */
      FileUtils.writeLines(file, newLines, false);
    }
    
  }
       
  @Test
  public void testMath() throws Exception {
    System.out.println(Math.abs(-991));
    
    System.out.println(FbaNumberUtils.round(Math.random() * 1000000000, 0));
    
    System.out.println(new Random().nextInt(Integer.MAX_VALUE));
    
    System.out.println(new AtomicInteger(1).incrementAndGet());
    
    System.out.println("recursiveMathSum(10)=" + recursiveMathSum(10));
    
    int v1 = new Random().nextInt(Integer.MAX_VALUE);
    int v2 = new Random().nextInt(Integer.MAX_VALUE);
    int count = 1;
    while(v1 != v2) {
      System.out.println(v1 + "!=" + v2);
      v2 = new Random().nextInt(Integer.MAX_VALUE);
      if(v1 == v2) {
        System.out.println(v1 + "==" + v2);
        break;
      }
      System.out.println("count=" + (count++));
      if(count >= Integer.MAX_VALUE) {
        System.out.println("count max:" + Integer.MAX_VALUE);
        break;
      }
    }
  }
  
  /**
   * 递归计算 1+2+3+....n 的值
   * @param n
   * @return
   */
  private int recursiveMathSum(int n) {
    //System.out.println(n);
    if(n > 1) {
      return n + recursiveMathSum(n - 1);
    }
    return 1;
  }
  
  public void testRegex() {
    String ip_regex = "^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])" +
                   "\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)" +
                   "\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)" +
                   "\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$";
    
    String url_regex = "^(https?:\\/\\/)(([\\d]{1,3}\\.){3}[\\d]{1,3}|([\\d\\w_!~*\\\\'()-]+\\.)" +
                     "*([\\d\\w][\\d\\w-]{0,61})?[\\d\\w]\\.[\\w]{2,6})(:[\\d]{1,4})?((\\/?)|(\\/[\\d\\w_!~*\\\\'().;?:@&=+$,%#-]+)+\\/?)$";
    
    System.out.println("182.1.2.3".matches(ip_regex));
    System.out.println("https://www.baidu.com".matches(url_regex));
    
    String url_regex_all = "^((https|http|ftp|rtsp|mms)?://)"
        + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
        + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
        + "|" // 允许IP和DOMAIN（域名）
        + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.
        + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名
        + "[a-z]{2,6})" // first level domain- .com or .museum
        + "(:[0-9]{1,4})?" // 端口- :80
        + "((/?)|" // a slash isn't required if there is no file name
        + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
    
    System.out.println("baidu.com/a.d/b.txt".matches(url_regex_all));
    
    String md5_regex = "^[a-z0-9]{32}$";
    
    System.out.println("BABED0C564D657CDFE36F9AB6F9CBC03".toLowerCase().matches(md5_regex));
    
    String url_regex_start = "^((https|http|ftp|rtsp|mms)?://\\S*|^www\\.\\S*)$";
    System.out.println("http://www.baidu.com/a.d/b.txt".matches(url_regex_start));
    System.out.println("www.baidu.com/a.d/b.txt".matches(url_regex_start));
    
    String url_regex_start_http = "^((https|http|ftp|rtsp|mms)?://\\S*)$";
    System.out.println("http://www.baidu.com/a.d/b.txt".matches(url_regex_start_http));
    System.out.println("www.baidu.com/a.d/b.txt".matches(url_regex_start_http));
    
    String url_regex_start_www = "^(www\\.\\S*)$";
    System.out.println("http://www.baidu.com/a.d/b.txt".matches(url_regex_start_www));
    System.out.println("www.baidu.com/a.d/b.txt".matches(url_regex_start_www));
    
    System.out.println("http://www.baidu.com/a.d/b.txt".replaceFirst("(https|http|ftp|rtsp|mms)?://", ""));
    System.out.println("www.baidu.com/a.d/b.txt".replaceFirst("www\\.", ""));
  }
  
  @Test
  public void testDateUtils() {
    Date now = new Date();
    
    System.out.println(DateUtils.setSeconds(DateUtils.setMinutes(DateUtils.setHours(now, 0), 0), 0));
        
    Date bt = DateUtils.addHours(now, -6);
    //bt = DateUtils.addDays(now, -6);
    
    System.out.println("bt: " + FbaDateFormatUtils.ISO_DATETIME_NO_T_FORMAT.format(bt));
    System.out.println("et: " + FbaDateFormatUtils.ISO_DATETIME_NO_T_FORMAT.format(now));
    System.out.println("days: " + FbaDateUtils.getDays(bt, now));
    
    Map<Date, Date> dates = FbaDateUtils.splitDate(bt, now);
    
    Iterator<Date> iter = dates.keySet().iterator();
    Date d = null;
    while(iter.hasNext()) {
      d = iter.next();
      System.out.println(FbaDateFormatUtils.ISO_DATETIME_NO_T_FORMAT.format(d) +
          " ~ " + FbaDateFormatUtils.ISO_DATETIME_NO_T_FORMAT.format(dates.get(d)));
    }
  }
  
  @Test
  public void testZip() throws Exception {
      File srcFile = new File("D:/data");
      //srcFile = new File("D:/Job/Eclipse 研发环境设置");
      
      File zipFile = new File("D:/test-zip.zip");
      
      File unzipFile = new File("D:/tmp/test-zip");
      
      //doCompress(srcFile, dstFile);
      
      zipFile = FileZipUtils.zip(srcFile, zipFile);
      
      System.out.println(zipFile);
      
      //ArchiveInputStream input = new ArchiveStreamFactory().createArchiveInputStream(originalInput);
      
      //CompressorInputStream input = new CompressorStreamFactory().createCompressorInputStream(originalInputStream);
      
      //CompressorOutputStream gzippedOut = new CompressorStreamFactory().createCompressorOutputStream(CompressorStreamFactory.GZIP, originalOutputStream);
      
      //ZipArchiveOutputStream zipOutput = new ZipArchiveOutputStream(new File("D:/test-zip.zip"));
      
      srcFile = FileZipUtils.unzip(zipFile, unzipFile);
      
      System.out.println(srcFile);
  }
  
  @Test
  public void testTime() {
    Date date = new Date(1414634266l);
    System.out.println(date);
    System.out.println(date.getTime());
    System.out.println(new Date().getTime());
    
    System.out.println(IdFactory.getIntId());
    System.out.println(IdFactory.getLongId());
    System.out.println(IdFactory.getRandomId());
    System.out.println(IdFactory.getRandomCode());
  }
  
}
