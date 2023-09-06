package com.domain.category.dao;

import com.global.utils.PropertiesMapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

  public List<String> getAllCategoryName() {
    List<String> list = new ArrayList<>();
    String sql = "SELECT category_name FROM CATEGORY";
    try {
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        list.add(rs.getString("category_name"));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return list;
  }
}
