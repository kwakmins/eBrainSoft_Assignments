package com.ebsoft.ebstudytemplates3week.domain.board.application;

import com.ebsoft.ebstudytemplates3week.domain.board.dao.BoardRepository;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.BoardDto;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.BoardPasswordConfirmDto;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.BoardUpdateDto;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.BoardWriteDto;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.SearchDto;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.response.BoardListDto;
import com.ebsoft.ebstudytemplates3week.domain.file.application.FileService;
import com.ebsoft.ebstudytemplates3week.domain.file.convenience.FileStore;
import com.ebsoft.ebstudytemplates3week.global.paging.Pagination;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BoardService {

  private final BoardRepository boardRepository;
  private final FileStore fileStore;
  private final FileService fileService;

  /*
  게시판을 글쓰기 폼에서 받은 dto를 통해, 생성한다.
   */
  @Transactional
  public void addBoard(BoardWriteDto reqDto) {
    fileService.addFile(reqDto);
    boardRepository.writeBoard(reqDto);
  }

  /*
  id로 게시판 조회 + 조회수 증가 O
   */
  @Transactional
  public BoardDto getBoardByIdViewPlus(Long boardId) {
    boardRepository.addView(boardId);
    return boardRepository.findBoardById(boardId);
  }

  /*
id로 게시판 조회 + 조회수 증가 X
 */
  @Transactional
  public BoardDto getBoardById(Long boardId) {
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
  public List<BoardListDto> getBoardList(Pagination pagination) {
    return boardRepository.findAllBoardToList(pagination);
  }

  /*
  게시판 목록들 반환 (검색)
 */
  public List<BoardListDto> getBoardList(SearchDto searchDto) {
    return boardRepository.findAllBoardToListBySearch(searchDto);
  }

  /*
  총 게시판 수 반환
   */
  public int getTotalBoardCnt() {
    return boardRepository.totalBoardCnt();
  }

  /*
  검색에 맞는 총 게시판 수 반환
   */
  public int getTotalBoardCnt(SearchDto searchDto) {
    return boardRepository.totalBoardCntBySearch(searchDto);
  }

  /*
  비밀번호가 같은지 확인
   */
  public boolean isSamePassword(BoardPasswordConfirmDto boardPasswordConfirmDto) {
    return boardRepository.passwordConfirm(boardPasswordConfirmDto);
  }

  /*
  게시판 업데이트
   */
  @Transactional
  public void updateBoard(BoardUpdateDto reqDto) {
    boardRepository.updateBoard(reqDto);
  }

  /*
  게시판 삭제
   */
  @Transactional
  public void deleteBoard(Long boardId) {
    boardRepository.deleteBoard(boardId);
  }
}
