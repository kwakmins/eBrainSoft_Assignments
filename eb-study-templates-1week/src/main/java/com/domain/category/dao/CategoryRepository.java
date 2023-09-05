package com.domain.category.dao;

import com.global.utils.PropertiesMapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRepository {

  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  private PropertiesMapper mapper;

  public CategoryRepository() throws ClassNotFoundException {
    mapper = new PropertiesMapper();

    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(mapper.getMyUrl(), mapper.getMyId(), mapper.getMyPw());

    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("SQLState: " + e.getSQLState());
      System.out.println("VendorError: " + e.getErrorCode());
    }
  }
}
