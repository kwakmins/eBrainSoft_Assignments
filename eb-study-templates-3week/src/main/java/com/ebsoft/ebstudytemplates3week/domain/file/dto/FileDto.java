package com.ebsoft.ebstudytemplates3week.domain.file.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileDto {

  // 파일 id
  public Long fileId;
  // 게시판 id
  public Long boardId;
  // 업로드한 파일 이름
  public String fileName;
  // 서버에서의 파일 이름
  public String storeName;

  public FileDto(String fileName, String storeName) {
    this.fileName = fileName;
    this.storeName = storeName;
  }
}
