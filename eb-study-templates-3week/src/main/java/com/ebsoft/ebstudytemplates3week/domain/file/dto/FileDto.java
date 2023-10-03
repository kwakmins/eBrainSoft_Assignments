package com.ebsoft.ebstudytemplates3week.domain.file.dto;

import lombok.Data;

@Data
public class FileDto {

  // 업로드한 파일 이름
  public String fileName;
  // 서버에서의 파일 이름
  public String storeName;

  public FileDto(String fileName, String storeName) {
    this.fileName = fileName;
    this.storeName = storeName;
  }
}
