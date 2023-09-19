package com.ebsoft.ebstudytemplates2week.domain.category.application;

import com.ebsoft.ebstudytemplates2week.domain.category.dao.CategoryRepository;
import com.ebsoft.ebstudytemplates2week.global.mybatis.MybatisConfig;
import java.util.List;
import java.util.Objects;
import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;

@NoArgsConstructor
public class CategoryService {

  CategoryRepository categoryRepository;

  /**
   * @return 모든 카테고리 이름
   */
  public List<String> getAllCategoryNames() {
    List<String> list;
    SqlSession sqlSession = null;
    try {
      sqlSession = MybatisConfig.getSqlSession();
      categoryRepository = sqlSession.getMapper(CategoryRepository.class);
      list = categoryRepository.CategoryAllNames();
    } finally {
      Objects.requireNonNull(sqlSession).close(); // 피드백 반영 : 커넥션은 닫아준다.
    }
    return list;
  }

  /**
   * @param name
   * @return 카테고리 ID
   */
  public Long getCategoryIdByName(String name) {
    Long id;
    SqlSession sqlSession = null;
    try {
      sqlSession = MybatisConfig.getSqlSession();
      categoryRepository = sqlSession.getMapper(CategoryRepository.class);
      id = categoryRepository.findByName(name);
    } finally {
      Objects.requireNonNull(sqlSession).close();
    }
    return id;
  }

}

