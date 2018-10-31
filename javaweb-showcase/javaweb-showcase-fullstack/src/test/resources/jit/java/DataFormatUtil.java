/**
 * 数据格式化相关工具类
 */
package com.bop.petbook.core.util.data;

import java.nio.charset.Charset;
import java.util.Date;

public final class DataFormatUtil {

  /**
   * 计算字符串的长度，一个汉字按照2个字符计算。
   */
  public static int length(String args) {
    return args.getBytes(Charset.forName("GBK")).length;
  }

  /**
   * <p>
   * 按照参数len指定的长度格式化字符串args，一个汉字按照2个字符计算。
   * </p>
   * <p>
   * 如果args的长度大于len，怎返回args
   * </p>
   * <p>
   * 如果args的长度小于len，则在args左面补充空格字符
   * </p>
   * 
   * @param len
   *            格式化后字符串总长度
   * @param args
   *            需格式化的字符串
   * @return
   */
  public static String format(int len, String args) {
    if (args == null) {
      return String.format("%" + len + "s", "");
    }
    String s = String.format("%" + len + "s", new String(args
        .getBytes(Charset.forName("GBK")), Charset
        .forName("ISO-8859-1")));
    s = new String(s.getBytes(Charset.forName("ISO-8859-1")), Charset
        .forName("GBK"));
    return s;
  }

  /**
   * 格式化浮点数，长度不足时左面补充空格字符
   * 
   * @param len
   *            总长度
   * @param digital
   *            小数点后位数
   * @param args
   *            需格式化的浮点数
   * @return
   */
  public static String format(int len, int digital, double args) {
    return String.format("%" + len + "." + digital + "f", args);
  }

  /**
   * 格式化整数，长度不足时左面补充空格字符
   * 
   * @param len
   *            总长度
   * @param args
   *            需格式化的整数
   * @return
   */
  public static String format(int len, int args) {
    return String.format("%" + len + "d", args);
  }

  /**
   * 格式化日期(yyyy-mm-dd)
   * 
   * @param args
   *            日期
   * @return
   */
  public static String format(Date args) {
    return String.format("%tF", args);
  }

}
