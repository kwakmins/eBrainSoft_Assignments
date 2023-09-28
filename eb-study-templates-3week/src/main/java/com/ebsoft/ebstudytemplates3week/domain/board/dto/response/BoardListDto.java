package com.ebsoft.ebstudytemplates3week.domain.board.dto.response;

import com.ebsoft.ebstudytemplates3week.domain.category.dto.CategoryDto;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class BoardListDto {

  // 게시판 id
  Long BoardId;
  // 카테고리
  CategoryDto category;
  // 작성자
  String user;
  //제목
  private String title;
  //내용
  private String content;
  //조회수
  private Integer viewCount;
  //생성 날짜
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdTime;
  //업데이트 날짜
  private LocalDateTime updatedTime;

}
