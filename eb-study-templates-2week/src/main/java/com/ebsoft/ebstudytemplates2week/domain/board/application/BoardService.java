package com.ebsoft.ebstudytemplates2week.domain.board.application;

import com.ebsoft.ebstudytemplates2week.domain.board.dao.BoardRepository;
import com.ebsoft.ebstudytemplates2week.domain.board.entity.Board;
import com.ebsoft.ebstudytemplates2week.global.mybatis.MybatisConfig;
import java.util.Objects;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

@Slf4j
public class BoardService {

  @Getter
  private static final BoardService instance = new BoardService();
  BoardRepository boardRepository;

  public void createBoard(Board board) {
    SqlSession sqlSession = null;
    if (!board.valid()) {
      throw new IllegalArgumentException();
    }
    try {
      sqlSession = MybatisConfig.getSqlSession();
      boardRepository = sqlSession.getMapper(BoardRepository.class);
      boardRepository.create(board);
      sqlSession.commit(); // 커밋 추가 (myBatis는 AutoCommit이 꺼져있음)
    } finally {
      if (sqlSession != null) {
        sqlSession.close();
      }
    }
  }

  public Long getNextId() {
    Long nextId = 0L;
    SqlSession sqlSession = null;
    try {
      sqlSession = MybatisConfig.getSqlSession();
      boardRepository = sqlSession.getMapper(BoardRepository.class);
      nextId = boardRepository.nextId() + 1L;
    } finally {
      Objects.requireNonNull(sqlSession).close();
    }
    return nextId;
  }
}
