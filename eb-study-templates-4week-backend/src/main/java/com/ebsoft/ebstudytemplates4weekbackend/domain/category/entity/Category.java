package com.ebsoft.ebstudytemplates4weekbackend.domain.category.entity;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
@Table(name = "t_category")
public class Category {

  public static final int MAX_NAME_LENGTH = 15;

  //카테고리 id
  @Id
  @Column(name = "category_id", nullable = false)
  private Long id;

  //카테고리 이름
  @Column(name = "category_name", nullable = false, length = MAX_NAME_LENGTH)
  private String name;

  @Builder
  public Category(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
