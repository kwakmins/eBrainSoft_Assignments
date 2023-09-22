package com.ebsoft.ebstudytemplates2week.domain.board.api.action;

import com.ebsoft.ebstudytemplates2week.domain.board.application.BoardService;
import com.ebsoft.ebstudytemplates2week.domain.board.entity.Board;
import com.ebsoft.ebstudytemplates2week.global.action.Action;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BoardForm implements Action {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String url = "/board.jsp";
    Long id = Long.valueOf(request.getParameter("id"));
    BoardService boardService = BoardService.getInstance();

    request.setAttribute("board", boardService.getOne(id));
    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
    dispatcher.forward(request, response);
  }
}
