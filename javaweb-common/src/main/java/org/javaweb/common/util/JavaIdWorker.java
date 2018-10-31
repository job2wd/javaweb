/*
 * $Id: JavaIdWorker.java 5962 2018-09-20 05:19:38Z wangweidong $
 * $HeadURL: https://192.168.1.46/svn/ZYPFTJXT/03-%E8%BD%AF%E4%BB%B6/05-%E4%BA%91%E7%AB%AF%E7%AE%A1%E7%90%86%E8%BD%AF%E4%BB%B6/v1.0/trunk/04-%E6%BA%90%E4%BB%A3%E7%A0%81/hapr-cmcm/src/main/java/com/hapr/cmc/util/JavaIdWorker.java $
 * Copyright (c) 2018 Company, HAPR. All Rights Reserved.
 */
package org.javaweb.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http://www.cnblogs.com/skying555/p/8647617.html
 * 
 * 雪花算法自造全局自增ID，适合大数据环境的分布式场景
 * 由twitter公布的开源的分布式id算法snowflake（Java版本）
 * 
 * @author wangwd
 * @version $Revision: 5962 $, $Date: 2018年9月19日 上午9:38:24$
 * @LastChanged $Author: wangweidong $, $Date:: 2018-09-20 13:19:38#$
 */
public class JavaIdWorker {
  
  protected static final Logger LOG = LoggerFactory.getLogger(JavaIdWorker.class);
  
  private long workerId;
  
  private long datacenterId;
  
  private long sequence = 0L;
  
  private long twepoch = 1288834974657L;
  
  private long workerIdBits = 5L;
  
  private long datacenterIdBits = 5L;
  
  private long maxWorkerId = -1L ^ (-1L << workerIdBits);
  
  private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
  
  private long sequenceBits = 12L;
  
  private long workerIdShift = sequenceBits;
  
  private long datacenterIdShift = sequenceBits + workerIdBits;
  
  private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
  
  private long sequenceMask = -1L ^ (-1L << sequenceBits);
  
  private long lastTimestamp = -1L;
  
  public JavaIdWorker(long workerId, long datacenterId) {
    
    // sanity check for workerId
    
    if (workerId > maxWorkerId || workerId < 0) {
      
      throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
      
    }
    
    if (datacenterId > maxDatacenterId || datacenterId < 0) {
      
      throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
      
    }
    
    this.workerId = workerId;
    
    this.datacenterId = datacenterId;
    
    LOG.info(String.format(
        "worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d",
        timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId));
    
  }
  
  public synchronized long nextId() {
    
    long timestamp = timeGen();
    
    if (timestamp < lastTimestamp) {
      
      LOG.error(String.format("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp));
      
      throw new RuntimeException(
          String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
      
    }
    
    if (lastTimestamp == timestamp) {
      
      sequence = (sequence + 1) & sequenceMask;
      
      if (sequence == 0) {
        
        timestamp = tilNextMillis(lastTimestamp);
        
      }
      
    } else {
      
      sequence = 0L;
      
    }
    
    lastTimestamp = timestamp;
    
    return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift)
        | sequence;
    
  }
  
  protected long tilNextMillis(long lastTimestamp) {
    
    long timestamp = timeGen();
    
    while (timestamp <= lastTimestamp) {
      
      timestamp = timeGen();
      
    }
    
    return timestamp;
    
  }
  
  protected long timeGen() {
    return System.currentTimeMillis();
  }
  
}
