package com.ebsoft.ebstudytemplates4weekbackend.domain.category.application;

import com.ebsoft.ebstudytemplates4weekbackend.domain.category.dao.CategoryRepository;
import com.ebsoft.ebstudytemplates4weekbackend.domain.category.entity.Category;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

  private final CategoryRepository categoryRepository;

  /**
   * 카테고리 모두 저장
   */
  @Transactional
  public void saveCategories(List<Category> categories) {
    categoryRepository.saveAll(categories);
  }

}
