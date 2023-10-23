package com.ebsoft.ebstudytemplates4weekbackend.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 로직에서 예외를 발생시킬 때 사용하는 언체크 예외
 */
@Getter
public class BusinessException extends RuntimeException {

  //오류 발생 부분의 값. 명확하게 없으면 Null.
  private final String invalidValue;
  //오류 필드명.
  private final String fieldName;
  //오류 상태 코드.
  private final HttpStatus httpStatus;
  //오류 메시지
  private final String message;

  public BusinessException(Object invalidValue, String fieldName, ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.invalidValue = invalidValue != null ? invalidValue.toString() : null;
    this.fieldName = fieldName;
    this.httpStatus = errorCode.getHttpStatus();
    this.message = errorCode.getMessage();
  }
}
