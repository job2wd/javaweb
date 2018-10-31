/*
 * $Id: JavaPinyinUtils.java 5975 2018-09-26 09:02:15Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaPinyinUtils.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.showcase.springboot.util;

import org.apache.commons.lang3.StringUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 中文字符转换为汉语拼音工具栏（使用了插件 pinyin4j-2.5.0.jar）
 * 
 * @author wangwd
 * @version $Revision: 5975 $, $Date: 2018年8月20日 上午9:27:36$
 * @LastChanged $Author: wangweidong $, $Date:: #$
 */
public class JavaPinyinUtils {
  
  /**
   * 获取传入所有汉字字符首字母大写输出（非汉字不做处理，如中文或英文状态下的一些符号、字母、数字等）
   */
  public static String getFirstLettersUppercase(String chineseLetters) {
    return getFirstLetters(chineseLetters, HanyuPinyinCaseType.UPPERCASE, HanyuPinyinToneType.WITHOUT_TONE);
  }
  
  /**
   * 获取传入所有汉字字符首字母小写输出（非汉字不做处理，如中文或英文状态下的一些符号、字母、数字等）
   */
  public static String getFirstLettersLowercase(String chineseLetters) {
    return getFirstLetters(chineseLetters, HanyuPinyinCaseType.LOWERCASE, HanyuPinyinToneType.WITHOUT_TONE);
  }
  
  /**
   * 获取传入所有汉字字符首字母（非汉字不做处理，如中文或英文状态下的一些符号、字母、数字等）
   * @param chineseLetters 汉字字符
   * @param caseType 大小写输出
   * @param toneType 是否带声调输出
   */
  public static String getFirstLetters(String chineseLetters, HanyuPinyinCaseType caseType, HanyuPinyinToneType toneType) {
    if(StringUtils.isBlank(chineseLetters)) {
      return null;
    }
    
    char[] cl_chars = chineseLetters.trim().toCharArray();
    String pinyin = "";
    
    HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
    defaultFormat.setCaseType(caseType); // 大写/小写输出
    defaultFormat.setToneType(toneType); // 是否带声调
    //defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
    
    try {
      for (int i = 0; i < cl_chars.length; i++) {
        String str = String.valueOf(cl_chars[i]);
        
        if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
          pinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0].substring(0, 1);
        } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
          pinyin += cl_chars[i];
        } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母
          pinyin += String.valueOf(cl_chars[i]).toUpperCase();
        } else {// 否则不转换
          pinyin += cl_chars[i];//如果是标点符号的话，带着
        }
      }
    } catch (BadHanyuPinyinOutputFormatCombination e) {
     throw new RuntimeException("字符不能转成汉语拼音");
    }
    
    return pinyin;
  }
  
}
