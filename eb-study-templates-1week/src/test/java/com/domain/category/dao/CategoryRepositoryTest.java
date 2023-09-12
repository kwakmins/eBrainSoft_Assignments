package com.domain.category.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;


public class CategoryRepositoryTest {

  @Test
  @DisplayName("모든 카테고리들을 가져와야 한다")
  public void should_getAllName_when_getAllCategoryName() throws ClassNotFoundException {
    CategoryRepository dao = new CategoryRepository();
    Map<Long, String> allCategory = dao.getAllCategory();
    assertThat(allCategory.size()).isEqualTo(3);
  }
}