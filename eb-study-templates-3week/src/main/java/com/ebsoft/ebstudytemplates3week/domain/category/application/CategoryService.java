package com.ebsoft.ebstudytemplates3week.domain.category.application;

import com.ebsoft.ebstudytemplates3week.domain.category.dao.CategoryRepository;
import com.ebsoft.ebstudytemplates3week.domain.category.dto.CategoryDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  /**
   * @return 모든 카테고리
   */
  public List<CategoryDto> getAllCategory() {
    return categoryRepository.findAllCategories();
  }
}
