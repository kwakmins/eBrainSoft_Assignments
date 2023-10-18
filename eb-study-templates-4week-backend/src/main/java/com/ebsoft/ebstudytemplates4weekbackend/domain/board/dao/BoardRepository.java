package com.ebsoft.ebstudytemplates4weekbackend.domain.board.dao;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.entity.Board;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

  @EntityGraph(attributePaths = {"comments", "category", "files"})
  Optional<Board> findWithCommentsById(Long boardID);
}
