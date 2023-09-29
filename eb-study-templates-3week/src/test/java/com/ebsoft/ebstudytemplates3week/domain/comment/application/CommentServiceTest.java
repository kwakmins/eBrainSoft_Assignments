package com.ebsoft.ebstudytemplates3week.domain.comment.application;

import com.ebsoft.ebstudytemplates3week.domain.board.application.BoardService;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.BoardWriteDto;
import com.ebsoft.ebstudytemplates3week.domain.comment.dto.CommentDto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommentServiceTest {

  @Autowired
  CommentService commentService;
  @Autowired
  BoardService boardService;

  CommentDto initCommentDto;
  BoardWriteDto initBoardWriteDto;
  Long lastBoardId; // 테스트에 들어가는 board id 값

  @BeforeEach
  void init() {

    lastBoardId = (long) boardService.getTotalBoardCnt() + 1L;

    initBoardWriteDto = BoardWriteDto.builder()
        .categoryId(1L)
        .title("title")
        .content("content")
        .user("user")
        .password("pass123*")
        .passwordConfirm("pass123*")
        .createdTime(LocalDateTime.parse("2000/01/01 00:00:00",
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
        .updatedTime(LocalDateTime.parse("2000/01/01 00:00:00",
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
        .build();

    initCommentDto = CommentDto.builder()
        .boardId(1L)
        .content("내용")
        .createdTime(LocalDateTime.parse("2000/01/01 00:00:00",
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
        .build();
  }

}