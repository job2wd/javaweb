/*
 * $Id: MysqlTestDataGenerator.java 140 2017-02-06 06:18:19Z job2wd $
 * $HeadURL: http://code.taobao.org/svn/job2wd/javaweb/javaweb-showcase/src/test/java/org/javaweb/showcase/test/chaos/MysqlTestDataGenerator.java $
 * Copyright (c) 2014 Company, guoyatech. All Rights Reserved.
 */
package org.javaweb.showcase.test.chaos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.javaweb.showcase.test.BaseJunitTest;
import org.junit.Test;


/**
 * 
 * @author wangwd
 * @version $Revision: 140 $, $Date: 2016-5-23 下午6:05:48$
 * @LastChanged $Author: job2wd $, $Date:: #$
 */
public class MysqlTestDataGenerator extends BaseJunitTest {

  @Test
  public void testGeneratingData() {
    Connection connection = null;
    Statement ctatement = null;
    ResultSet resultSet = null;
    
    String _table_name = "malware_file_event_20160520";
    _table_name = "malware_url_list";

    try {
      //Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
      connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ids_data", "root", "cyberaudit"); //链接本地MYSQL

      ctatement = connection.createStatement();

      resultSet = ctatement.executeQuery("SHOW CREATE TABLE " + _table_name);
      
      int index = 1;
      while (resultSet.next()) {
        Object o = resultSet.getObject(index);
        System.out.println(o);
        index++;
      }
      /*
       * //新增一条数据
       * stmt.executeUpdate(
       * "INSERT INTO user (username, password) VALUES ('init', '123456')");
       * ResultSet res = stmt.executeQuery("select LAST_INSERT_ID()");
       * int ret_id;
       * if (res.next()) {
       * ret_id = res.getInt(1);
       * System.out.print(ret_id);
       * }
       * 
       * //删除一条数据
       * String sql = "DELETE FROM user WHERE id = 1";
       * long deleteRes = stmt.executeUpdate(sql);
       * //如果为0则没有进行删除操作，如果大于0，则记录删除的条数
       * System.out.print("DELETE:" + deleteRes);
       * 
       * //更新一条数据
       * String updateSql = "UPDATE user SET username = 'xxxx' WHERE id = 2";
       * long updateRes = stmt.executeUpdate(updateSql);
       * System.out.print("UPDATE:" + updateRes);
       */

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (resultSet != null) {
          resultSet.close();
        }
        if (ctatement != null) {
          ctatement.close();
        }
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

}
