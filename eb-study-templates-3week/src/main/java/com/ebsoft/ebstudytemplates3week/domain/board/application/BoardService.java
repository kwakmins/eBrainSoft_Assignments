package com.ebsoft.ebstudytemplates3week.domain.board.application;

import com.ebsoft.ebstudytemplates3week.domain.board.dao.BoardRepository;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.BoardDto;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.BoardWriteDto;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.response.BoardListDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

  private final BoardRepository boardRepository;

  /*
  게시판을 글쓰기 폼에서 받은 dto를 통해, 생성한다.
   */
  @Transactional
  public void addBoard(BoardWriteDto reqDto) {
    boardRepository.writeBoard(reqDto);
  }

  /*
  id로 게시판 조회 + 조회수 증가
   */
  @Transactional
  public BoardDto getBoardById(Long boardId) {
    boardRepository.addView(boardId);
    return boardRepository.findBoardById(boardId);
  }

  /*
  가장 최근에 쓰여진 게시판 id 반환
   */
  public Long getLastWriteBoardId() {
    return boardRepository.lastBoardId();
  }

  /*
  게시판 목록들 반환
   */
  public List<BoardListDto> getBoardList() {
    return boardRepository.findAllBoardToList();
  }
}
