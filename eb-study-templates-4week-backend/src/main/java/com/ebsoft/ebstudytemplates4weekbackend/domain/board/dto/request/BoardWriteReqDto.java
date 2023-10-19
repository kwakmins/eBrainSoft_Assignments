package com.ebsoft.ebstudytemplates4weekbackend.domain.board.dto.request;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.entity.Board;
import com.ebsoft.ebstudytemplates4weekbackend.domain.category.entity.Category;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * 게시판 작성 정보 받는 ReqDto
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardWriteReqDto {

  public static final int DEFAULT_VIEW_COUNT = 0;
  public static final int MIN_USER_NAME_LENGTH = 3;
  public static final int MAX_USER_NAME_LENGTH = 5;
  public static final int MIN_TITLE_LENGTH = 4;
  public static final int MAX_TITLE_LENGTH = 99;
  public static final int MIN_CONTENT_LENGTH = 4;
  public static final int MAX_CONTENT_LENGTH = 2000;

  // 카테고리
  @NotNull(message = "카테고리 아이디를 입력해주세요.")
  @Positive(message = "올바른 카테고리 아이디를 입력해주세요.")
  private Long categoryId;

  // 작성자 이름
  @NotBlank(message = "작성자 이름을 입력해주세요.")
  @Length(min = MIN_USER_NAME_LENGTH, max = MAX_USER_NAME_LENGTH
      , message = "작성자 이름은 {min}자 이상 {max}자 이하로 입력해주세요.")
  private String userName;

  // 비밀번호
  @NotBlank(message = "비밀번호를 입력해주세요.")
  @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{4,16}$"
      , message = "비밀번호는 특수문자를 포함한 4~16자리 수 여야만 합니다.")
  private String password;

  // 비밀번호 확인
  @NotBlank(message = "비밀번호 확인을 입력해주세요.")
  private String passwordConfirm;

  // 제목
  @NotBlank(message = "제목을 입력해주세요.")
  @Length(min = MIN_TITLE_LENGTH, max = MAX_TITLE_LENGTH
      , message = "제목은 {min}자 이상 {max}자 이하로 입력해주세요.")
  private String title;

  // 내용
  @NotBlank(message = "내용을 입력해주세요.")
  @Length(min = MIN_CONTENT_LENGTH, max = MAX_CONTENT_LENGTH
      , message = "내용은 {min}자 이상 {max}자 이하로 입력해주세요.")
  private String content;

  /**
   * Entity로 변환
   *
   * @param category        카테고리
   * @param encodedPassword 인코딩된 비밀번호
   * @return 게시판 Entity
   */
  public Board toEntity(Category category, String encodedPassword) {
    return Board.builder()
        .category(category)
        .password(encodedPassword)
        .title(this.title)
        .userName(this.userName)
        .content(this.content)
        .viewCount(DEFAULT_VIEW_COUNT)
        .build();
  }

  /**
   * 비밀번호 확인이 올바른지 확인
   *
   * @return 비밀번호 확인 여부
   */
  public boolean isRightPasswordConfirm() {
    return password.equals(passwordConfirm);
  }

}
