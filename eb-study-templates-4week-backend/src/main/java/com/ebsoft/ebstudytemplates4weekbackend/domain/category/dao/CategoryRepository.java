package com.ebsoft.ebstudytemplates4weekbackend.domain.category.dao;

import com.ebsoft.ebstudytemplates4weekbackend.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
