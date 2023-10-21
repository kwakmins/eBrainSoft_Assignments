package com.ebsoft.ebstudytemplates4weekbackend.testconfig;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;

/**
 * restDocs 관한 설정
 */
@TestConfiguration
public class RestDocsConfiguration {

  /**
   * snippets 가독성 향상 설정
   *
   * @return 가독성 향상된 docs
   */
  @Bean
  public RestDocumentationResultHandler write() {
    return MockMvcRestDocumentation.document(
        "{class-name}/{method-name}",
        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
        Preprocessors.preprocessResponse(Preprocessors.prettyPrint())
    );
  }
}