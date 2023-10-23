package com.ebsoft.ebstudytemplates4weekbackend.domain.category;

import com.ebsoft.ebstudytemplates4weekbackend.domain.category.entity.Category;

/**
 * 카테고리 테스트 헬퍼
 */
public class CategoryTestHelper {

  public static Category createCategory() {
    return Category.builder()
        .name("카테고리명")
        .build();
  }

  public static Category createCategoryWithId() {
    return Category.builder()
        .id(1L)
        .name("카테고리명")
        .build();
  }


}
