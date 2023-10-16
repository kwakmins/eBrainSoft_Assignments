package com.ebsoft.ebstudytemplates4weekbackend.global.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 포맷팅된 에러 메시지 담을 Response DTO
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

  private final String message;

  public static ErrorResponse from(String message) {
    return new ErrorResponse(message);
  }
}
