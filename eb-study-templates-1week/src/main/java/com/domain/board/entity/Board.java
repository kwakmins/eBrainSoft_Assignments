package com.domain.board.entity;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class Board {

  private Long boardId;
  private Long categoryId;
  private String user;
  private String password;
  private String title;
  private String content;
  private Integer viewCount;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Board(Long boardId, Long categoryId, String user, String password, String title,
      String content, Integer viewCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.boardId = boardId;
    this.categoryId = categoryId;
    this.user = user;
    this.password = password;
    this.title = title;
    this.content = content;
    this.viewCount = viewCount;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Board() {
  }

  public Long getBoardId() {
    return boardId;
  }

  public void setBoardId(Long boardId) {
    this.boardId = boardId;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getViewCount() {
    return viewCount;
  }

  public void setViewCount(Integer viewCount) {
    this.viewCount = viewCount;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean valid() {

    if (user.length() < 3 || user.length() >= 5) {
      return false;
    }

    if (!Pattern.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{4,16}$",
        password)) {
      return false;
    }
    if (title.length() < 4 || title.length() >= 100) {
      return false;
    }
    if (content.length() < 4 || content.length() >= 2000) {
      return false;
    }
    return true;
  }

}