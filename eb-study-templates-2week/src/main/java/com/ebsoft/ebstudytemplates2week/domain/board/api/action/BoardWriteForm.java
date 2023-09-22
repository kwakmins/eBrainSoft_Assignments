package com.ebsoft.ebstudytemplates2week.domain.board.api.action;

import com.ebsoft.ebstudytemplates2week.domain.category.application.CategoryService;
import com.ebsoft.ebstudytemplates2week.global.action.Action;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BoardWriteForm implements Action {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String url = "/boardWrite.jsp";
    CategoryService categoryService = CategoryService.getInstance();

    request.setAttribute("categoryList", categoryService.getAllCategory());

    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
    dispatcher.forward(request, response);
  }
}
