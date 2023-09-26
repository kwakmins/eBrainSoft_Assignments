package com.ebsoft.ebstudytemplates3week.domain.category.dao;

import com.ebsoft.ebstudytemplates3week.domain.category.dto.CategoryDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryRepository {

  List<CategoryDto> findAllCategories();
}
