package com.domain.board.dao;

import com.domain.board.entity.Board;
import com.global.utils.PropertiesMapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    if (!board.valid()) {
      return false;
    }
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

  public List<Board> getList(int pageNumber) {
    long x = getNextId(); // pstm을 해당 메서드에서 다르게 생성하게 때문에 위치 중요
    String sql = "SELECT * FROM BOARD WHERE board_id < ? ORDER BY board_id DESC LIMIT 10";
    List<Board> list = new ArrayList<>();
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setLong(1, x - (pageNumber - 1) * 10L);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        Board board = new Board();
        board.setBoardId(rs.getLong(1));
        board.setCategoryId(rs.getLong(2));
        board.setUser(rs.getString(3));
        board.setTitle(rs.getString(5));
        board.setViewCount(rs.getInt(7));
        board.setCreatedAt(rs.getTimestamp(8).toLocalDateTime());
        board.setUpdatedAt(rs.getTimestamp(9).toLocalDateTime());
        list.add(board);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  public boolean nextPage(int pageNumber) {
    long x = getNextId();
    String sql = "SELECT * FROM BOARD WHERE board_id < ? ORDER BY board_id DESC LIMIT 10";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setLong(1, x - (pageNumber - 1) * 10L);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public Board findOne(long boardId) {
    String sql = "SELECT * FROM BOARD WHERE board_id=?";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setLong(1, boardId);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        Board board = new Board();
        board.setBoardId(rs.getLong(1));
        board.setCategoryId(rs.getLong(2));
        board.setUser(rs.getString(3));
        board.setPassword(rs.getString(4));
        board.setTitle(rs.getString(5));
        board.setContent(rs.getString(6));
        board.setViewCount(rs.getInt(7));
        board.setCreatedAt(rs.getTimestamp(8).toLocalDateTime());
        board.setUpdatedAt(rs.getTimestamp(9).toLocalDateTime());
        return board;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  public void plusViewCount(Long boardId) {
    String sql = "UPDATE BOARD SET view_count = view_count+1 WHERE board_id = ?";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setLong(1, boardId);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}