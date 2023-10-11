package com.ebsoft.ebstudytemplates4weekbackend.global.init;

import com.ebsoft.ebstudytemplates4weekbackend.domain.category.application.CategoryService;
import com.ebsoft.ebstudytemplates4weekbackend.domain.category.entity.Category;
import com.ebsoft.ebstudytemplates4weekbackend.domain.category.entity.CategoryType;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DbInit {

  @Autowired
  private CategoryService categoryService;

  /**
   * 지정된 카테고리들을 모두 미리 저장한다.
   */
  @PostConstruct
  private void InitCategories() {
    List<Category> categoryList = new ArrayList<>();
    for (CategoryType categoryType : CategoryType.values()) {
      categoryList.add(categoryType.toEntity());
    }

    categoryService.saveCategories(categoryList);

    log.info("지정된 카테고리 저장 완료");
  }
}
