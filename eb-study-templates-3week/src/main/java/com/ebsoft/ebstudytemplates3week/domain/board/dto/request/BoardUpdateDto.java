package com.ebsoft.ebstudytemplates3week.domain.board.dto.request;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BoardUpdateDto {

  // 게시판 아이디
  @NotNull
  public Long boardId;
  // 글쓴이
  @NotBlank
  @Length(min = 3, max = 5)
  private String user;
  // 비밀번호
  @NotBlank
  @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{4,16}$")
  private String password;
  // 제목
  @NotBlank
  @Length(min = 4, max = 99)
  private String title;
  // 내용
  @NotBlank
  @Length(min = 4, max = 2000)
  private String content;
  // 업데이트 시간
  private LocalDateTime updatedTime;
  // 파일 리스트
  private List<MultipartFile> files;
  // 삭제할 파일 리스트
  private List<Long> deleteFiles;
}
