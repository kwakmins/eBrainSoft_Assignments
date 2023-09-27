package com.ebsoft.ebstudytemplates3week.domain.board.dao;

import com.ebsoft.ebstudytemplates3week.domain.board.dto.request.BoardWriteDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardRepository {

  void writeBoard(BoardWriteDto boardWriteDto);
}
