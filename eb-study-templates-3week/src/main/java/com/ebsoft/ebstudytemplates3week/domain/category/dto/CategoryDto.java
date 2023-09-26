package com.ebsoft.ebstudytemplates3week.domain.category.dto;

import lombok.Data;

/**
 * 카테고리의 모든 요소가 반환되는 DTO
 */
@Data
public class CategoryDto {

  //카테고리 아이디
  public Long categoryId;
  //카테고리 이름
  public String categoryName;
}
