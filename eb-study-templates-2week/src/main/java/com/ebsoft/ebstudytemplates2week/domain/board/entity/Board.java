package com.ebsoft.ebstudytemplates2week.domain.board.entity;

import java.time.LocalDateTime;
import java.util.regex.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@ToString
@Slf4j
public class Board {

  private Long boardId;
  private Long categoryId;
  private String user;
  private String password;
  private String title;
  private String content;
  private Integer viewCount;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  /**
   * 서버측 검증
   * @return T or F
   */
  public boolean valid() {

    if (user.length() < 3 || user.length() >= 5) {
      log.error("입력된 유저의 이름의 길이가 3보다 작거나 5보다 큽니다");
      return false;
    }

    if (!Pattern.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{4,16}$",
        password)) {
      log.error("입력된 비밀번호가 올바르지 않습니다.");
      return false;
    }
    if (title.length() < 4 || title.length() >= 100) {
      log.error("입력된 제목의 길이가 4미만 또는 100이상 입니다.");
      return false;
    }
    if (content.length() < 4 || content.length() >= 2000) {
      log.error("입력된 내용의 길이가 4미만 또는 2000이상 입니다.");
      return false;
    }
    return true;
  }

}
