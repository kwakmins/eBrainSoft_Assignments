package com.ebsoft.ebstudytemplates3week.domain.board.dto.request;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardWriteDto {

  // 카테고리
  @NotNull
  @Positive
  private Long categoryId;
  // 글쓴이
  @NotBlank
  @Length(min = 3, max = 5)
  private String user;
  // 비밀번호
  @NotBlank
  @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{4,16}$")
  private String password;
  // 비밀번호 확인
  @NotBlank
  private String passwordConfirm;
  // 제목
  @NotBlank
  @Length(min = 4, max = 99)
  private String title;
  // 내용
  @NotBlank
  @Length(min = 4, max = 2000)
  private String content;
  // 작성 시간
  private LocalDateTime createdTime;
  // 업데이트 시간
  private LocalDateTime updatedTime;
}
