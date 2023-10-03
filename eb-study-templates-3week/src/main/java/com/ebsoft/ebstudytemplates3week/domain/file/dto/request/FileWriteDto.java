package com.ebsoft.ebstudytemplates3week.domain.file.dto.request;

import com.ebsoft.ebstudytemplates3week.domain.file.dto.FileDto;
import lombok.Data;

@Data
public class FileWriteDto {

  // 게시판 id
  public Long boardId;
  // 업로드한 파일 이름
  public String fileName;
  // 서버에서의 파일 이름
  public String storeName;

  public FileWriteDto(Long boardId, FileDto fileDto) {
    this.boardId = boardId;
    this.fileName = fileDto.fileName;
    this.storeName = fileDto.storeName;
  }
}
