package com.ebsoft.ebstudytemplates2week.domain.comment.entity;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Comment {

  private Long commentId;
  private Long boardId;
  private String content;
  private LocalDateTime dateTime;
}
