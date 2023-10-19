package com.ebsoft.ebstudytemplates4weekbackend.domain.board.dao;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
