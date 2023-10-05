package com.ebsoft.ebstudytemplates3week.global.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 업데이트,삭제시 비밀번호 인터셉터
 */
@Slf4j
public class PasswordConfirm implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    log.info("비밀번호 체크 인터셉터 {}", request.getRequestURL());
    HttpSession session = request.getSession(false); // 쿠키로 세션 받음

    if (session == null || session.getAttribute("passConfirm") == null) {
      log.info("비밀번호 체크 미인증 사용자");
      return false;
    }
    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    HttpSession session = request.getSession(false);
    if (session != null) {
      log.info("비밀번호 체크 세션 삭제");
      session.invalidate();
    }
  }
}
