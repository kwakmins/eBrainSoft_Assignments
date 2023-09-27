package com.ebsoft.ebstudytemplates3week.domain.board.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.ebsoft.ebstudytemplates3week.domain.board.dao.BoardRepository;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.BoardDto;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.BoardWriteDto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
class BoardServiceTest {

  @Autowired
  private BoardService boardService;
  @Autowired
  private BoardRepository boardRepository;

  private BoardWriteDto globalBoardWriteDto;

  /*
  제약 조건에 맞는 올바른 데이터 주입
   */
  @BeforeEach
  void setUp() {
    globalBoardWriteDto = BoardWriteDto.builder()
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
  }

  @Test
  @DisplayName("게시판이 정상적으로 저장이 되어야함.")
  @Transactional
    // 자동 증분 이유 모르겠음.
  void should_save_when_writeBoard() {
    Long prevBoardCnt = boardRepository.totalBoardCnt(); // 추가 되기 전 게시판 수
    boardService.addBoard(globalBoardWriteDto); // 추가
    Long afterBoardCnt = boardRepository.totalBoardCnt(); // 추가 된 후 게시판 수
    assertThat(prevBoardCnt + 1L).isEqualTo(afterBoardCnt); // 추가 되기 전 + 1 = 추가 된 후
  }

  @Test
  @DisplayName("게시판이 정상적으로 불러와지고, 조회수가 증가해야함.")
  @Transactional
  void should_increaseViewCount_when_findBoardById() {
    boardService.addBoard(globalBoardWriteDto); // 추가
    Long boardId = boardRepository.lastBoardId();
    log.info(boardId.toString());
    BoardDto boardDto = boardService.getBoardById(boardId); // 조회수 +1

    assertThat(boardDto.getCreatedTime()).isEqualTo(
        globalBoardWriteDto.getCreatedTime()); // 불러 오기 테스트
    assertThat(boardDto.getViewCount()).isEqualTo(1); //조회수가 1이여야함
  }

  /**
   * 데이터 유효성 검사.
   * @ModelAttirbute or @RequestBody 등은 mockMVC 테스트로 해야함.
   */
//  @ParameterizedTest
//  @ValueSource(strings = {"", "1", "2자", "3자리",
//      "111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111"})
//  @DisplayName("제목이 요구사항이랑 다를 때, 게시판이 저장되면 안됨.")
//  @Transactional
//  void should_dontSave_when_titleWrongData(String param) {
//    Long prevBoardCnt = boardRepository.totalBoardCnt(); // 추가 되기 전 게시판 수
//    BoardWriteDto boardWriteDto1 = BoardWriteDto.builder()
//        .categoryId(boardWriteDto.getCategoryId())
//        .title(param)
//        .content(boardWriteDto.getContent())
//        .createdTime(boardWriteDto.getCreatedTime())
//        .password(boardWriteDto.getPassword())
//        .passwordConfirm(boardWriteDto.getPasswordConfirm())
//        .updatedTime(boardWriteDto.getCreatedTime())
//        .user(boardWriteDto.getUser())
//        .build();
//    boardService.addBoard(boardWriteDto1); // 추가
//    Long afterBoardCnt = boardRepository.totalBoardCnt(); // 추가 된 후 게시판 수
//    Assertions.assertThat(prevBoardCnt).isEqualTo(afterBoardCnt); //추가 되기 전 = 추가 된 후
//  }
}