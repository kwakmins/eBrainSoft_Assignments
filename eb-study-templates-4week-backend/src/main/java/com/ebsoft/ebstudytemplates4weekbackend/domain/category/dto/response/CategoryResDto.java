package com.ebsoft.ebstudytemplates4weekbackend.domain.category.dto.response;

import com.ebsoft.ebstudytemplates4weekbackend.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CategoryResDto {

  // 카테고리 id
  private Long id;
  // 카테고리 이름
  private String name;

  /**
   * Entity로 Dto전환
   *
   * @param category 카테고리 Entity
   */
  public CategoryResDto(Category category) {
    this.id = category.getId();
    this.name = category.getName();
  }
}
