package com.ebsoft.ebstudytemplates4weekbackend.domain.board.dao;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.entity.Board;
import com.ebsoft.ebstudytemplates4weekbackend.domain.category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

/**
 * queryDsl를 사용할 DAO 인터페이스
 */
public interface BoardRepositoryCustom { //이름 상관 없음

  Page<Board> searchBoards(
      @Param("category") Category category,
      @Param("search") String search,
      Pageable pageable
  );

}
