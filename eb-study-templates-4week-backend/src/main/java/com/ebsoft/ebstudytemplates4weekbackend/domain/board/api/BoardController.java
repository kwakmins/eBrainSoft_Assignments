package com.ebsoft.ebstudytemplates4weekbackend.domain.board.api;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.application.BoardService;
import com.ebsoft.ebstudytemplates4weekbackend.domain.board.dto.request.BoardWriteReqDto;
import com.ebsoft.ebstudytemplates4weekbackend.domain.board.dto.response.BoardWriteFormResDto;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
   * @param reqDto         게시판 작성한 ReqDto
   * @param multipartFiles 게시판에 등록된 파일들
   * @return 201, `/board/{생성된 게시판 id}` location
   */
  @PostMapping("/write")
  public ResponseEntity<Void> createBoard(
      @RequestBody @Valid BoardWriteReqDto reqDto,
      @RequestPart(required = false) List<MultipartFile> multipartFiles
  ) {

    Long createdBoardId = boardService.createBoard(reqDto, multipartFiles);

    return ResponseEntity.ok()
        .location(URI.create("/board/" + createdBoardId))
        .build();
  }
}
