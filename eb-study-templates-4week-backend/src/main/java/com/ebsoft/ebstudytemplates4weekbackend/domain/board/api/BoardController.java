package com.ebsoft.ebstudytemplates4weekbackend.domain.board.api;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.application.BoardService;
import com.ebsoft.ebstudytemplates4weekbackend.domain.board.dto.request.BoardWriteReqDto;
import com.ebsoft.ebstudytemplates4weekbackend.domain.board.dto.response.BoardDetailResDto;
import com.ebsoft.ebstudytemplates4weekbackend.domain.board.dto.response.BoardListResDto;
import com.ebsoft.ebstudytemplates4weekbackend.domain.board.dto.response.BoardWriteFormResDto;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
public class BoardController {

  private final BoardService boardService;

  /**
   * 게시판 작성시 필요한 정보 전송
   *
   * @return 200, 게시판 작성시 필요한 정보
   *
   * TODO 게시판 작성시 필요한 정보 : 모든 카테고리.
   * TODO 모든 카테고리를 주는 2가지 방식 비교.
   *
   * TODO 1. 게시판 관련 컨트롤러에서, 작성시 필요한 정보를  줄 때, 카테고리도 같이 주기.(채택)
   * TODO 장점 : 추후 다른 필요한 정보를 쉽게 담아서 줄 수 있다.
   * TODO 단점 : 카테고리만 담아서 주기에, 카테고리 관련 도메인에 가깝다.
   *
   * TODO 2. 카테고리 관련 컨트롤러에서 관리.
   * TODO 장점 : 명확하게 역할에 맞는 책임을 맡게 아키텍쳐 구성 가능.
   * TODO 단점 : 추후 다른 필요한 정보를 줄 때, 어려움.
   */
  @GetMapping("/write")
  public ResponseEntity<BoardWriteFormResDto> getWriteBoardForm() {
    BoardWriteFormResDto response = boardService.getBoardWriteForm();

    return ResponseEntity.status(HttpStatus.OK)
        .body(response);
  }

  /**
   * 게시판 생성
   *
   * @param request     게시판 작성한 ReqDto
   * @param uploadFiles 게시판에 등록할 파일들
   * @return 201, `/board/{생성된 게시판 id}` location
   */
  @PostMapping("/write")
  public ResponseEntity<Void> createBoard(
      @RequestPart @Valid BoardWriteReqDto request,
      @RequestPart(required = false) List<MultipartFile> uploadFiles
  ) {

    Long createdBoardId = boardService.createBoard(request, uploadFiles);

    return ResponseEntity.ok()
        .location(URI.create("/board/" + createdBoardId))
        .build();
  }

  /**
   * 게시판 상세보기
   *
   * @param boardId 게시판 id
   * @return 200, 상세보기 resDto
   */
  @GetMapping("/{boardId}")
  public ResponseEntity<BoardDetailResDto> readBoard(
      @PathVariable Long boardId
  ) {
    BoardDetailResDto response = boardService.getBoardDetail(boardId);

    return ResponseEntity.status(HttpStatus.OK)
        .body(response);
  }

  /**
   * 게시판 목록 조회
   *
   * @param pageable   페이지 정보
   * @param categoryId 검색 카테고리 / null은 모두 검색
   * @param search     검색명
   * @return 200, 게시판 목록 rseDto
   */
  @GetMapping("")
  public ResponseEntity<BoardListResDto> readBoards(
      @PageableDefault(page = 0, size = 10) Pageable pageable,
      @RequestParam(required = false) Long categoryId,
      @RequestParam(defaultValue = "") String search
  ) {
    //TODO categoryId가 NUll인 경우.
    // 1. 컨트롤러에서 불러오는 메서드 변경(서비스에서 2메서드 사용) vs 2. 서비스에서 한 메서드로 처리 (채택)
    // 1. 컨트롤러에서 로직이 발생.
    // 2. null 값을 메서드로 넘기는 것이 찝찝함.
    BoardListResDto response = boardService.getBoards(pageable, categoryId, search);

    return ResponseEntity.status(HttpStatus.OK)
        .body(response);
  }

  /**
   * 비밀번호 확인 핸들러. 틀리면 오류 Http Status
   *
   * @param boardId  게시판 id
   * @param password 비밀번호
   * @return 200, 틀리면 404
   */
  @GetMapping("/{boardId}/passwordCheck")
  public ResponseEntity<Void> passwordCheck(
      @PathVariable Long boardId,
      @RequestParam String password
  ) {
    boardService.checkPassword(boardId, password);

    return ResponseEntity.ok().build();
  }
}
