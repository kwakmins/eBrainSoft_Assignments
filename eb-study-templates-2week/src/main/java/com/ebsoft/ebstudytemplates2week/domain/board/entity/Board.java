package com.ebsoft.ebstudytemplates2week.domain.board.entity;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
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
}
