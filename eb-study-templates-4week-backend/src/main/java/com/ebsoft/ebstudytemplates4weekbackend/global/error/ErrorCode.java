package com.ebsoft.ebstudytemplates4weekbackend.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 오류 메시지와 상태를 쉽게 추가하기 위한 Enum
 */
@Getter
public enum ErrorCode {
  //카테고리
  CATEGORY_NOT_FOUND("해당 카테고리를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

  //게시판
  BOARD_WRONG_PASSWORD_CONFIRM("비밀번호가 서로 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
  FILE_MAX_SIZE("파일은 최대 3개까지만 저장이 가능합니다.", HttpStatus.BAD_REQUEST),
  BOARD_NOT_FOUND("해당 파일을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

  //파일
  FILE_IO_EXCEPTION("파일에 관한 알 수 없는 오류가 발생하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
  ;

  //오류 메시지
  private final String message;
  //오류 상태코드
  private final HttpStatus httpStatus;

  ErrorCode(String message, HttpStatus httpStatus) {
    this.message = message;
    this.httpStatus = httpStatus;
  }
}
