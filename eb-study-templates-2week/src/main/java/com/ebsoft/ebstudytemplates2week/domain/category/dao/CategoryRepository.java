package com.ebsoft.ebstudytemplates2week.domain.category.dao;

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
}
