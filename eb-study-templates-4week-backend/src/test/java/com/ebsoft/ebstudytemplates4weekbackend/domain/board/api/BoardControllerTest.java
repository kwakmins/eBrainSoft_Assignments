package com.ebsoft.ebstudytemplates4weekbackend.domain.board.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ebsoft.ebstudytemplates4weekbackend.AbstractRestDocsTests;
import com.ebsoft.ebstudytemplates4weekbackend.domain.board.application.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockMultipartFile;

@WebMvcTest(BoardController.class)
@MockBean(JpaMetamodelMappingContext.class) //auditing
class BoardControllerTest extends AbstractRestDocsTests {

  @Autowired
  private BoardController boardController;

  @MockBean
  private BoardService boardService;

  @Nested
  @DisplayName("게시판 생성 관련 컨트롤러 테스트")
  class createBoard {

    @Test
    @DisplayName("게시판을 정상적으로 생성하면, 201 상태코드와 location을 줘야한다")
    void 게시판을_정상적으로_생성하면_201_상태코드와_location을_줘야한다() throws Exception {
      String requestJson = "{\"categoryId\" :2 ,\"userName\" : \"asd\" ,\"password\" : \"test123*\" ,\"passwordConfirm\" :\"test123*\" ,\"title\" :\"titi\" ,\"content\" :\"asdfasdf\" }\n";

      given(boardService.createBoard(any(), any())).willReturn(1L);

      MockMultipartFile request = new MockMultipartFile("request", "request.json",
          "application/json", requestJson.getBytes());

      mockMvc.perform(
              multipart("/api/v1/board/write")
                  .file(request))
          .andExpect(status().isCreated())
          .andExpect(header().string(HttpHeaders.LOCATION, "/board/1"));
    }

    @Test
    @DisplayName("게시판을 생성할 때, 올바르지 않은 데이터면, 400 예외가 발생한다.")
    void 게시판을_생성할_때_올바르_않은_데이터면_400_예외가_발생한다() throws Exception {
      // 작성자 이름이 한자리인 데이터.
      String requestJson = "{\"categoryId\" :2 ,\"userName\" : \"1\" ,\"password\" : \"test123*\" ,\"passwordConfirm\" :\"test123*\" ,\"title\" :\"titi\" ,\"content\" :\"asdfasdf\" }\n";

      given(boardService.createBoard(any(), any())).willReturn(1L);

      MockMultipartFile request = new MockMultipartFile("request", "request.json",
          "application/json", requestJson.getBytes());

      mockMvc.perform(
              multipart("/api/v1/board/write")
                  .file(request))
          .andExpect(status().isBadRequest());
    }
  }
}