package com.ebsoft.ebstudytemplates4weekbackend.domain.comment.dto.request;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.entity.Board;
import com.ebsoft.ebstudytemplates4weekbackend.domain.comment.entity.Comment;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

/**
 * 댓글 작성 시 요구 ReqDto
 */
@Getter
public class CommentCreateReqDto {

  //댓글 내용
  @NotBlank(message = "댓글 내용을 입력해주세요.")
  @Length(max = 251, message = "250자 이하로 입력해주세요.")
  private String content;

  public Comment toEntity(Board board) {
    return Comment.builder()
        .board(board)
        .content(this.content)
        .build();
  }
}
