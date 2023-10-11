package com.ebsoft.ebstudytemplates4weekbackend.domain.board.api;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.application.BoardService;
import com.ebsoft.ebstudytemplates4weekbackend.domain.category.dto.response.CategoryResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/free")
public class BoardController {

  private final BoardService boardService;

  /**
   * 게시판 작성시 필요한 정보 전송
   */
  @GetMapping("/write")
  public HttpEntity<List<CategoryResponse>> createBoard() {
    
  }
}
