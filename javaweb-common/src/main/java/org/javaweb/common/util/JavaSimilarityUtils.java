/*
 * $Id: JavaSimilarityUtils.java 5962 2018-09-20 05:19:38Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaSimilarityUtils.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 计算相似度：
 * <p>
 *  相似度度量（Similarity），即计算个体间的相似程度，相似度度量的值越小，说明个体间相似度越小，相似度的值越大说明个体差异越大。
 *  对于多个不同的文本或者短文本对话消息要来计算他们之间的相似度如何，一个好的做法就是将这些文本中词语，映射到向量空间，形成文本中文字和向量数据的映射关系，通过计算几个或者多个不同的向量的差异的大小，来计算文本的相似度。
 * </p>
 * <p><b>向量空间余弦相似度(Cosine Similarity):</b>
          余弦相似度用向量空间中两个向量夹角的余弦值作为衡量两个个体间差异的大小。余弦值越接近1，就表明夹角越接近0度，也就是两个向量越相似，这就叫"余弦相似性"。
 * </p>
 * 
 * @author wangwd
 * @version $Revision: 5962 $, $Date: 2018年9月11日 上午9:30:25$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-20 13:19:38#$
 */
public class JavaSimilarityUtils {
  
  private static final char[] seps = {'(', ')', '（', '）', ',', '，', '.', '。', ';', '；', '<', '《', '>', '》', ':', '：', '\'', '‘', '’',
      '[', '【', ']', '】', '-', '-', '_', '—', '+', '=', '\\', '、', '|', '`', '·', '~', '!', '！', '@',
      '@', '#', '$', '￥', '%', '^', '…', '*', '?', '？', '/' };
  
  /**
   * 求余弦相似度
   */
  public static double similarity(String s1, String s2) {
    Map<Character, int[]> vectorMap = getVectorMap(s1, s2);
    
    double result = pointMulti(vectorMap) / sqrtMulti(vectorMap);
    return result;
  }
  
  private static Map<Character, int[]> getVectorMap(String s1, String s2) {
    Map<Character, int[]> vectorMap = new HashMap<Character, int[]>();
    int[] tempArray = null;
    
    for (Character character1 : s1.toCharArray()) {
      if (ArrayUtils.contains(seps, character1)) {
        continue;
      }
      if (vectorMap.containsKey(character1)) {
        vectorMap.get(character1)[0]++;
      } else {
        tempArray = new int[2];
        tempArray[0] = 1;
        tempArray[1] = 0;
        vectorMap.put(character1, tempArray);
      }
    }
    
    for (Character character2 : s2.toCharArray()) {
      if (ArrayUtils.contains(seps, character2)) {
        continue;
      }
      
      if (vectorMap.containsKey(character2)) {
        vectorMap.get(character2)[1]++;
      } else {
        tempArray = new int[2];
        tempArray[0] = 0;
        tempArray[1] = 1;
        vectorMap.put(character2, tempArray);
      }
    }
    
    return vectorMap;
  }
  
  private static double sqrtMulti(Map<Character, int[]> paramMap) {
    double result = 0;
    result = squares(paramMap);
    result = Math.sqrt(result);
    return result;
  }
  
  // 求平方和
  private static double squares(Map<Character, int[]> paramMap) {
    double result1 = 0;
    double result2 = 0;
    Set<Character> keySet = paramMap.keySet();
    for (Character character : keySet) {
      int temp[] = paramMap.get(character);
      result1 += (temp[0] * temp[0]);
      result2 += (temp[1] * temp[1]);
    }
    return result1 * result2;
  }
  
  // 点乘法
  private static double pointMulti(Map<Character, int[]> paramMap) {
    double result = 0;
    Set<Character> keySet = paramMap.keySet();
    for (Character character : keySet) {
      int temp[] = paramMap.get(character);
      result += (temp[0] * temp[1]);
    }
    return result;
  }
  
}
