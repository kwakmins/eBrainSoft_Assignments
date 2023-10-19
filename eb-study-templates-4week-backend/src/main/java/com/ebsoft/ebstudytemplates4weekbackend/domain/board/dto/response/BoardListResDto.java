package com.ebsoft.ebstudytemplates4weekbackend.domain.board.dto.response;

import java.util.List;
import lombok.Getter;

/**
 * 게시판 목록 조회 ResDto
 */
@Getter
public class BoardListResDto {

  // 게시판 목록들
  private List<BoardResDto> boards;

  // 총 검색 갯수
  private Long totalElements;

  // 총 페이지 갯수
  private Integer totalPages;

  // 현재 페이지
  private Integer currentPage;

  public BoardListResDto(List<BoardResDto> boards, Long totalElements, Integer totalPages,
      Integer currentPage) {
    this.boards = boards;
    this.totalElements = totalElements;
    this.totalPages = totalPages;
    this.currentPage = currentPage;
  }
}
