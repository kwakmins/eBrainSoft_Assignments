package com.ebsoft.ebstudytemplates4weekbackend.domain.file.dto.response;

import com.ebsoft.ebstudytemplates4weekbackend.domain.file.entity.File;
import lombok.Getter;

/**
 * 파일 조회 ResDto
 */

@Getter
public class FileResDto {

  //파일 이름
  private String fileName;

  //저장소 이름
  private String storeName;

  public FileResDto(File file) {
    this.fileName = file.getFileName();
    this.storeName = file.getStoreName();
  }
}
