package com.ebsoft.ebstudytemplates3week.domain.board.application;

import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.BoardWriteDto;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardServiceTest {

  @Autowired
  private BoardService boardService;

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
  void should_save_when_writeBoard() {
    boardService.addBoard(boardWriteDto);
  }
}