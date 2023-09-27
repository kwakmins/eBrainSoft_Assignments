package com.ebsoft.ebstudytemplates3week.domain.board.api;

import com.ebsoft.ebstudytemplates3week.domain.board.application.BoardService;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.BoardWriteDto;
import com.ebsoft.ebstudytemplates3week.domain.category.application.CategoryService;
import java.time.LocalDateTime;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/free")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

  private final CategoryService categoryService;
  private final BoardService boardService;

  /*
  게시판을 작성할 때, 랜더링.
   */
  @GetMapping("/write")
  public String writeForm(Model model) {
    model.addAttribute("boardWriteDto", new BoardWriteDto()); //th:object 사용
    model.addAttribute("AllCategories", categoryService.getAllCategory());
    return "form/boardWriteForm";
  }

  /*
  작성 폼으로부터, 게시판을 작성한다.
   */
  @PostMapping("/write")
  public String writeBoard(@Valid @ModelAttribute BoardWriteDto reqDto) {
    reqDto.setCreatedTime(LocalDateTime.now());
    reqDto.setUpdatedTime(LocalDateTime.now());

    // 비밀번호 확인이 틀린 경우.
    if (!reqDto.getPassword().equals(reqDto.getPasswordConfirm())) {
      throw new IllegalArgumentException();
      // 프론트단에서 이미 제약조건을 걸었는데,
      // 다른 방식으로 억지로 값을 넣은 대상에게, 친절하게 bindResult로 담아서 줄 필요가 있을까?
    }
    // log.info(reqDto.toString());
    boardService.addBoard(reqDto);
    return "form/boardWriteForm";
  }
}
