package com.ebsoft.ebstudytemplates3week.domain.board.dao;

import com.ebsoft.ebstudytemplates3week.domain.board.dto.BoardDto;
import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.BoardWriteDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardRepository {

  void writeBoard(BoardWriteDto boardWriteDto);

  BoardDto findBoardById(Long boardId);

  void addView(Long BoardId);

  Long totalBoardCnt();

  Long lastBoardId();
}
