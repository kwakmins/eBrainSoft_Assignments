package com.ebsoft.ebstudytemplates4weekbackend.domain.board.dao;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.entity.Board;
import com.ebsoft.ebstudytemplates4weekbackend.domain.category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {

  /**
   * 모든 게시판 조회 (카테고리 O)
   *
   * @param category 검색 카테고리
   * @param search   검색 명
   * @param pageable 페이지
   * @return 조건에 맞는 모든 게시판
   */
  @Query("SELECT b FROM Board b"
      + " WHERE b.category = :category"
      + " AND (b.userName LIKE %:search%"
      + " OR b.content LIKE %:search%"
      + " OR b.title LIKE %:search%)")
  Page<Board> findSearchWithCategoryBoards(@Param("category") Category category,
      @Param("search") String search,
      Pageable pageable);

  /**
   * 모든 게시판 조회 (카테고리 X)
   *
   * @param userName 검색 명
   * @param content  검색 명
   * @param title    검색 명
   * @param pageable 페이지
   * @return 조건에 맞는 모든 게시판
   */
  Page<Board> findBoardsByUserNameContainingOrContentContainingOrTitleContaining(
      String userName, String content, String title, Pageable pageable);

}
