/*
 * $Id:$
 * $HeadURL:$
 * Copyright (c) 2014 Company, Prismtech. All Rights Reserved.
 */
package org.javaweb.showcase.socket.ssl;

import java.io.ByteArrayInputStream;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author wangwd
 * @version $Revision:$, $Date: 2018年5月11日 上午11:34:33$
 * @LastChanged $Author:$, $Date:: #$
 */
public class TestClient {

  /**
   * @param args
   */
  public static void main(String[] args) {
    try {
      Configuration.configLocation = "D:/conf.properties";
      Properties p = Configuration.getConfig();

      SocketTCPClient client = new SocketTCPClient();

      Scanner in = new Scanner(System.in);
      String line = "";

      while (true) {
        Logger.info("请输入值（输入 ‘exit’ 将退出客户端）：\n");
        
        line = in.nextLine();

        if (StringUtils.isBlank(line)) {
          Logger.info("请输入值（输入 ‘exit’ 将退出客户端）：\n");
          continue;
        }

        ByteArrayInputStream requestData = new ByteArrayInputStream(line.getBytes(p.getProperty("socketStreamEncoding")));

        byte[] servereResponse = client.request(requestData);

        Logger.debug("服务端回应数据：" + new String(servereResponse, p.getProperty("socketStreamEncoding")));

        if ("exit".equals(line)) {
          Logger.debug("客户端即将退出....");
          Thread.sleep(1000);
          break;
        }

        SocketIOUtils.close(requestData, null);
      }

      in.close();
      System.exit(0);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
