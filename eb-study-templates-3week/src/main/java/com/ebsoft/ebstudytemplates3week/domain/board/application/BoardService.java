package com.ebsoft.ebstudytemplates3week.domain.board.application;

import com.ebsoft.ebstudytemplates3week.domain.board.dao.BoardRepository;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.BoardWriteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;

  /*
  게시판을 글쓰기 폼에서 받은 dto를 통해, 생성한다.
   */
  public void addBoard(BoardWriteDto reqDto) {
    boardRepository.writeBoard(reqDto);
  }

}
