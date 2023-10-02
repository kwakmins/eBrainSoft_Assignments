package com.ebsoft.ebstudytemplates3week.domain.board.dto.request;

import lombok.Data;

@Data
public class BoardPasswordConfirmDto {

  // 게시판 아이디
  public Long BoardId;
  // 비밀번호
  public String password;
}
