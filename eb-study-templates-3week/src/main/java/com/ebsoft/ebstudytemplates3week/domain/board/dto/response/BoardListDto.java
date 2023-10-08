package com.ebsoft.ebstudytemplates3week.domain.board.dto.response;

import com.ebsoft.ebstudytemplates3week.domain.category.dto.CategoryDto;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BoardListDto {

  // 게시판 id
  private Long BoardId;
  // 카테고리
  private CategoryDto category;
  // 작성자
  private String user;
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
  // 첨부파일 유무
  private Boolean haveFile;

}
