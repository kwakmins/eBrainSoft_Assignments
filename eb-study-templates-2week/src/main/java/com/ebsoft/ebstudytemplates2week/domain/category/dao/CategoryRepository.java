package com.ebsoft.ebstudytemplates2week.domain.category.dao;

import com.ebsoft.ebstudytemplates2week.domain.category.entity.Category;
import java.util.List;

public interface CategoryRepository {

  /**
   * @return 카테고리 DB의 모든 이름
   */
  public List<String> CategoryAllNames();

  /**
   * @param name
   * @return 카테고리 ID
   */
  public Long findByName(String name);

  /**
   * @return 모든 카테고리
   */
  public List<Category> findAll();
}
