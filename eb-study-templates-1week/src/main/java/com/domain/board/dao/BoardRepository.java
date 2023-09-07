package com.domain.board.dao;

import com.domain.board.entity.Board;
import com.global.utils.PropertiesMapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class BoardRepository {

  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  private PropertiesMapper mapper;

  public BoardRepository() {
    mapper = new PropertiesMapper();

    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(mapper.getMyUrl(), mapper.getMyId(), mapper.getMyPw());

    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("SQLState: " + e.getSQLState());
      System.out.println("VendorError: " + e.getErrorCode());
    } catch (ClassNotFoundException e) {
      System.out.println("ClassNotFoundException: " + e.getMessage());

    }
  }

  public Long getNextId() {
    String sql = "SELECT board_id FROM BOARD ORDER BY board_id DESC";
    try {
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        return rs.getLong(1) + 1L;
      }
      return 1L;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public boolean createBoard(Board board) {
    String sql = "INSERT INTO BOARD VALUES(?,?,?,?,?,?,?,?,?)";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setLong(1, board.getBoardId());
      pstmt.setLong(2, board.getCategoryId());
      pstmt.setString(3, board.getUser());
      pstmt.setString(4, board.getPassword());
      pstmt.setString(5, board.getTitle());
      pstmt.setString(6, board.getContent());
      pstmt.setInt(7, board.getViewCount());
      pstmt.setTimestamp(8, Timestamp.valueOf(board.getCreatedAt()));
      pstmt.setTimestamp(9, Timestamp.valueOf(board.getUpdatedAt()));
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
}
