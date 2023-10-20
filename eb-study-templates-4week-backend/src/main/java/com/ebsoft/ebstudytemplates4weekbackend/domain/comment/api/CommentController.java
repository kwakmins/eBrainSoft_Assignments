package com.ebsoft.ebstudytemplates4weekbackend.domain.comment.api;

import com.ebsoft.ebstudytemplates4weekbackend.domain.comment.application.CommentService;
import com.ebsoft.ebstudytemplates4weekbackend.domain.comment.dto.request.CommentCreateReqDto;
import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {

  private final CommentService commentService;

  /**
   * 댓글 생성
   *
   * @param boardId 관련 게시판
   * @param request 생성 내용
   * @return 201, 관련 게시판 URL location
   */
  @PostMapping("/{boardId}")
  public ResponseEntity<Void> createComment(
      @PathVariable Long boardId,
      @RequestBody @Valid CommentCreateReqDto request
  ) {
    Long createdCommentId = commentService.createComment(boardId, request);

    return ResponseEntity.status(HttpStatus.CREATED)
        .location(URI.create("/board/" + createdCommentId))
        .build();
  }
}
