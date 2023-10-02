package com.ebsoft.ebstudytemplates3week.domain.board.dao;

import com.ebsoft.ebstudytemplates3week.domain.board.dto.BoardDto;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.BoardPasswordConfirmDto;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.BoardWriteDto;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.SearchDto;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.response.BoardListDto;
import com.ebsoft.ebstudytemplates3week.global.paging.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardRepository {

  void writeBoard(BoardWriteDto boardWriteDto);

  BoardDto findBoardById(Long boardId);

  void addView(Long BoardId);

  int totalBoardCnt();

  int totalBoardCntBySearch(SearchDto searchDto);

  Long lastBoardId();

  List<BoardListDto> findAllBoardToList(Pagination pagination);

  List<BoardListDto> findAllBoardToListBySearch(SearchDto searchDto);

  Boolean passwordConfirm(BoardPasswordConfirmDto boardPasswordConfirmDto);
}
