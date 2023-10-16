package com.ebsoft.ebstudytemplates4weekbackend.global.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 스프링 시큐리티 관련 설정
 */
@Configuration
@EnableWebSecurity
public class SpringSecurity {

  /**
   * 필터 체인
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .cors().disable()    //cors방지
        .csrf().disable()    //csrf방지
        .formLogin().disable()  //기본 로그인 페이지 없애기
        .headers().frameOptions().disable();
    return http.build();
  }

  /**
   * 비밀번호 인코딩 방식 정하기
   *
   * @return BCrypt 인코더 사용
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
