package com.ebsoft.ebstudytemplates3week.domain.board.api;

import com.ebsoft.ebstudytemplates3week.domain.board.application.BoardService;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.BoardDto;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.BoardWriteDto;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.response.BoardListDto;
import com.ebsoft.ebstudytemplates3week.domain.category.application.CategoryService;
import com.ebsoft.ebstudytemplates3week.domain.comment.application.CommentService;
import com.ebsoft.ebstudytemplates3week.domain.comment.dto.CommentDto;
import com.ebsoft.ebstudytemplates3week.global.paging.Pagination;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board/free")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

  private final CategoryService categoryService;
  private final BoardService boardService;
  private final CommentService commentService;

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
  public String writeBoard(@Valid @ModelAttribute BoardWriteDto reqDto,
      RedirectAttributes redirectAttributes) {
    reqDto.setCreatedTime(LocalDateTime.now());
    reqDto.setUpdatedTime(LocalDateTime.now());

    // 비밀번호 확인이 틀린 경우.
    if (!reqDto.getPassword().equals(reqDto.getPasswordConfirm())) {
      throw new IllegalArgumentException();
      // todo 프론트단에서 이미 제약조건을 걸었는데,
      // todo 다른 방식으로 억지로 값을 넣은 대상에게, 친절하게 bindResult로 담아서 줄 필요가 있을까?
    }
    // log.info(reqDto.toString());
    boardService.addBoard(reqDto);

    //todo id 자동 증분으로 인해, dto에 id가 없어, 마지막에 넣은 값을 얻어, 계산.
    //todo 그 외 테스트 등에서도 다음과 같이 해야함.
    Long lastWriteBoardId = boardService.getLastWriteBoardId();
    redirectAttributes.addAttribute("boardId", lastWriteBoardId);
    return "redirect:/board/free/view/{boardId}";
  }

  /*
  게시판 조회
   */
  @GetMapping("/view/{id}")
  public String viewBoard(@PathVariable("id") Long boardId, Model model) {
    BoardDto board = boardService.getBoardById(boardId);
    log.info(boardId.toString());
    log.info(board.toString());
    model.addAttribute("board", board);
    return "form/boardForm";
  }

  /*
  게시판 목록
   */
  @GetMapping("/list")
  public String viewBoardList(Model model,
      @RequestParam(defaultValue = "1") int page) {
    Pagination pagination = new Pagination(boardService.getTotalBoardCnt(), page);
    List<BoardListDto> boardList = boardService.getBoardList(pagination);
    model.addAttribute("boardList", boardList); // 게시판들
    model.addAttribute("page", page); //현재 페이지
    model.addAttribute("pageVo", pagination); // 페이지에 관한 정보
    model.addAttribute("AllCategories", categoryService.getAllCategory()); //카테고리들
    return "form/boardListForm";
  }

  /*
  댓글 작성
  todo 배치: 게시판 로직이랑 분단 vs 같은 url을 사용하는 핸들러 바로 밑 배치
   */
  @PostMapping("/view/{id}")
  public String writeComment(@PathVariable("id") Long boardId,
      @Valid @ModelAttribute CommentDto reqDto) {
    reqDto.setBoardId(boardId); // 1:N 관계 , /view/{id} 의 id로 값을 받을 수 있음.
    reqDto.setCreatedTime(LocalDateTime.now());
    commentService.addComment(reqDto); // 댓글 추가
    return "redirect:/board/free/view/" + boardId;
  }
}
