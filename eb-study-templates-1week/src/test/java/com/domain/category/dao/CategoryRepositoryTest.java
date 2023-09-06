package com.domain.category.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;


public class CategoryRepositoryTest {

  @Test
  @DisplayName("모든 카테고리 이름을 가져와야 한다")
  public void should_getAllName_when_getAllCategoryName() throws ClassNotFoundException {
    CategoryRepository dao = new CategoryRepository();
    List<String> list = dao.getAllCategoryName();
    assertThat(list.size()).isEqualTo(3);
  }
}