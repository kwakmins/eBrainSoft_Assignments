package com.ebsoft.ebstudytemplates4weekbackend.domain.board.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.BoardTestHelper;
import com.ebsoft.ebstudytemplates4weekbackend.domain.board.dao.BoardRepository;
import com.ebsoft.ebstudytemplates4weekbackend.domain.board.dto.request.BoardWriteReqDto;
import com.ebsoft.ebstudytemplates4weekbackend.domain.board.entity.Board;
import com.ebsoft.ebstudytemplates4weekbackend.domain.category.CategoryTestHelper;
import com.ebsoft.ebstudytemplates4weekbackend.domain.category.dao.CategoryRepository;
import com.ebsoft.ebstudytemplates4weekbackend.domain.category.entity.Category;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

  @InjectMocks
  private BoardService boardService;

  @Mock
  private BoardRepository boardRepository;
  @Mock
  private CategoryRepository categoryRepository;
  @Mock
  private PasswordEncoder passwordEncoder;

  @Nested
  @DisplayName("게시판 생성 관련 서비스 테스트")
  class CreateBoard {

    @Test
    @DisplayName("게시판이 정상적으로 생성 되어, 생성된 Id를 반환한다.")
    void 게시판이_정상적으로_생성_되어_생성된_Id를_반환한다() {
      BoardWriteReqDto request = BoardWriteReqDto.builder()
          .title("제목")
          .categoryId(1L)
          .content("내용")
          .userName("유저명")
          .password("test123*")
          .passwordConfirm("test123*")
          .build();

      Category category = CategoryTestHelper.createCategoryWithId();
      Board board = BoardTestHelper.createBoardWithId(category);

      given(categoryRepository.findById(any())).willReturn(Optional.ofNullable(category));
      given(passwordEncoder.encode(any())).willReturn("test123*");
      given(boardRepository.save(any())).willReturn(board);

      Assertions.assertThat(boardService.createBoard(request, null))
          .isEqualTo(board.getId());
    }
  }

}