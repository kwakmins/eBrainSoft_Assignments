package com.global.utils;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PropertiesMapperTest {

  @Test
  @DisplayName("JSON 매핑에 성공한다")
  void should_notNull_when_createMapper() {
    PropertiesMapper mapper = new PropertiesMapper();
    assertThat(mapper.getMyId()).isNotNull();
    assertThat(mapper.getMyPw()).isNotNull();
    assertThat(mapper.getMyUrl()).isNotNull();
  }
}