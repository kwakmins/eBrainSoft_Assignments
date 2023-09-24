package com.ebsoft.ebstudytemplates2week.domain.board.api.action;

import com.ebsoft.ebstudytemplates2week.domain.board.application.BoardService;
import com.ebsoft.ebstudytemplates2week.domain.board.entity.Board;
import com.ebsoft.ebstudytemplates2week.domain.category.application.CategoryService;
import com.ebsoft.ebstudytemplates2week.global.action.Action;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardWriteACtion implements Action {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    BoardService boardService = new BoardService();
    CategoryService categoryService = new CategoryService();

    Board board = Board.builder()
        .boardId(boardService.getNextId())
        .category(categoryService.findById(Long.valueOf(request.getParameter("categoryId"))))
        .content(request.getParameter("content"))
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .password(request.getParameter("password"))
        .title(request.getParameter("title"))
        .user(request.getParameter("user"))
        .viewCount(0)
        .build();

    log.info(board.getContent());
    boardService.createBoard(board);

    new BoardWriteForm().execute(request, response);
  }
}
