package com.ebsoft.ebstudytemplates4weekbackend.domain.comment.dto.response;

import com.ebsoft.ebstudytemplates4weekbackend.domain.comment.entity.Comment;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * 댓글 조회 ResDto
 */
@Getter
public class CommentResDto {

  //내용
  private String content;

  //만든 시간
  private LocalDateTime createdTime;

  public CommentResDto(Comment comment) {
    this.content = comment.getContent();
    this.createdTime = comment.getCreatedTime();
  }
}
