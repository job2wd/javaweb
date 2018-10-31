/*
 * $Id: ChineseDistinguisher.java 80 2017-01-20 04:21:11Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/test/java/org/javaweb/showcase/test/chaos/ChineseDistinguisher.java $
 * Copyright (c) 2014 Company, LJ. All Rights Reserved.
 */
package org.javaweb.showcase.test.chaos;

/**
 * 
 * @author wangwd
 * @version $Revision: 80 $, $Date: 2014-10-8 上午11:40:49$
 * @LastChanged $Author: job2wd $, $Date:: #$
 */
public class ChineseDistinguisher {

  //GENERAL_PUNCTUATION 判断中文的"号

  // CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号

  // HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号

  public static boolean isChinese(char c) {

    Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

    if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS

    || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS

    || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A

    || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION

    || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION

    || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {

      return true;

    }

    return false;

  }

  public static void main(String[] args) {

    System.out.println(isChinese('！'));

    System.out.println(isChinese('A'));

  }

}
