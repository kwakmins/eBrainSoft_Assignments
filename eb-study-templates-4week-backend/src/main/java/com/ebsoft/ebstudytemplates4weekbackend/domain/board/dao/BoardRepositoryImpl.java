package com.ebsoft.ebstudytemplates4weekbackend.domain.board.dao;

import static com.ebsoft.ebstudytemplates4weekbackend.domain.board.entity.QBoard.board;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.entity.Board;
import com.ebsoft.ebstudytemplates4weekbackend.domain.category.entity.Category;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * queryDsl를 사용할 DAO 구현체
 */
public class BoardRepositoryImpl implements BoardRepositoryCustom { //이름 꼭 Impl 사용

  private final JPAQueryFactory queryFactory;

  public BoardRepositoryImpl(EntityManager em) {
    this.queryFactory = new JPAQueryFactory(em);
  }

  @Override
  public Page<Board> searchBoards(Category category, String search, Pageable pageable) {
    List<Board> boards = queryFactory.select(board)
        .from(board)
        .where(categoryEq(category),
            (board.content.contains(search)
                .or(board.title.contains(search))
                .or(board.userName.contains(search))
            )
        )
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    Long total = queryFactory.select(board.count())
        .from(board)
        .where(categoryEq(category),
            (board.content.contains(search)
                .or(board.title.contains(search))
                .or(board.userName.contains(search))
            ))
        .fetchOne();

    return new PageImpl<>(boards, pageable, total);
  }

  private BooleanExpression categoryEq(Category category) {
    return category != null ? board.category.eq(category) : null;
  }
}
