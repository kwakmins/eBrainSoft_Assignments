package com.ebsoft.ebstudytemplates4weekbackend.global.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


/**
 * 생성 시간과 업데이트 시간 컬럼이 들어간 부모 엔티티
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {

  @CreatedDate
  @Column(name = "created_time", nullable = false, updatable = false)
  private LocalDateTime createdTime;

  @LastModifiedDate
  @Column(name = "updated_time", nullable = false)
  private LocalDateTime updatedTime;
}
