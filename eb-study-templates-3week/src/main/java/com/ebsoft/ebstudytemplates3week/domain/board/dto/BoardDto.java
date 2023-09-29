package com.ebsoft.ebstudytemplates3week.domain.board.dto;

import com.ebsoft.ebstudytemplates3week.domain.category.dto.CategoryDto;
import com.ebsoft.ebstudytemplates3week.domain.comment.dto.CommentDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto {

  //게시판 id
  private Long boardId;
  //카테고리 (1:1)
  private CategoryDto category;
  //유저명
  private String user;
  //비밀번호
  private String password;
  //제목
  private String title;
  //내용
  private String content;
  //조회수
  private Integer viewCount;
  //생성 날짜
  private LocalDateTime createdTime;
  //업데이트 날짜
  private LocalDateTime updatedTime;
  // 댓글 리스트
  private List<CommentDto> comments;
}
