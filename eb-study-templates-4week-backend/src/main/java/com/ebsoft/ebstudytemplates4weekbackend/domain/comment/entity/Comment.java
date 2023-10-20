package com.ebsoft.ebstudytemplates4weekbackend.domain.comment.entity;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.entity.Board;
import com.ebsoft.ebstudytemplates4weekbackend.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "t_comment")
public class Comment extends BaseEntity {

  public static final int MAX_CONTENT_LENGTH = 255;

  //댓글 id
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "comment_id", nullable = false)
  private Long id;

  //게시판 (N:1)
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "board_id", nullable = false)
  private Board board;

  //댓글 내용
  @Column(name = "content", nullable = false, length = MAX_CONTENT_LENGTH)
  private String content;

  @Builder
  public Comment(Board board, String content) {
    this.board = board;
    this.content = content;
  }
}
