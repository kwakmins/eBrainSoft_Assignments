package com.ebsoft.ebstudytemplates4weekbackend.domain.board.application;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.dao.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;


  @Transactional
  public Long saveBoard() {

    return boardRepository.save();
  }
}
