package com.ebsoft.ebstudytemplates2week.global.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// 팩토리 패턴
public interface Action {

  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException;
}
