package com.domain.board.dao;

import com.domain.board.entity.Board;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class BoardRepositoryTest {

  @Test
  @DisplayName("게시판을 만들기는, 정상적인 데이터일 때, true를 반환해야한다.")
  //추후 delete가 구현되면, 테스트 데이터 없애기
  public void CreateBoard_should_ReturnTrue_when_trueData() {
    BoardRepository boardRepository = new BoardRepository();
    Board board = new Board(boardRepository.getNextId(), 1L, "user", "asd!12", "테스트12", "테스트12",
        121,
        LocalDateTime.now(), LocalDateTime.now());

    Assertions.assertThat(boardRepository.createBoard(board)).isTrue();
  }

  @Test
  @DisplayName("게시판을 만들기는, 작성자가 3글자 미만 일 때, flase를 반환해야한다.")
  //추후 이 외에도 작성자가 5글자 이상 비밀번호 , 제목, 내용 등 제약 조건에 맞는 테스트 추가
  public void CreateBoard_should_returnFalse_when_userFalseData() {
    BoardRepository boardRepository = new BoardRepository();
    Board board = new Board(boardRepository.getNextId(), 1L, "u", "asd!12", "테스트12", "테스트12",
        121,
        LocalDateTime.now(), LocalDateTime.now());

    Assertions.assertThat(boardRepository.createBoard(board)).isFalse();
  }

}