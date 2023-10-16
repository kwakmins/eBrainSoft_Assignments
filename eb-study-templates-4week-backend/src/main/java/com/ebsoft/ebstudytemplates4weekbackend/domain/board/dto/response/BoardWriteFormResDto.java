package com.ebsoft.ebstudytemplates4weekbackend.domain.board.dto.response;

import com.ebsoft.ebstudytemplates4weekbackend.domain.category.dto.response.CategoryResDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardWriteFormResDto {

  private List<CategoryResDto> categories;

  public static BoardWriteFormResDto form(List<CategoryResDto> categories) {
    return new BoardWriteFormResDto(categories);
  }
}
