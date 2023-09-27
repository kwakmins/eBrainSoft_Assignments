package com.ebsoft.ebstudytemplates3week.domain.board.application;

import com.ebsoft.ebstudytemplates3week.domain.board.dao.BoardRepository;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.BoardWriteDto;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class BoardServiceTest {

  @Autowired
  private BoardService boardService;
  @Autowired
  private BoardRepository boardRepository;

  private BoardWriteDto boardWriteDto;

  /*
  제약 조건에 맞는 올바른 데이터 주입
   */
  @BeforeEach
  void setUp() {
    boardWriteDto = BoardWriteDto.builder()
        .categoryId(1L)
        .title("title")
        .content("content")
        .user("user")
        .password("pass123*")
        .passwordConfirm("pass123*")
        .createdTime(LocalDateTime.now())
        .updatedTime(LocalDateTime.now())
        .build();
  }

  @Test
  @DisplayName("게시판이 정상적으로 저장이 되어야함.")
  @Transactional
  void should_save_when_writeBoard() {
    Long prevBoardCnt = boardRepository.totalBoardCnt(); // 추가 되기 전 게시판 수
    boardService.addBoard(boardWriteDto); // 추가
    Long afterBoardCnt = boardRepository.totalBoardCnt(); // 추가 된 후 게시판 수
    Assertions.assertThat(prevBoardCnt + 1L).isEqualTo(afterBoardCnt);
  }
}