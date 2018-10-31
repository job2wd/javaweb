/*
 * $Id: CacheManager.java 153 2017-02-17 03:38:09Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-common/src/main/java/org/javaweb/common/cache/simple/CacheManager.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.data.service.cache.simple;

import java.util.ArrayList;
import java.util.List;

import org.javaweb.data.service.cache.simple.impl.CacheComplexKey4HelloWord;
import org.javaweb.data.service.cache.simple.impl.CacheKey4HelloWord;


/**
 * FBA 基础数据缓存管理类。<br>
 * 基础数据目前包括：域信息、设备信息、黑白名单基线信息、协议信息、引擎信息、流量频次告警配置信息。<br>
 * <b style="color:#FF0000;">注意：该缓存中的信息，界面上或其他地方对数据库中相应信息做了变更后要及时同步更新该缓存中信息！</b>
 * 
 * @author wangwd
 * @version $Revision: 153 $, $Date: 2016-4-14 下午3:10:25$
 * @LastChanged $Author: job2wd $, $Date:: 2017-02-17 11:38:09#$
 */
public final class CacheManager {
  
  public static final String CACHE_NAMESPACE_POLICY_CACHE = "POLICY_CACHE";
  
  //************ Policy Cache Start ************//
  public static void initAllHelloWords() {//DiPolicyService diPolicyService) {
    DataCacher.putCache(CACHE_NAMESPACE_POLICY_CACHE, new CacheKey4HelloWord());
    
    try {
      List<CacheComplexKey4HelloWord.HelloWord> allPolicys = new ArrayList<CacheComplexKey4HelloWord.HelloWord>();//diPolicyService.show(null);
      addHelloWords(allPolicys);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  public static void addHelloWords(List<CacheComplexKey4HelloWord.HelloWord> policys) {
    DataCacher.add(CACHE_NAMESPACE_POLICY_CACHE, policys);
  }
  
  public static void addHelloWord(CacheComplexKey4HelloWord.HelloWord policy) {
    DataCacher.add(CACHE_NAMESPACE_POLICY_CACHE, policy);
  }

  public static void updateHelloWord(CacheComplexKey4HelloWord.HelloWord policy) {
    DataCacher.update(CACHE_NAMESPACE_POLICY_CACHE, policy);
  }
  
  public static void removeHelloWord(CacheComplexKey4HelloWord.HelloWord policy) {
    DataCacher.remove(CACHE_NAMESPACE_POLICY_CACHE, policy);
  }
  
  public static void removeHelloWordById(Long id) {
    DataCacher.remove(CACHE_NAMESPACE_POLICY_CACHE, "id", id);
  }
  
  public static void removeHelloWordByName(String name) {
    DataCacher.remove(CACHE_NAMESPACE_POLICY_CACHE, "name", name);
  }
  
  public static void removeHelloWordByIpPort(String ipPort) {
    DataCacher.remove(CACHE_NAMESPACE_POLICY_CACHE, CacheComplexKey4HelloWord.KEY_NAME, ipPort);
  }
  
  public static CacheComplexKey4HelloWord.HelloWord getHelloWordById(Integer id) {
    return (CacheComplexKey4HelloWord.HelloWord)DataCacher.get(CACHE_NAMESPACE_POLICY_CACHE, "id", id);
  }
  
  public static CacheComplexKey4HelloWord.HelloWord getHelloWordByName(String name) {
    return (CacheComplexKey4HelloWord.HelloWord)DataCacher.get(CACHE_NAMESPACE_POLICY_CACHE, "name", name);
  }
  
  public static CacheComplexKey4HelloWord.HelloWord getHelloWordByIpPort(String ipPort) {
    return (CacheComplexKey4HelloWord.HelloWord)DataCacher.get(CACHE_NAMESPACE_POLICY_CACHE, CacheComplexKey4HelloWord.KEY_NAME, ipPort);
  }
  
  public static CacheComplexKey4HelloWord.HelloWord getHelloWordByIpPort(String srcIp, String desIp, String desPort) {
    return (CacheComplexKey4HelloWord.HelloWord)DataCacher.get(CACHE_NAMESPACE_POLICY_CACHE, CacheComplexKey4HelloWord.KEY_NAME, srcIp + "-" + desIp + ":" + desPort);
  }
  
}
