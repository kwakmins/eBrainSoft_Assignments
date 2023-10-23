package com.ebsoft.ebstudytemplates4weekbackend.domain.board.dao;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.BoardTestHelper;
import com.ebsoft.ebstudytemplates4weekbackend.domain.board.entity.Board;
import com.ebsoft.ebstudytemplates4weekbackend.domain.category.CategoryTestHelper;
import com.ebsoft.ebstudytemplates4weekbackend.domain.category.dao.CategoryRepository;
import com.ebsoft.ebstudytemplates4weekbackend.domain.category.entity.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class BoardRepositoryTest {

  @Autowired
  private BoardRepository boardRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Nested
  @DisplayName("게시판 저장 관련 DB 테스트")
  class BoardSaveTest {

    @Test
    @DisplayName("게시판이 정상적으로 저장됨")
    void 게시판이_정상적으로_저장됨() {
      Category category = categoryRepository.save(CategoryTestHelper.createCategory());
      Board board = BoardTestHelper.createBoard(category);

      Assertions.assertThat(boardRepository.save(board).getId()).isEqualTo(1L);
    }
  }
}