package com.ebsoft.ebstudytemplates4weekbackend.domain.board.dto.response;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.entity.Board;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class BoardResDto {

  //카테고리 이름 (1:1)
  private String categoryName;

  //유저명
  private String userName;

  //제목
  private String title;

  //조회수
  private Integer viewCount;

  // 작성일
  private LocalDateTime createdTime;

  // 수정일
  private LocalDateTime updatedTime;


  public BoardResDto(Board board) {
    this.categoryName = board.getCategory().getName();
    this.userName = board.getUserName();
    this.title = board.getTitle();
    this.viewCount = board.getViewCount();
    this.createdTime = board.getCreatedTime();
    this.updatedTime = board.getUpdatedTime();
  }
}
