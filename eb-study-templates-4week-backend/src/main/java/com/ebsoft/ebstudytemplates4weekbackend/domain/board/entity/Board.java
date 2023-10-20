package com.ebsoft.ebstudytemplates4weekbackend.domain.board.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.dto.request.BoardUpdateReqDto;
import com.ebsoft.ebstudytemplates4weekbackend.domain.category.entity.Category;
import com.ebsoft.ebstudytemplates4weekbackend.domain.comment.entity.Comment;
import com.ebsoft.ebstudytemplates4weekbackend.domain.file.entity.File;
import com.ebsoft.ebstudytemplates4weekbackend.global.entity.BaseEntity;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
@Table(name = "t_baord")
public class Board extends BaseEntity {

  public static final int MAX_USERNAME_LENGTH = 10;
  public static final int MAX_PASSWORD_LENGTH = 512;
  public static final int MAX_TITLE_LENGTH = 100;

  //게시판 id
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "board_id", nullable = false)
  private Long id;

  //카테고리 (1:1)
  @OneToOne(fetch = LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  //유저명
  @Column(name = "user_name", nullable = false, length = MAX_USERNAME_LENGTH)
  private String userName;

  //비밀번호
  @Column(name = "password", nullable = false, length = MAX_PASSWORD_LENGTH)
  private String password;

  //제목
  @Column(name = "title", nullable = false, length = MAX_TITLE_LENGTH)
  private String title;

  //내용
  @Column(columnDefinition = "TEXT", name = "content", nullable = false)
  private String content;

  //조회수
  @Column(name = "view_count", nullable = false)
  private Integer viewCount;

  // 댓글 리스트 (1:N)
  @OneToMany(mappedBy = "board", cascade = ALL)
  private List<Comment> comments;

  // 파일 리스트 (N:1)
  @OneToMany(mappedBy = "board", cascade = ALL)
  private List<File> files;

  @Builder
  public Board(Category category, String userName, String password, String title, String content,
      Integer viewCount) {
    this.category = category;
    this.userName = userName;
    this.password = password;
    this.title = title;
    this.content = content;
    this.viewCount = viewCount;
  }

  public void update(BoardUpdateReqDto updateReqDto, List<File> files) {
    this.userName = updateReqDto.getUserName();
    this.title = updateReqDto.getTitle();
    this.content = updateReqDto.getContent();
    this.files = files;
  }
}
