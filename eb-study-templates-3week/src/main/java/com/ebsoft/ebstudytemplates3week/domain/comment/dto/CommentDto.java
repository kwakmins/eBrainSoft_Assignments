package com.ebsoft.ebstudytemplates3week.domain.comment.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {

  // 1:N 관계인 board의 아이디
  public Long boardId;
  // 내용
  @NotBlank
  public String content;
  // 작성일
  public LocalDateTime createdTime;
}
