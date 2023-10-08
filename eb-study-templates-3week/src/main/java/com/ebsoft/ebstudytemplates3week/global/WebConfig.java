package com.ebsoft.ebstudytemplates3week.global;

import com.ebsoft.ebstudytemplates3week.global.interceptors.PasswordConfirm;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  // 업데이트에 인터셉터 추가
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new PasswordConfirm())
        .order(1)
        .addPathPatterns("/board/free/update/*");
  }
}
