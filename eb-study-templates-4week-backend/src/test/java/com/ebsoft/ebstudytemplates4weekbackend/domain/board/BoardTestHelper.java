package com.ebsoft.ebstudytemplates4weekbackend.domain.board;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.entity.Board;
import com.ebsoft.ebstudytemplates4weekbackend.domain.category.entity.Category;

/**
 * 게시판 테스트 헬퍼
 */
public class BoardTestHelper {

  public static Board createBoard(Category category) {
    return Board.builder()
        .category(category)
        .title("제목")
        .content("내용")
        .password("test123*")
        .userName("작성자")
        .viewCount(0)
        .build();
  }

  //todo Board Entity에 id까지 Builder로 지정해야해서 찝찝함.
  public static Board createBoardWithId(Category category) {
    return Board.builder()
        .id(1L)
        .category(category)
        .title("제목")
        .content("내용")
        .password("test123*")
        .userName("작성자")
        .viewCount(0)
        .build();
  }

}
