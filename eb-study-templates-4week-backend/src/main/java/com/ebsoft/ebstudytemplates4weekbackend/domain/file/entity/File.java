package com.ebsoft.ebstudytemplates4weekbackend.domain.file.entity;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.ebsoft.ebstudytemplates4weekbackend.domain.board.entity.Board;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
@Table(name = "t_file")
public class File {

  public static final int MAX_FILE_NAME_LENGTH = 512;
  public static final int MAX_STORE_NAME_LENGTH = 512;

  //파일 id
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "file_id", nullable = false)
  private Long id;

  //게시판 (N:1)
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "board_id")
  private Board board;

  //업로드한 파일 이름
  @Column(name = "file_name", nullable = false, length = MAX_FILE_NAME_LENGTH)
  private String fileName;

  //서버에서의 파일 이름
  @Column(name = "store_name", nullable = false, length = MAX_STORE_NAME_LENGTH)
  private String storeName;

  @Builder
  public File(Board board, String fileName, String storeName) {
    this.board = board;
    this.fileName = fileName;
    this.storeName = storeName;
  }
}
