package com.domain.comment.dao;

import com.domain.comment.entity.Comment;
import com.global.utils.PropertiesMapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CommentRepository {

  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  private PropertiesMapper mapper;

  public CommentRepository() {
    mapper = new PropertiesMapper();

    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(mapper.getMyUrl(), mapper.getMyId(), mapper.getMyPw());

    } catch (SQLException e) {
      System.out.println("SQLException: " + e.getMessage());
      System.out.println("SQLState: " + e.getSQLState());
      System.out.println("VendorError: " + e.getErrorCode());
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Comment> findAllByBoardId(Long boardId) {
    String sql = "SELECT * FROM COMMENT WHERE board_id = ? ORDER BY created_at";
    List<Comment> list = new ArrayList<>();
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setLong(1, boardId);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        Comment comment = new Comment();
        comment.setContent(rs.getString(3));
        comment.setDateTime(rs.getTimestamp(4).toLocalDateTime());
        list.add(comment);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return list;
  }

  public Long getNextId() {
    String sql = "SELECT comment_id FROM COMMENT ORDER BY comment_id DESC";
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

  public boolean createComment(Comment comment) {
    String sql = "INSERT INTO COMMENT VALUES(?,?,?,?)";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setLong(1, comment.getCommentId());
      pstmt.setLong(2, comment.getBoardId());
      pstmt.setString(3, comment.getContent());
      pstmt.setTimestamp(4, Timestamp.valueOf(comment.getDateTime()));
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
}
