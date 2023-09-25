package com.ebsoft.ebstudytemplates2week.domain.board.api;

import com.ebsoft.ebstudytemplates2week.global.action.Action;
import com.ebsoft.ebstudytemplates2week.global.action.ActionFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@WebServlet(value = "/board/free/*")
@Slf4j
public class BoardController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");

    String[] url = req.getRequestURI().split("/");
    String path = url[url.length - 1]; // ex) http://localhost:8080/board/free/write 에서 마지막 write를 가져온다.
    log.info("경로: " + path);

    ActionFactory factory = ActionFactory.getInstance();
    Action action = factory.getAction(path); // 경로에 맞게 action을 가져온다
    action.execute(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doGet(req, resp);
  }
}
