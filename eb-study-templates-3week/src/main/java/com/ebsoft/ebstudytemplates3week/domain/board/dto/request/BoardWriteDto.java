package com.ebsoft.ebstudytemplates3week.domain.board.dto.request;

import com.ebsoft.ebstudytemplates3week.domain.category.dto.CategoryDto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardWriteDto {

  // 카테고리
  private Long categoryId;
  // 글쓴이
  private String user;
  // 비밀번호
  private String password;
  // 비밀번호 확인
  private String passwordConfirm;
  // 제목
  private String title;
  // 내용
  private String content;
  // 작성 시간
  private LocalDateTime createdTime;
  // 업데이트 시간
  private LocalDateTime updatedTime;
}
