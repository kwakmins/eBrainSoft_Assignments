package com.ebsoft.ebstudytemplates4weekbackend.domain.board.dto.response;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.entity.Board;
import com.ebsoft.ebstudytemplates4weekbackend.domain.comment.dto.response.CommentResDto;
import com.ebsoft.ebstudytemplates4weekbackend.domain.file.dto.response.FileResDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

/**
 * 게시판 상세보기 ResDto
 */
@Getter
public class BoardDetailResDto {

  //카테고리 이름 (1:1)
  private String categoryName;

  //유저명
  private String userName;

  //제목
  private String title;

  //내용
  private String content;

  //조회수
  private Integer viewCount;

  // 댓글 리스트 (N:1)
  private List<CommentResDto> comments;

  // 파일 리스트 (N:1)
  private List<FileResDto> files;

  // 작성일
  private LocalDateTime createdTime;

  // 수정일
  private LocalDateTime updatedTime;


  public BoardDetailResDto(Board board) {
    this.categoryName = board.getCategory().getName();
    this.userName = board.getUserName();
    this.title = board.getTitle();
    this.content = board.getContent();
    this.viewCount = board.getViewCount();
    this.createdTime = board.getCreatedTime();
    this.updatedTime = board.getUpdatedTime();
    this.comments = board.getComments().stream()
        .map(CommentResDto::new)
        .collect(Collectors.toList());
    this.files = board.getFiles().stream()
        .map(FileResDto::new)
        .collect(Collectors.toList());
  }
}
