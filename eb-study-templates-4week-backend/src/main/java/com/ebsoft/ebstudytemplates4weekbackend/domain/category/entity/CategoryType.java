package com.ebsoft.ebstudytemplates4weekbackend.domain.category.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 카테고리 종류 ENUM
 */
@Getter
@RequiredArgsConstructor
public enum CategoryType {

  JAVA(1, "JAVA"),
  DATABASE(2, "DataBase"),
  JAVASCRIPT(3, "JavaScript");

  private final long id;
  private final String name;

  public Category toEntity() {
    return Category.builder()
        .id(this.id)
        .name(this.name)
        .build();
  }
}
