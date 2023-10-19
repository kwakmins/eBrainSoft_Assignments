package com.ebsoft.ebstudytemplates4weekbackend.domain.board.dto.response;

import com.ebsoft.ebstudytemplates4weekbackend.domain.category.dto.response.CategoryResDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 게시판 작성폼에 필요한 정보 ResDto
 */
@Getter
@AllArgsConstructor
public class BoardWriteFormResDto {

  private List<CategoryResDto> categories;

  public static BoardWriteFormResDto form(List<CategoryResDto> categories) {
    return new BoardWriteFormResDto(categories);
  }
}
