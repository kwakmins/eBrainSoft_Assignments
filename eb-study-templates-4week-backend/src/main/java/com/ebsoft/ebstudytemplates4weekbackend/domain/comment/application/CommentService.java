package com.ebsoft.ebstudytemplates4weekbackend.domain.comment.application;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.dao.BoardRepository;
import com.ebsoft.ebstudytemplates4weekbackend.domain.board.entity.Board;
import com.ebsoft.ebstudytemplates4weekbackend.domain.comment.dao.CommentRepository;
import com.ebsoft.ebstudytemplates4weekbackend.domain.comment.dto.request.CommentCreateReqDto;
import com.ebsoft.ebstudytemplates4weekbackend.domain.comment.entity.Comment;
import com.ebsoft.ebstudytemplates4weekbackend.global.error.BusinessException;
import com.ebsoft.ebstudytemplates4weekbackend.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final BoardRepository boardRepository;

  /**
   * 댓글 생성
   *
   * @param boardId 관련 게시판
   * @param request 생성 내용
   * @return 생성된 댓글 id
   */
  @Transactional
  public Long createComment(Long boardId, CommentCreateReqDto request) {
    Comment comment = request.toEntity(getBoardById(boardId));

    return commentRepository.save(comment).getId();
  }

  /**
   * 게시판 id로 게시판 찾기
   *
   * @param boardId 게시판 id
   */
  private Board getBoardById(Long boardId) {
    return boardRepository.findById(boardId).orElseThrow(
        () -> new BusinessException(boardId, "boardId", ErrorCode.BOARD_NOT_FOUND));
  }
}
