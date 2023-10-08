package com.ebsoft.ebstudytemplates3week;

import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.jupiter.api.Test;

/**
 * MySql 연결 테스트
 */
public class mySqlTest {

  @Test
  public void testConnection() throws Exception {
    Class.forName("com.mysql.cj.jdbc.Driver");
    try (Connection connection = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/ebrainsoft_study?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8",
        "ebsoft", "ebsoft")) {
      System.out.println(connection);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
