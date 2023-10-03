package com.ebsoft.ebstudytemplates3week.domain.board.api;

import com.ebsoft.ebstudytemplates3week.domain.board.application.BoardService;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.BoardDto;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.BoardPasswordConfirmDto;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.BoardUpdateDto;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.BoardWriteDto;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.SearchDto;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.response.BoardListDto;
import com.ebsoft.ebstudytemplates3week.domain.category.application.CategoryService;
import com.ebsoft.ebstudytemplates3week.domain.comment.application.CommentService;
import com.ebsoft.ebstudytemplates3week.domain.comment.dto.CommentDto;
import com.ebsoft.ebstudytemplates3week.global.paging.Pagination;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.bind.annotation.ResponseBody;
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

    //todo id 자동 증분으로 인해, dto에 id가 없어, 마지막에 넣은 값을 얻어, 계산. (!!!!히든으로 dto에 넣을 수 있다!!!)
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
    BoardDto board = boardService.getBoardByIdViewPlus(boardId);
    log.info("댓글 수 : " + String.valueOf(board.getComments().size()));
    model.addAttribute("board", board);
    return "form/boardForm";
  }

  /*
  게시판 목록
   */
  @GetMapping("/list")
  public String viewBoardList(Model model,
      @RequestParam(defaultValue = "1") int page,
      @ModelAttribute SearchDto searchDto) {

    log.info("검색어 : " + searchDto.toString());

    searchDto.setPagination(new Pagination(boardService.getTotalBoardCnt(searchDto), page));
    List<BoardListDto> boardList = boardService.getBoardList(searchDto);
    model.addAttribute("boardList", boardList); // 게시판들
    model.addAttribute("page", page); //현재 페이지
    model.addAttribute("pageVo", searchDto.pagination); // 페이지에 관한 정보
    model.addAttribute("AllCategories", categoryService.getAllCategory()); //카테고리들

    // 이전 검색 날짜 값 부여 및 default값 부여
    model.addAttribute("startDate",
        searchDto.startDate == null ?
            LocalDateTime.now().minusYears(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            : searchDto.startDate);

    model.addAttribute("endDate",
        searchDto.endDate == null ?
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            : searchDto.endDate);

    // 검색 조건 유지를 위한 이전 값 넣기
    model.addAttribute("prevCategory", searchDto.category); // 검색 카테고리
    model.addAttribute("prevContent", searchDto.searchContent); // 검색 내용

    // 페이징에서 이전 파라미터들 모두 받기
    model.addAttribute("prevSearchParam", searchDto.toUrlParm());
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

  /*
  비밀번호 확인 (업데이트)
   */
  @ResponseBody
  @GetMapping("/checkPwd")
  public boolean checkPasswordForUpdate(@ModelAttribute BoardPasswordConfirmDto reqDto) {
    boolean samePassword = boardService.isSamePassword(reqDto);
    log.info("수정을 위한 비밀번호 확인 성공 여부 : " + samePassword);
    return samePassword;
  }

  /*
  게시물 업데이트 랜더링
   */
  @GetMapping("/update/{boardId}")
  public String updateBoardForm(@PathVariable Long boardId, Model model) {
    log.info("업데이트 게시물 번호:" + boardId);
    model.addAttribute("board", boardService.getBoardById(boardId)); // 디폴트값 주기
    return "form/boardUpdateForm";
  }

  /*
  게시물 업데이트 액션
   */
  @PostMapping("/update/{boardId}")
  public String updateBoard(@PathVariable Long boardId,
      @Valid @ModelAttribute BoardUpdateDto reqDto) {
    reqDto.setUpdatedTime(LocalDateTime.now()); //업데이트 시간 변경
    log.info(reqDto.toString());
    boardService.updateBoard(reqDto);
    return "redirect:/board/free/view/" + boardId;
  }

  /*
  비밀번호 확인 후 삭제
 */
  @ResponseBody
  @PostMapping("/checkPwd")
  public boolean checkPasswordForDelete(@ModelAttribute BoardPasswordConfirmDto reqDto) {
    boolean samePassword = boardService.isSamePassword(reqDto);
    log.info("수정을 위한 비밀번호 확인 성공 여부 : " + samePassword);
    return samePassword;
  }

}
