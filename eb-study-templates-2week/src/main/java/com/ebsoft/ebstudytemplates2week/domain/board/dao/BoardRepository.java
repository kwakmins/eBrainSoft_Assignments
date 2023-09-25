package com.ebsoft.ebstudytemplates2week.domain.board.dao;

import com.ebsoft.ebstudytemplates2week.domain.board.entity.Board;

public interface BoardRepository {

  /**
   * board 생성
   */
  public void create(Board board);

  public Long nextId();

  Board getOne(Long id);
}
